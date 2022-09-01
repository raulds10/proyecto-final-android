package com.utnfrt.alimentar.data.entity.family;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.CrearFamiliarResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.DeleteFamiliarResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse.FamiliaresResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.UpdateFamiliarResponse;
import com.utnfrt.alimentar.data.entity.user.User;
import io.reactivex.Observable;

public interface RepositoryFamily {
    User obtainUserLocal();
    void logout();
    Observable<FamiliaresResponse> obtainFamiliares();
    Observable<CrearFamiliarResponse> createFamiliar(String fullName, String fachaNac, String peso, String altura);
    Observable<UpdateFamiliarResponse> editFamiliar(String idFamiliar, String fullName, String fachaNac, String peso, String altura);
    Observable<DeleteFamiliarResponse> deleteFamiliar(String idFamiliar);

}
