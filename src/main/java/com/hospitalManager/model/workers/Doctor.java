package com.hospitalManager.model.workers;

import com.hospitalManager.model.utils.Result;

import java.util.Date;

public class Doctor extends DutyWorker {
    private String specialization;
    private String number;



    public Doctor() { super(); }

    public Doctor(String login, String password, String name, String specialization, String number) {
        super(login, password, name);
        this.specialization = specialization;
        this.number = number;
    }

    @Override
    public String getProffesion() {
        return "Lekarz";
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public Result addDuty(Date dutyDate) {
        return super.addDuty(dutyDate);
    }
}
