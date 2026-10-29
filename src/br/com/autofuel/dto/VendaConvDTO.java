
package br.com.autofuel.dto;

import java.util.Date;

public class VendaConvDTO {

    public int getId_vend() {
        return id_vend;
    }

    public void setId_vend(int id_vend) {
        this.id_vend = id_vend;
    }

    public int getId_fun() {
        return id_fun;
    }

    public void setId_fun(int id_fun) {
        this.id_fun = id_fun;
    }

    public double getVal_total_vend() {
        return val_total_vend;
    }

    public void setVal_total_vend(double val_total_vend) {
        this.val_total_vend = val_total_vend;
    }

    public Date getDat_vend() {
        return dat_vend;
    }

    public void setDat_vend(Date dat_vend) {
        this.dat_vend = dat_vend;
    }
    
    private int    id_vend;
    private int    id_fun;
    private double val_total_vend;
    private Date   dat_vend;
    
}
