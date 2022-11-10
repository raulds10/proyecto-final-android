package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PesoHistorico {
    private String idFamiliar;
    private List<PesoHistoricoFamiliar> pesoHistoricoFamiliar = new ArrayList<>();

    public String getIdFamiliar() {
        return idFamiliar;
    }

    public void setIdFamiliar(String idFamiliar) {
        this.idFamiliar = idFamiliar;
    }

    public List<PesoHistoricoFamiliar> getPesoHistoricoFamiliar() {
        return pesoHistoricoFamiliar;
    }

    public void setPesoHistoricoFamiliar(List<PesoHistoricoFamiliar> pesoHistoricoFamiliar) {
        this.pesoHistoricoFamiliar = pesoHistoricoFamiliar;
    }


    public void agregarPesoHistorico(String peso){
        getPesoHistoricoFamiliar().add(new PesoHistoricoFamiliar(peso,new Date()));
    }
}
