
package br.com.autofuel.dto;

public class ProdutoDTO {

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getNome_prod() {
        return nome_prod;
    }

    public void setNome_prod(String nome_prod) {
        this.nome_prod = nome_prod;
    }

    public double getPreco_prod() {
        return preco_prod;
    }

    public void setPreco_prod(double preco_prod) {
        this.preco_prod = preco_prod;
    }

    public int getEstoque_prod() {
        return estoque_prod;
    }

    public void setEstoque_prod(int estoque_prod) {
        this.estoque_prod = estoque_prod;
    }
    
    private int    id_prod;
    private String nome_prod;
    private double preco_prod;
    private int    estoque_prod;
    
}
