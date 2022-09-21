package com.example.tp1;

import java.io.Serializable;
import java.util.Date;

/**
 * A classe PlanoAlimentar vai representar o objeto de novos planos inseridos
 * na aplicação, deste modo é contituida por os atributos que caracterizam
 * o objeto Plano Alimentar.
 *
 * @version 0.4
 */
public class PlanoAlimentar implements Serializable {

    private String hora;
    private String refeicao;
    private String informacaoRefeicao;

    public PlanoAlimentar(String aRefeicao, String aInformacaoRefeicao, String aHora) {
        this.refeicao = aRefeicao;
        this.informacaoRefeicao = aInformacaoRefeicao;
        this.hora = aHora;
    }

    public String getHora() {
        return this.hora;
    }

    public String getRefeicao() {
        return this.refeicao;
    }

    public String getInformacaoRefeicao() {
        return this.informacaoRefeicao;
    }


    public String toString() {

        return  this.hora+" "+this.refeicao;
    }
}
