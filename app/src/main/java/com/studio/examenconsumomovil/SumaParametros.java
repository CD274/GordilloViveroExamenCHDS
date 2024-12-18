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

public class SumaParametros extends AppCompatActivity {

    private EditText editTextNumero; // Para ingresar el número
    private TextView textViewResultado; // Para mostrar el resultado
    private Button buttonCalcular; // Botón para enviar la solicitud

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma_parametros);

        // Referencias a los elementos de la interfaz
        editTextNumero = findViewById(R.id.editTextNumero);
        textViewResultado = findViewById(R.id.textViewResultado);
        buttonCalcular = findViewById(R.id.buttonCalcular);

        // Configurar el botón para enviar la solicitud
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarSuma();
            }
        });
    }

    private void enviarSuma() {
        String numeroIngresado = editTextNumero.getText().toString().trim();

        if (numeroIngresado.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese un número", Toast.LENGTH_SHORT).show();
            return;
        }

        // Construir la URL con el parámetro
        String url = Config.IP +"/suma/" + numeroIngresado;

        // Crear la cola de solicitudes
        RequestQueue queue = Volley.newRequestQueue(this);

        // Crear la solicitud GET
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Mostrar el resultado en el TextView
                        textViewResultado.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SumaParametros.this, "Error al obtener el resultado", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

        // Agregar la solicitud a la cola
        queue.add(stringRequest);
    }
}
