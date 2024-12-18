package com.studio.examenconsumomovil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class PerimetroAreaFiguras extends AppCompatActivity {

    private EditText etRadioMayor, etRadioMenor, etBaseMayor, etBaseMenor, etAltura, etLado1, etLado2, etLadoPentagono;
    private TextView tvResultadoElipse, tvResultadoTrapecio, tvResultadoPentagono;
    private Button btnCalcularElipse, btnCalcularTrapecio, btnCalcularPentagono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perimetro_area_figuras);

        // Inicializar vistas
        etRadioMayor = findViewById(R.id.etRadioMayor);
        etRadioMenor = findViewById(R.id.etRadioMenor);
        btnCalcularElipse = findViewById(R.id.btnCalcularElipse);
        tvResultadoElipse = findViewById(R.id.tvResultadoElipse);

        etBaseMayor = findViewById(R.id.etBaseMayor);
        etBaseMenor = findViewById(R.id.etBaseMenor);
        etAltura = findViewById(R.id.etAltura);
        etLado1 = findViewById(R.id.etLado1);
        etLado2 = findViewById(R.id.etLado2);
        btnCalcularTrapecio = findViewById(R.id.btnCalcularTrapecio);
        tvResultadoTrapecio = findViewById(R.id.tvResultadoTrapecio);

        etLadoPentagono = findViewById(R.id.etLadoPentagono);
        btnCalcularPentagono = findViewById(R.id.btnCalcularPentagono);
        tvResultadoPentagono = findViewById(R.id.tvResultadoPentagono);

        // Configurar botones
        btnCalcularElipse.setOnClickListener(v -> calcularElipse());
        btnCalcularTrapecio.setOnClickListener(v -> calcularTrapecio());
        btnCalcularPentagono.setOnClickListener(v -> calcularPentagono());
    }

    private void calcularElipse() {
        String a = etRadioMayor.getText().toString();
        String b = etRadioMenor.getText().toString();
        if (a.isEmpty() || b.isEmpty()) {
            mostrarError();
            return;
        }
        realizarSolicitud(Config.IP +"/elipse/" + a + "/" + b, tvResultadoElipse);
    }

    private void calcularTrapecio() {
        String B = etBaseMayor.getText().toString();
        String b = etBaseMenor.getText().toString();
        String h = etAltura.getText().toString();
        String L1 = etLado1.getText().toString();
        String L2 = etLado2.getText().toString();
        if (B.isEmpty() || b.isEmpty() || h.isEmpty() || L1.isEmpty() || L2.isEmpty()) {
            mostrarError();
            return;
        }
        realizarSolicitud(Config.IP +"/trapecio/" + B + "/" + b + "/" + h + "/" + L1 + "/" + L2, tvResultadoTrapecio);
    }

    private void calcularPentagono() {
        String l = etLadoPentagono.getText().toString();
        if (l.isEmpty()) {
            mostrarError();
            return;
        }
        realizarSolicitud(Config.IP +"/pentagono/" + l, tvResultadoPentagono);
    }

    private void realizarSolicitud(String url, TextView resultadoView) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> resultadoView.setText(response),
                error -> {
                    Toast.makeText(this, "Error en la solicitud", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                });
        queue.add(stringRequest);
    }

    private void mostrarError() {
        Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
    }
}
