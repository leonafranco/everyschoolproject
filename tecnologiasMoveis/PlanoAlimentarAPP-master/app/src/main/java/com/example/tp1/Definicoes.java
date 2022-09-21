package com.example.tp1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Definicoes extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_person);

        EditText editTextNomePaciente = findViewById(R.id.editTextNomeDoente);
        EditText editTextIntervalo = findViewById(R.id.editTextIntervalo);
        Button buttonSubmeter = findViewById(R.id.buttonSummit);

        buttonSubmeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editPreferences = getSharedPreferences("SharedPrefDef", MODE_PRIVATE).edit();

                String nome = editTextNomePaciente.getText().toString();
                if (verificaIntervaloRefeicao(editTextIntervalo) && nome != null) {
                    editPreferences.putInt("intervaloRef", Integer.parseInt(editTextIntervalo.getText().toString()));
                    editPreferences.putString("nomeDoente", nome);
                    editPreferences.apply();
                    finish();
                } else {
                    Toast.makeText(Definicoes.this, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public boolean verificaIntervaloRefeicao(EditText aEditTextIntervalo) {

        if (aEditTextIntervalo.getText().toString() == null)
            return false;
        else {
            int minuto = Integer.parseInt(aEditTextIntervalo.getText().toString());
            if (minuto < 0 || minuto > 59) return false;
            return true;
        }
    }
}
