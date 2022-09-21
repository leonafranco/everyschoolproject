package entities;

import java.util.Date;
import java.util.Vector;

public class Encomenda {

    private String identificador;
    private float custoTotal;
    private Date dataCriacao;
    private Date dataAceitacao;
    private Date dataEntrega;
    private String estado;
    private Vector <Produto> produto;

    /**
     * Construtor padrao a criacao de uma nova encomenda
     * @param identificador
     * @param dataCriacao
     */
    public Encomenda(String identificador,  Date dataCriacao) {
        this.identificador = identificador;
        this.custoTotal = 0;
        this.dataCriacao = dataCriacao;
        this.dataAceitacao = null;
        this.dataEntrega = null;
        this.estado = "iniciada";
        this.produto = null;
    }

    /**
     * Metodo publico para aceder variavel privada de identificador
     * @return identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Metodo public para aceder variavel privada de Custo
     * @return Custo
     */
    public float getCustoTotal() {
        return custoTotal;
    }

    /**
     * Metodo publico para aceder variavel de variavel privada data criacao
     * @return dataCriacao
     */

    public Date getDataCriacao() {
        return dataCriacao;
    }

    /**
     * Metodo publico para aceder variavel priavada de estado
     * @return
     */
    public String getEstado() {
        return estado;
    }



}
