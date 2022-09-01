package com.utnfrt.alimentar.data.api.principalAPI;

import com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion.AfeccionFamiliarResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.afeccion.AfeccionesResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.CrearFamiliarResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.DeleteFamiliarResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse.FamiliaresResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.UpdateFamiliarResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.HortalizaAddUsuarioResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.HortalizasResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.hortaliza.hortalizausuarioresponse.HortalizaUsuarioResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.DetailMenuResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu.ListMenuResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.createuserresponse.CreateUsuarioResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.edituserresponse.EditUsuarioResponse;
import com.utnfrt.alimentar.data.api.principalAPI.apimodel.user.loginresponse.LoginResponse;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PrincipalApi {

    /** ---------- ENDPOINTS USUARIO ---------- **/

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @FormUrlEncoded
    @POST("login")
    Observable<LoginResponse> postLogin(@Header("Authorization") String authToken, @Field("nombre") String username, @Field("password") String password);

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @FormUrlEncoded
    @POST("createUsuario")
    Observable<CreateUsuarioResponse> postCreateUser(@Header("Authorization") String authToken, @Field("nombre") String nombre, @Field("password") String password, @Field("email") String email);

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @FormUrlEncoded
    @POST("updateUsuario")
    Observable<EditUsuarioResponse> postEditUser(@Header("Authorization") String authToken, @Field("id_usuario") String idUsuario, @Field("usuario") String usuario, @Field("password") String password, @Field("email") String email);



    /** ---------- ENDPOINTS FAMILIAR ---------- **/

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @FormUrlEncoded
    @POST("getFamiliares")
    Observable<FamiliaresResponse> postObtainFamiliares(@Header("Authorization") String authToken, @Field("id_usuario") String idUsuario);

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @FormUrlEncoded
    @POST("createFamiliar")
    Observable<CrearFamiliarResponse> postCreateFamiliar(@Header("Authorization") String authToken, @Field("id_usuario") String idUsuario, @Field("nombre") String nombre, @Field("fecha_nacimiento") String fecha_nacimiento,
                                                         @Field("peso") String peso, @Field("altura") String altura);

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @FormUrlEncoded
    @POST("updateFamiliar")
    Observable<UpdateFamiliarResponse> postUpdateFamiliar(@Header("Authorization") String authToken, @Field("id_familiar") String idFamiliar, @Field("nombre") String nombre, @Field("fecha_nacimiento") String fecha_nacimiento,
                                                          @Field("peso") String peso, @Field("altura") String altura);

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @FormUrlEncoded
    @POST("deleteFamiliar")
    Observable<DeleteFamiliarResponse> postDeleteFamiliar(@Header("Authorization") String authToken, @Field("id_familiar") String idFamiliar);



    /** ---------- ENDPOINTS AFECCIONES ---------- **/

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @GET("listAfecciones_json")
    Observable<AfeccionesResponse> getAfecciones(@Header("Authorization") String authToken);

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @FormUrlEncoded
    @POST("createFamiliar_Afeccion")
    Observable<AfeccionFamiliarResponse> postAddAfeccionFamiliar(@Header("Authorization") String authToken, @Field("id_familiar") String idFamiliar, @Field("id_afeccion") String idAfeccion);



    /** ----------ENDPOINTS HORTALIZAS ---------- **/

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @GET("listHortalizas_json")
    Observable<HortalizasResponse> getHortalizas(@Header("Authorization") String authToken);

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @FormUrlEncoded
    @POST("getHortalizasByUsuario")
    Observable<List<HortalizaUsuarioResponse>> getHortalizasUser(@Header("Authorization") String authToken, @Field("id_usuario") String idUsuario);

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @FormUrlEncoded
    @POST("createUsuario_Hortaliza")
    Observable<HortalizaAddUsuarioResponse> postAddHortalizaUser(@Header("Authorization") String authToken, @Field("id_usuario") String idUsuario, @Field("id_hortaliza") String idHortaliza, @Field("cantidad") String cantidad);



    /** ---------- ENDPOINTS MENU ---------- **/

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @GET("getMenus")
    Observable<ListMenuResponse> getMenus(@Header("Authorization") String authToken);

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @FormUrlEncoded
    @POST("elegirMenu")
    Observable<List<Integer>> postObtainListMenu(@Header("Authorization") String authToken, @Field("id_usuario") String idUsuario);

    @Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json"})
    @FormUrlEncoded
    @POST("getMenu")
    Observable<DetailMenuResponse> postDetailMenu(@Header("Authorization") String authToken, @Field("id_menu") String idMenu);

}
