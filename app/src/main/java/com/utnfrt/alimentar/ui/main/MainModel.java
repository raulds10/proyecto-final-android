package com.utnfrt.alimentar.ui.main;

import com.utnfrt.alimentar.data.entity.user.RepositoryUser;
import com.utnfrt.alimentar.data.entity.user.User;

public class MainModel implements MainContract.Model {

    private RepositoryUser repository;

    public MainModel(RepositoryUser repository){
        this.repository = repository;
    }

    @Override
    public User obtainUser() {
        return repository.obtainUserLocal();
    }
}
