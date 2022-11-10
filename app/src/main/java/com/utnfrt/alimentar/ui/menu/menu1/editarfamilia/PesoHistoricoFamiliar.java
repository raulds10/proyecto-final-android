package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia;

import java.util.Date;

public class PesoHistoricoFamiliar {
    private String peso;
    private Date ultimaFecha;


    public PesoHistoricoFamiliar( String peso, Date ultimaFecha) {
        this.peso = peso;
        this.ultimaFecha = ultimaFecha;
    }


    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public Date getUltimaFecha() {
        return ultimaFecha;
    }

    public void setUltimaFecha(Date ultimaFecha) {
        this.ultimaFecha = ultimaFecha;
    }

}
