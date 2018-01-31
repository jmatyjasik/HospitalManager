package com.hospitalManager.model.workers;

public class Administrator extends Worker {
    public Administrator(String login, String password, String name) {
        super(login, password, name);
    }

    @Override
    public String getProffesion() {
        return "Administrator";
    }
}
