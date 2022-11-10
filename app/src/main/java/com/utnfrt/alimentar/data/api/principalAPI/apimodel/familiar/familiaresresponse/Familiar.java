package com.utnfrt.alimentar.data.api.principalAPI.apimodel.familiar.familiaresresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.PesoHistoricoFamiliar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Familiar {

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
    private Integer peso;
    @SerializedName("altura")
    @Expose
    private Double altura;
    @SerializedName("afecciones")
    @Expose
    private List<AfeccioneFamiliar> afecciones = null;



    public Familiar() {}

    public Familiar(Integer idFamiliar, String nombre, String fechaNacimiento, Integer peso, Double altura, List<AfeccioneFamiliar> afecciones) {
        super();
        this.idFamiliar = idFamiliar;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.altura = altura;
        this.afecciones = afecciones;
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

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public List<AfeccioneFamiliar> getAfecciones() {
        return afecciones;
    }

    public void setAfecciones(List<AfeccioneFamiliar> afecciones) {
        this.afecciones = afecciones;
    }

}
