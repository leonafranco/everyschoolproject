package com.example.tp1;

import java.io.Serializable;
import java.util.ArrayList;

public class GereHistoricoPlano implements Serializable {

    private ArrayList<HistoricoPlanoAlimentar> listaHistoricoPLano = new ArrayList<>();

    public boolean adicionarPlanoHistorico(HistoricoPlanoAlimentar aHistorico) {

        if (this.listaHistoricoPLano != null) {

            this.listaHistoricoPLano.add(aHistorico);
            return true;
        }
        return false;
    }

    public ArrayList<HistoricoPlanoAlimentar> getListaHistoricoPLano() {
        return this.listaHistoricoPLano;
    }


}
