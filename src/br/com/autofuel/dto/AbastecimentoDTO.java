
package br.com.autofuel.dto;

import java.util.Date;

public class AbastecimentoDTO {

    public int getId_abast() {
        return id_abast;
    }

    public void setId_abast(int id_abast) {
        this.id_abast = id_abast;
    }

    public int getId_bom() {
        return id_bom;
    }

    public void setId_bom(int id_bom) {
        this.id_bom = id_bom;
    }

    public int getId_fun() {
        return id_fun;
    }

    public void setId_fun(int id_fun) {
        this.id_fun = id_fun;
    }

    public String getPlaca_abast() {
        return placa_abast;
    }

    public void setPlaca_abast(String placa_abast) {
        this.placa_abast = placa_abast;
    }

    public double getLitros_abast() {
        return litros_abast;
    }

    public void setLitros_abast(double litros_abast) {
        this.litros_abast = litros_abast;
    }

    public double getVal_litro_abast() {
        return val_litro_abast;
    }

    public void setVal_litro_abast(double val_litro_abast) {
        this.val_litro_abast = val_litro_abast;
    }

    public double getVal_total_abast() {
        return val_total_abast;
    }

    public void setVal_total_abast(double val_total_abast) {
        this.val_total_abast = val_total_abast;
    }

    public Date getDat_abast() {
        return dat_abast;
    }

    public void setDat_abast(Date dat_abast) {
        this.dat_abast = dat_abast;
    }
    
    private int    id_abast;
    private int    id_bom;
    private int    id_fun;
    private String placa_abast;
    private double litros_abast;
    private double val_litro_abast;
    private double val_total_abast;
    private Date   dat_abast;
    
}
