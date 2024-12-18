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

public class Biografia extends AppCompatActivity {

    private TextView textViewBiografia; // Declaración del TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biografia);

        // Asignar referencia al TextView
        textViewBiografia = findViewById(R.id.textViewBiografia);

        // Llamar al método para obtener los datos del servidor
        recibirMensaje();
    }

    private void recibirMensaje() {
        String url = Config.IP +"/Carlos";

        // Crear la cola de solicitudes
        RequestQueue queue = Volley.newRequestQueue(this);

        // Crear una solicitud de tipo StringRequest
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Mostrar respuesta en el TextView
                        textViewBiografia.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Biografia.this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        // Agregar la solicitud a la cola
        queue.add(stringRequest);
    }
}
