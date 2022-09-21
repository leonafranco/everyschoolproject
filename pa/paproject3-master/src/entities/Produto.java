package entities;

import java.util.Date;

public class Produto {

    private String designacao;
    private String fabricante;
    private int peso;
    private float preco;
    private long SKU;
    private long lote;
    private Date dataprod;
    private Date dataValidade;
    private long stock;
    private String categoria;
    private String classificacao;

    /**
     * Construtor padrao para a criacao de um novo Produto
     * @param designacao
     * @param fabricante
     * @param peso
     * @param preco
     * @param SKU
     * @param lote
     * @param dataprod
     * @param dataValidade
     * @param stock
     * @param categoria
     * @param classificacao
     */

    public Produto(String designacao, String fabricante, int peso, float preco, long SKU, long lote, Date dataprod, Date dataValidade, long stock, String categoria, String classificacao) {
        this.designacao = designacao;
        this.fabricante = fabricante;
        this.peso = peso;
        this.preco = preco;
        this.SKU = SKU;
        this.lote = lote;
        this.dataprod = dataprod;
        this.dataValidade = dataValidade;
        this.stock = stock;
        this.categoria = categoria;
        this.classificacao = classificacao;
    }

    /**
     * Metodo Getter para aceder a variavel Categoria privada
     * @return Categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Metodo Getter para aceder a variavel Classificacao privada
     * @return classificacao
     */

    public String getClassificacao() {
        return classificacao;
    }

    /**
     * Metodo Getter para aceder a variavel Designacao privada
     * @return Designacao
     */

    public String getDesignacao() {
        return designacao;
    }
    /**
     * Metodo Getter para aceder a variavel Fabricante privada
     * @return Fabricante
     */

    public String getFabricante() {
        return fabricante;
    }

    /**
     * Metodo Getter para aceder a variavel Peso privada
     * @return peso
     */

    public int getPeso() {
        return peso;
    }

    /**
     * Metodo Getter para aceder a variavel preco privada
     * @return preco
     */

    public float getPreco() {
        return preco;
    }

    /**
     * Metodo Getter para aceder a variavel SKU privada
     * @return SKU
     */

    public long getSKU() {
        return SKU;
    }
    /**
     * Metodo Getter para aceder a variavel Lote privada
     * @return lote
     */

    public long getLote() {
        return lote;
    }
    /**
     * Metodo Getter para aceder a variavel Data de producao privada
     * @return dataprod
     */

    public Date getDataprod() {
        return dataprod;
    }
    /**
     * Metodo Getter para aceder a variavel Data de Validade privada
     * @return dataValidade
     */

    public Date getDataValidade() {
        return dataValidade;
    }

    /**
     * Metodo Getter para aceder a variavel Stock privada
     * @return Stock
     */

    public long getStock() {
        return stock;
    }
}
