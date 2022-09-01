package com.utnfrt.alimentar.data.api.principalAPI.apimodel.menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailMenuResponse {

    @SerializedName("menu")
    @Expose
    private MenuDetail menu;

    public DetailMenuResponse() {}

    public DetailMenuResponse(MenuDetail menu) {
        super();
        this.menu = menu;
    }

    public MenuDetail getMenu() {
        return menu;
    }

    public void setMenu(MenuDetail menu) {
        this.menu = menu;
    }

}
