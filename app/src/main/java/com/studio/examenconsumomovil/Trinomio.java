package com.studio.examenconsumomovil;

import org.json.JSONException;
import org.json.JSONObject;
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

public class Trinomio extends AppCompatActivity {

    private EditText edtA, edtB, edtC;
    private TextView txtResultado;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trinomio);

        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtC = findViewById(R.id.edtC);
        txtResultado = findViewById(R.id.txtResultado);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularTrinomio();
            }
        });
    }

    private void calcularTrinomio() {
        String a = edtA.getText().toString();
        String b = edtB.getText().toString();
        String c = edtC.getText().toString();

        // Validar entradas
        if (a.isEmpty() || b.isEmpty() || c.isEmpty()) {
            Toast.makeText(this, "Por favor, llena todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Double.parseDouble(a);
            Double.parseDouble(b);
            Double.parseDouble(c);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Introduce valores numéricos válidos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Construir la URL del endpoint
        String url = Config.IP+"/trinomio/" + a + "/" + b + "/" + c;

        // Enviar solicitud al servidor
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Parsear la respuesta JSON
                            JSONObject jsonResponse = new JSONObject(response);

                            // Obtener los valores del JSON
                            String mensaje = jsonResponse.optString("mensaje", "Sin mensaje");
                            String binomio = jsonResponse.optString("binomio", "No aplica");
                            String solucion = jsonResponse.optString("solucion", "No aplica");

                            // Formatear y mostrar los resultados
                            String resultado = mensaje + "\n" +
                                    "Binomio: " + binomio + "\n" +
                                    "Solución: " + solucion;

                            txtResultado.setText(resultado);
                        } catch (JSONException e) {
                            txtResultado.setText("Error al procesar la respuesta del servidor.");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Trinomio.this, "Error al conectarse al servidor.", Toast.LENGTH_SHORT).show();
                    }
                });

        // Añadir solicitud a la cola
        queue.add(stringRequest);
    }
}