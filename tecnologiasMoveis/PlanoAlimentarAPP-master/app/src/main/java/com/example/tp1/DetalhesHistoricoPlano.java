package com.example.tp1;

import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetalhesHistoricoPlano extends AppCompatActivity {

    private TextView textViewHoraPrevista, textViewNomeRefeicao, textViewInformacao, textViewHoraRealizada,
                        textViewData, textViewObservacao;
    int position;
    private ArrayList<HistoricoPlanoAlimentar> gereHistoricoPlano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_refeicao);


        textViewHoraPrevista = findViewById(R.id.editTextHoraPrevista);
        textViewNomeRefeicao = findViewById(R.id.editTextTextRefeicao);
        textViewInformacao = findViewById(R.id.editTextTextInformacao);
        textViewHoraRealizada = findViewById(R.id.editTextHoraRealizada1);
        textViewData = findViewById(R.id.editTextData);
        textViewObservacao = findViewById(R.id.editTextDetalhesObservacao);


        Bundle extras = getIntent().getExtras();

        if(extras!=null) {
            position = extras.getInt("position");
            gereHistoricoPlano = new ArrayList<>();
            gereHistoricoPlano = (ArrayList<HistoricoPlanoAlimentar>) extras.getSerializable("gereHistorico");
        }



        HistoricoPlanoAlimentar index = gereHistoricoPlano.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        textViewNomeRefeicao.setText(index.getNomeRefeicao());
        textViewHoraPrevista.setText(index.getHora());
        textViewInformacao.setText(index.getInformacaoRefeicao());
        if(!index.getHoraRealizada().equals(null)) {
            textViewHoraRealizada.setText(index.getHoraRealizada());
        }
        textViewData.setText(sdf.format(index.getDataCriacao()));
        if(!index.getObservacao().equals(null)) {
            textViewObservacao.setText(index.getObservacao());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
