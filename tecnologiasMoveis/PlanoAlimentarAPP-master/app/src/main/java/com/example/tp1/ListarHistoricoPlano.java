package com.example.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ListarHistoricoPlano extends AppCompatActivity {

    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private ListaHistoricoAdapter adapterLista;
    private ListView listView;
    private GereHistoricoPlano gereHistoricoDB;
    private ArrayList<HistoricoPlanoAlimentar> gereHistorico;
    private Button botaoProximo, botaoAnterior;
    private Calendar c;
    private ArrayList<HistoricoPlanoAlimentar> listaPlanoAlimentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitorico_refeicao);

        botaoProximo = findViewById(R.id.buttonProximo);
        botaoAnterior = findViewById(R.id.buttonAnterior);
        TextView textViewDate = findViewById(R.id.textViewDateHistory);
        textViewDate.setText(formatDate.format(new Date()));
        DB dataBaseHelper = new DB(ListarHistoricoPlano.this);
        gereHistoricoDB = dataBaseHelper.listarHistorico();
        gereHistorico = gereHistoricoDB.getListaHistoricoPLano();
        c = Calendar.getInstance();

        try {
            c.setTime(formatDate.parse(textViewDate.getText().toString()));
            textViewDate.setText(formatDate.format(c.getTime()));
            listaPlanoAlimentar = new ArrayList<>();


            for (int i = 0; i < gereHistorico.size(); i++) {
                if(gereHistorico.get(i).getDataCriacao().compareTo(c.getTime()) == 0) {
                    listaPlanoAlimentar.add(gereHistorico.get(i));
                }
            }

            adapterLista = new ListaHistoricoAdapter(ListarHistoricoPlano.this, listaPlanoAlimentar, c);
            listView = findViewById(R.id.listViewHitoricoRefeicoes);
            listView.setAdapter(adapterLista);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(getApplicationContext(), DetalhesHistoricoPlano.class);
                    i.putExtra("position", position);
                    i.putExtra("gereHistorico", listaPlanoAlimentar);
                    startActivityForResult(i,001);
                }
            });

        } catch (ParseException e) {
            e.printStackTrace();
        }



        botaoProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // c = Calendar.getInstance();
                try {
                    c.setTime(formatDate.parse(textViewDate.getText().toString()));
                    c.add(Calendar.DATE, 1);
                    textViewDate.setText(formatDate.format(c.getTime()));
                    listaPlanoAlimentar = new ArrayList<>();


                    for (int i = 0; i < gereHistorico.size(); i++) {
                        if(gereHistorico.get(i).getDataCriacao().compareTo(c.getTime()) == 0) {
                            listaPlanoAlimentar.add(gereHistorico.get(i));
                        }
                    }

                    adapterLista = new ListaHistoricoAdapter(ListarHistoricoPlano.this, listaPlanoAlimentar, c);
                    listView = findViewById(R.id.listViewHitoricoRefeicoes);
                    listView.setAdapter(adapterLista);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getApplicationContext(), DetalhesHistoricoPlano.class);
                            i.putExtra("position", position);
                            i.putExtra("gereHistorico", listaPlanoAlimentar);
                            startActivityForResult(i,001);
                        }
                    });

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        botaoAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    c.setTime(formatDate.parse(textViewDate.getText().toString()));
                    c.add(Calendar.DATE, -1);
                    textViewDate.setText(formatDate.format(c.getTime()));

                    listaPlanoAlimentar = new ArrayList<>();

                    for (int i = 0; i < gereHistorico.size(); i++) {
                        if(gereHistorico.get(i).getDataCriacao().compareTo(c.getTime()) == 0) {
                            listaPlanoAlimentar.add(gereHistorico.get(i));
                        }
                    }

                    adapterLista = new ListaHistoricoAdapter(ListarHistoricoPlano.this, listaPlanoAlimentar, c);
                    listView = findViewById(R.id.listViewHitoricoRefeicoes);
                    listView.setAdapter(adapterLista);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getApplicationContext(), DetalhesHistoricoPlano.class);
                            i.putExtra("position", position);
                            i.putExtra("gereHistorico", listaPlanoAlimentar);
                            startActivityForResult(i,001);
                        }
                    });

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        });

        // Receber o ArrayLIst através da transição de activities

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        //Guarda o estado do objeto quando a activity for terminada
        //Isto para quando o ecrã mudar de posição
        savedInstanceState.putSerializable("arrayList", gereHistorico);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        gereHistorico = (ArrayList<HistoricoPlanoAlimentar>) savedInstanceState.getSerializable("arrayList");

    }


}
