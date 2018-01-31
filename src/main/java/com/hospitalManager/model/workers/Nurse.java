package com.hospitalManager.model.workers;

public class Nurse extends Worker {
    public Nurse(String login, String password, String name) {
        super(login, password, name);
    }

    @Override
    public String getProffesion() {
        return "Pielęgniarka";
    }
}
