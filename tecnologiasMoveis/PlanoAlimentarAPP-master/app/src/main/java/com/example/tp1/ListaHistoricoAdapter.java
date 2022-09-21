package com.example.tp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ListaHistoricoAdapter extends BaseAdapter {

    Context context;
    ArrayList<HistoricoPlanoAlimentar> listaPlanoAlimentar;
    Calendar c;

    public ListaHistoricoAdapter (Context aContext, ArrayList<HistoricoPlanoAlimentar> aLista, Calendar c) {
        this.context = aContext;
        this.listaPlanoAlimentar = aLista;
        this.c = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = convertView;

        if (itemView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.list_row2, parent, false);
        }


        HistoricoPlanoAlimentar plano = listaPlanoAlimentar.get(position);

        //Alteramos o seu conteúdo com base no elementos guardados no ArrayList

        if(plano.getDataCriacao().compareTo(c.getTime()) == 0) {
            TextView horaRefeicao = (TextView) itemView.findViewById(R.id.horaRefeicao);
            TextView nomeRefeicao = (TextView) itemView.findViewById(R.id.nomeRefeicao);
            TextView horaRealizadaRefeicao = (TextView) itemView.findViewById(R.id.horaRealizadaRefeicao);

            horaRefeicao.setText(plano.getHora());
            nomeRefeicao.setText(plano.getNomeRefeicao());
            horaRealizadaRefeicao.setText(plano.getHoraRealizada());
       }

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
