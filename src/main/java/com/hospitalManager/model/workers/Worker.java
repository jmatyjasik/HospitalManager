package com.hospitalManager.model.workers;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Worker {

    public Worker(){}

    public Worker(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    private String login;
    private String password;
    private String name;

    public String getLogin(){
        return login;
    }
    public String getName(){ return name; }

    public void Validate() {

    }

    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public String getInfo() {
        return String.format("%s [%s] - %s", name, login, getProffesion());
    }

    @JsonIgnore
    public String getProffesion() {
        return "";
    }
}


