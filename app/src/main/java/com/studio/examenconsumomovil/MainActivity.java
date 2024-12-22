package com.studio.examenconsumomovil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los botones
        Button btnBiografia = findViewById(R.id.btnBiografia);
        Button btnListaNombres = findViewById(R.id.btnListaNombres);
        Button btnPerimetroArea = findViewById(R.id.btnPerimetroArea);
        Button btnSuma = findViewById(R.id.btnSuma);
        Button btnSumaParametros = findViewById(R.id.btnTrinomio);
        Button btnTrinomio = findViewById(R.id.btnTrinomioC);

        // Evento para abrir Biografía
        btnBiografia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Biografia.class));
            }
        });

        // Evento para abrir Lista de Nombres
        btnListaNombres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListaDeNombres.class));
            }
        });

        // Evento para abrir Perímetro y Área
        btnPerimetroArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PerimetroAreaFiguras.class));
            }
        });

        // Evento para abrir Suma
        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Suma.class));
            }
        });

        // Evento para abrir Suma con Parámetros
        btnSumaParametros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SumaParametros.class));
            }
        });
        // Evento para abrir Calculo de Trinomio Cuadrado Perfecto
        btnTrinomio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Trinomio.class));
            }
        });
    }
}
