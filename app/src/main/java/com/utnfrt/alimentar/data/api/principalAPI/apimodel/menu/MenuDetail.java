package com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuDetail {

    @SerializedName("id_menu")
    @Expose
    private Integer idMenu;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("ingredientes")
    @Expose
    private String ingredientes;
    @SerializedName("preparacion")
    @Expose
    private String preparacion;

    public MenuDetail() {}

    public MenuDetail(Integer idMenu, String nombre, String ingredientes, String preparacion) {
        super();
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.preparacion = preparacion;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

}