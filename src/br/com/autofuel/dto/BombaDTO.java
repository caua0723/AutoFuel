
package br.com.autofuel.dto;

public class BombaDTO {

    public int getId_bom() {
        return id_bom;
    }

    public void setId_bom(int id_bom) {
        this.id_bom = id_bom;
    }

    public String getDescricao_bom() {
        return descricao_bom;
    }

    public void setDescricao_bom(String descricao_bom) {
        this.descricao_bom = descricao_bom;
    }

    public String getTipo_comb_bom() {
        return tipo_comb_bom;
    }

    public void setTipo_comb_bom(String tipo_comb_bom) {
        this.tipo_comb_bom = tipo_comb_bom;
    }

    public boolean isAtiva_bom() {
        return ativa_bom;
    }

    public void setAtiva_bom(boolean ativa_bom) {
        this.ativa_bom = ativa_bom;
    }
    
    
    private int     id_bom;
    private String  descricao_bom;
    private String  tipo_comb_bom;
    private boolean ativa_bom ; 
    private double preco_litro_bom;    

    public double getPreco_litro_bom() {
        return preco_litro_bom;
    }

    public void setPreco_litro_bom(double preco_litro_bom) {
        this.preco_litro_bom = preco_litro_bom;
    }

}

   

