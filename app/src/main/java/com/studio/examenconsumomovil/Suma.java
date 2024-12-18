package com.studio.examenconsumomovil;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Suma extends AppCompatActivity {

    private TextView textViewSuma; // Declaración del TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma);

        // Asignar referencia al TextView
        textViewSuma = findViewById(R.id.textViewSuma);

        // Llamar al método para obtener el resultado
        obtenerSuma();
    }

    private void obtenerSuma() {
        String url = Config.IP +"/suma"; // Endpoint del servidor

        // Crear la cola de solicitudes
        RequestQueue queue = Volley.newRequestQueue(this);

        // Solicitud String para obtener el resultado
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Mostrar el resultado en el TextView
                        textViewSuma.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Suma.this, "Error al obtener el resultado", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

        // Agregar la solicitud a la cola
        queue.add(stringRequest);
    }
}
