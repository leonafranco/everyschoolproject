package com.example.tp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListaPlanoAlimentar extends AppCompatActivity {

    private final static int REQUEST_CODE = 001;
    private ListView listView;
    private ListaPlanoAdapter adapter;
    private GerePlanos gerePlanos;
    private DB dataBaseHelper;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.plano_alimentar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano_alimentar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        gerePlanos = (GerePlanos) getIntent().getSerializableExtra("arrayListRef");

        sortArrayList();

        adapter = new ListaPlanoAdapter(this, gerePlanos.listaPlanos());    //Obtém-se o novo adapter definido por a classe ListAdapter
        listView = findViewById(R.id.listViewRefeicoes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), DetalhesPlano.class);
                i.putExtra("position", position);
                i.putExtra("gerePlano", gerePlanos);
                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        //Guarda o estado do objeto quando a activity for terminada
        //Isto para quando o ecrã mudar de posição
        savedInstanceState.putSerializable("arrayList", gerePlanos);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //Restaura os dados quando a activity voltar à posição
        gerePlanos = (GerePlanos) savedInstanceState.getSerializable("arrayList");

        sortArrayList();

        adapter = new ListaPlanoAdapter(this, gerePlanos.listaPlanos());    //Obtém-se o novo adapter definido por a classe ListAdapter
        listView = findViewById(R.id.listViewRefeicoes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), DetalhesPlano.class);
                i.putExtra("position", position);
                i.putExtra("gerePlano", gerePlanos);
                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    //Recupera o estado do objeto GerePlano quando voltar da activity CriarPlano
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                if(data != null && data.getExtras() != null) {
                    gerePlanos = (GerePlanos) data.getExtras().getSerializable("backGere");

                    sortArrayList();

                    adapter = new ListaPlanoAdapter(this, gerePlanos.listaPlanos());    //Obtém-se o novo adapter definido por a classe ListAdapter
                    listView = findViewById(R.id.listViewRefeicoes);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i = new Intent(getApplicationContext(), DetalhesPlano.class);
                            i.putExtra("position", position);
                            i.putExtra("gerePlano", gerePlanos);
                            startActivityForResult(i, REQUEST_CODE);
                        }
                    });
                }
            }
        }
    }

    //Eu coloquei este método aqui mas não está a funcionar correctamente
    //Depois verifica como é que se volta para activity MAIN e implementa o código que lá está dentro
    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        intent.putExtra("backHome", gerePlanos);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.Adicionar:
                Intent i = new Intent(getApplicationContext(), CriarPlano.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("refGere", gerePlanos);
                i.putExtras(bundle);
                startActivityForResult(i,REQUEST_CODE);
                break;
            case R.id.Definicoes:

                startActivity(new Intent("com.example.tp1.Definicoes"));
                break;


        }

        return super.onOptionsItemSelected(item);

    }

    private void sortArrayList() {
        Collections.sort(gerePlanos.listaPlanos(), new Comparator<PlanoAlimentar>() {
            @Override
            public int compare(PlanoAlimentar o1, PlanoAlimentar o2) {
                return o1.getHora().compareTo(o2.getHora());
            }
        });
    }

}