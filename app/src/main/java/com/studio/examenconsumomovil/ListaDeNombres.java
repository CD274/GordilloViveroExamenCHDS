package com.studio.examenconsumomovil;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListaDeNombres extends AppCompatActivity {

    private TextView textViewListaNombres; // Declaración del TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_nombres);

        // Asignar referencia al TextView
        textViewListaNombres = findViewById(R.id.textViewListaNombres);

        // Llamar al método para obtener los datos
        recibirMensaje();
    }

    private void recibirMensaje() {
        String url = Config.IP +"/nombre"; // Endpoint del servidor

        // Crear la cola de solicitudes
        RequestQueue queue = Volley.newRequestQueue(this);

        // Solicitud JSON para obtener un arreglo
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        StringBuilder listaNombres = new StringBuilder();

                        try {
                            // Iterar por el arreglo JSON
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                String id = obj.getString("id");
                                String nombre = obj.getString("nombre");
                                listaNombres.append("ID: ").append(id)
                                        .append(", Nombre: ").append(nombre)
                                        .append("\n");
                            }
                            // Mostrar la lista en el TextView
                            textViewListaNombres.setText(listaNombres.toString());
                        } catch (JSONException e) {
                            Toast.makeText(ListaDeNombres.this, "Error al procesar datos", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListaDeNombres.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

        // Agregar la solicitud a la cola
        queue.add(jsonArrayRequest);
    }
}
