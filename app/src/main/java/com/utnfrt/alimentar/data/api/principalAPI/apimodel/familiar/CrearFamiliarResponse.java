package com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CrearFamiliarResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("familiar")
    @Expose
    private FamiliarCrear familiar;

    public CrearFamiliarResponse() {}

    public CrearFamiliarResponse(String status, String message, FamiliarCrear familiar) {
        super();
        this.status = status;
        this.message = message;
        this.familiar = familiar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FamiliarCrear getFamiliar() {
        return familiar;
    }

    public void setFamiliar(FamiliarCrear familiar) {
        this.familiar = familiar;
    }

}
class FamiliarCrear {

    @SerializedName("id_familiar")
    @Expose
    private Integer idFamiliar;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("fecha_nacimiento")
    @Expose
    private String fechaNacimiento;
    @SerializedName("peso")
    @Expose
    private String peso;
    @SerializedName("altura")
    @Expose
    private String altura;
    @SerializedName("id_usuario")
    @Expose
    private String idUsuario;

    public FamiliarCrear() {}

    public FamiliarCrear(Integer idFamiliar, String nombre, String fechaNacimiento, String peso, String altura, String idUsuario) {
        super();
        this.idFamiliar = idFamiliar;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.altura = altura;
        this.idUsuario = idUsuario;
    }

    public Integer getIdFamiliar() {
        return idFamiliar;
    }

    public void setIdFamiliar(Integer idFamiliar) {
        this.idFamiliar = idFamiliar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

}