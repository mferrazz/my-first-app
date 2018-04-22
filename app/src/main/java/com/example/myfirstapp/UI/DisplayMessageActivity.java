package com.example.myfirstapp.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String nome = intent.getStringExtra(MainActivity.EXTRA_NOME);
        String cognome = intent.getStringExtra(MainActivity.EXTRA_COGNOME);
        String telefono = intent.getStringExtra(MainActivity.EXTRA_TELEFONO);

        TextView viewNome = findViewById(R.id.nome);
        TextView viewCognome = findViewById(R.id.cognome);
        TextView viewTelefono = findViewById(R.id.telefono);

        viewNome.setText(nome);
        viewCognome.setText(cognome);
        viewTelefono.setText(telefono);
    }
}
