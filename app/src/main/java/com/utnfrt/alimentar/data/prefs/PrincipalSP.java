package com.utnfrt.alimentar.data.prefs;

import android.content.SharedPreferences;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PrincipalSP {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    /**DATOS REGISTRO USUARIO Y PARA CUANDO USUARIO YA ESTA LOGUEADO**/
    private String SHAREDPREFERENCE_TOKEN = "token";
    private String SHAREDPREFERENCE_NOMBRE = "nombre";
    private String SHAREDPREFERENCE_APELLIDO = "apellido";
    private String SHAREDPREFERENCE_PASSWORD = "password";
    private String SHAREDPREFERENCE_EMAIL = "email";
    private String SHAREDPREFERENCE_ID_USUARIO = "idUsuario";


    @Inject
    public PrincipalSP(SharedPreferences mSharedPreferences) {
        this.prefs = mSharedPreferences;
        editor = prefs.edit();
    }

    public void setToken(String value){
        editor.putString(SHAREDPREFERENCE_TOKEN,value);
        editor.commit();
    }
    public String getToken(){
        return prefs.getString(SHAREDPREFERENCE_TOKEN,"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c3VhcmlvIjp7InVzZXIiOiJhYmFuaWNvIn0sImlhdCI6MTU4NzQ5NDg3NSwiZXhwIjoxNTg3NDk0OTk1fQ.KShsOkhjqLrGevSijdTgnH8IknS-o-2eef6sLTXrL08");
    }

    public void setNombre(String value){
        editor.putString(SHAREDPREFERENCE_NOMBRE,value);
        editor.commit();
    }
    public String getNombre(){
        return prefs.getString(SHAREDPREFERENCE_NOMBRE,"");
    }

    public void setApellido(String value){
        editor.putString(SHAREDPREFERENCE_APELLIDO,value);
        editor.commit();
    }
    public String getApellido(){
        return prefs.getString(SHAREDPREFERENCE_APELLIDO,"");
    }

    public void setPassword(String value){
        editor.putString(SHAREDPREFERENCE_PASSWORD,value);
        editor.commit();
    }
    public String getPassword(){
        return prefs.getString(SHAREDPREFERENCE_PASSWORD,"");
    }

    public void setEmail(String value){
        editor.putString(SHAREDPREFERENCE_EMAIL,value);
        editor.commit();
    }
    public String getEmail(){
        return prefs.getString(SHAREDPREFERENCE_EMAIL,"");
    }

    public void setIdUsuario(String value){
        editor.putString(SHAREDPREFERENCE_ID_USUARIO,value);
        editor.commit();
    }
    public String getIdUsuario(){
        return prefs.getString(SHAREDPREFERENCE_ID_USUARIO,"");
    }


    public void limpiarSP(){
        editor.clear();
        editor.commit();
    }
}
