package com.utnfrt.alimentar.utils;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse.Familiar;
import com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.PesoHistorico;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class HistorySaver {
    private static final String PESO_HISTORICO = "PESO_HISTORICO";

    public static List<PesoHistorico> getTodosPesosHistoricos() {
        if (Paper.book().contains(PESO_HISTORICO)) {
            Paper.book().write(PESO_HISTORICO, new ArrayList<>());
        }
        return Paper.book().read(PESO_HISTORICO);
    }

    public static void guardarPesosHistoricos(List<PesoHistorico> pesoHistoricos){
        Paper.book().write(PESO_HISTORICO,pesoHistoricos);
    }

    public static void guardarPesoHistorico(String idFamiliar, String peso) {
        PesoHistorico pesoHistorico = buscarPesoPorIdFamiliar(idFamiliar);
        if (pesoHistorico == null) {
            PesoHistorico newPesoHistorico = new PesoHistorico();
            newPesoHistorico.setIdFamiliar(idFamiliar);
            newPesoHistorico.agregarPesoHistorico(peso);
            List<PesoHistorico> pesoHistoricos = getTodosPesosHistoricos();
            pesoHistoricos.add(newPesoHistorico);
            guardarPesosHistoricos(pesoHistoricos);
        } else {
            pesoHistorico.agregarPesoHistorico(peso);
        }
    }

    public static PesoHistorico buscarPesoPorIdFamiliar(String idFamiliar) {
        for (PesoHistorico pesoHistorico : getTodosPesosHistoricos()) {
            if (pesoHistorico.getIdFamiliar().equals(idFamiliar)) return pesoHistorico;
        }
        return null;
    }


}
