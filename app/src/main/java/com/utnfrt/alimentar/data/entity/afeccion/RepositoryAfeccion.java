package com.utnfrt.alimentar.data.entity.afeccion;


import com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion.AfeccionFamiliarResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion.AfeccionesResponse;
import io.reactivex.Observable;

public interface RepositoryAfeccion {

    Observable<AfeccionesResponse> obtainAfecciones();
    Observable<AfeccionFamiliarResponse> addAfeccionToFamiliar(String idFamiliar, String idAfeccion);

}
