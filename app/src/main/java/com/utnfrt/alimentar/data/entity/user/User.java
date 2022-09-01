package com.utnfrt.alimentar.data.entity.user;

public class User {

    private String nombres;
    private String email;
    private String password;
    private String userId;

    public User(){}

    public User(String nombres, String email, String password, String userId) {
        this.nombres = nombres;
        this.email = email;
        this.password = password;
        this.userId = userId;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
