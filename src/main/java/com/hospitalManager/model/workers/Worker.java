package com.hospitalManager.model.workers;

public class Worker {

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

    public void Validate() {

    }

    public String getPassword() {
        return password;
    }

    public String getInfo() {
        return String.format("%s [%s] - %s", name, login, getProffesion());
    }

    public String getProffesion() {
        return "";
    }
}


