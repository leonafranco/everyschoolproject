package com.example.tp1;

import java.io.Serializable;
import java.util.Date;

public class HistoricoPlanoAlimentar implements Serializable {

    private Date dataCriacao;
    private String hora;
    private String refeicao;
    private String informacaoRefeicao;
    private String modoConsumida;
    private String horaRealizada;
    private String observacao;

    public HistoricoPlanoAlimentar(String aHora, String aRefeicao, String aInformacaoRefeicao,
                                   Date aDataCriacao, String aHoraRealizada, String aObservacao, String aModoConsumida) {

        this.refeicao = aRefeicao;
        this.informacaoRefeicao = aInformacaoRefeicao;
        this.hora = aHora;
        this.dataCriacao = aDataCriacao;
        this.horaRealizada = aHoraRealizada;
        this.observacao = aObservacao;
        this.modoConsumida = aModoConsumida;
    }

    public String getHora() {
        return hora;
    }

    public String getNomeRefeicao() {
        return refeicao;
    }

    public String getInformacaoRefeicao() {
        return informacaoRefeicao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public String getHoraRealizada() {
        return horaRealizada;
    }

    public String getObservacao() {
        return observacao;
    }

    public String getModoConsumida() {
        return modoConsumida;
    }

    public String toString() {
        return  this.hora+" "+this.refeicao+"                      "+this.horaRealizada;
    }
}
