package com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListMenuResponse {

    @SerializedName("menus")
    @Expose
    private List<Menu> menus = null;

    public ListMenuResponse() {}

    public ListMenuResponse(List<Menu> menus) {
        super();
        this.menus = menus;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}