package com.utnfrt.alimentar.data.entity.hortaliza;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.HortalizaAddUsuarioResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.HortalizasResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.hortalizausuarioresponse.HortalizaUsuarioResponse;
import java.util.List;
import io.reactivex.Observable;

public interface RepositoryHortaliza {

    Observable<HortalizasResponse> obtainHortalizas();
    Observable<List<HortalizaUsuarioResponse>> obtainHortalizaUser();
    Observable<HortalizaAddUsuarioResponse> addhortalizaUser(String idHortaliza, String cantidad);

}
