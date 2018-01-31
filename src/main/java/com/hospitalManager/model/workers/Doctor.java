package com.hospitalManager.model.workers;

public class Doctor extends Worker {
    private String specialization;
    private String number;

    public Doctor(String login, String password, String name, String specialization, String number) {
        super(login, password, name);
        this.specialization = specialization;
        this.number = number;
    }

    @Override
    public String getProffesion() {
        return "Lekarz";
    }
}
