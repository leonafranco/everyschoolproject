package com.example.tp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaPlanoAdapter extends BaseAdapter {

    Context context;
    ArrayList<PlanoAlimentar> listaPlanoAlimentar;

    public ListaPlanoAdapter (Context aContext, ArrayList<PlanoAlimentar> aLista) {
        this.context = aContext;
        this.listaPlanoAlimentar = aLista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = convertView;

        if (itemView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.list_row, parent, false);
        }

        TextView horaRefeicao = (TextView) itemView.findViewById(R.id.horaRefeicao);
        TextView nomeRefeicao = (TextView) itemView.findViewById(R.id.nomeRefeicao);
        //TextView realizadaRefeicao = (textView) itemView.findViewById(R.id.textViewRealizacao);

        PlanoAlimentar plano = listaPlanoAlimentar.get(position);

        //Alteramos o seu conteúdo com base no elementos guardados no ArrayList
        horaRefeicao.setText(plano.getHora());
        nomeRefeicao.setText(plano.getRefeicao());

        //Devolve a View que será apresentada dentro da ListView
        return itemView;
    }

    @Override
    public int getCount() {
        return listaPlanoAlimentar.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPlanoAlimentar.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
