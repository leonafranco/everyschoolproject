package pt.ipc.estgoh.loja.entities;

import java.util.Date;

public class Equipamento {

    private String marca;
    private String modelo;
    private int sku;
    private Date dataDeFabrico;
    private int lote;
    private Date dataDeSubmissao;
    private Date dataDeReparacao;

    public Equipamento(String marca, String modelo, int sku, Date dataDeFabrico, int lote, Date dataDeSubmissao, Date dataDeReparacao) {
        this.marca = marca;
        this.modelo = modelo;
        this.sku = sku;
        this.dataDeFabrico = dataDeFabrico;
        this.lote = lote;
        this.dataDeSubmissao = dataDeSubmissao;
        this.dataDeReparacao = dataDeReparacao;
    }


}
