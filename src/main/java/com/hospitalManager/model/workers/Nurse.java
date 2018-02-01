package com.hospitalManager.model.workers;

public class Nurse extends DutyWorker {
    public Nurse(){super();}

    public Nurse(String login, String password, String name) {
        super(login, password, name);
    }

    @Override
    public String getProffesion() {
        return "Pielęgniarka";
    }
}
