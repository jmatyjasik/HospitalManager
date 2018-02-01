package com.hospitalManager.model.workers;

import com.hospitalManager.model.utils.DateUtil;
import com.hospitalManager.model.utils.Result;

import java.util.ArrayList;
import java.util.Date;


public class DutyWorker extends Worker{
    private ArrayList<Date> duties;

    public DutyWorker() {
        super();
        this.duties = new ArrayList<>();
    }

    public DutyWorker(String login, String password, String name) {
        super(login, password, name);
        this.duties = new ArrayList<>();
    }

    @Override
    public String getProffesion() {
        return "";
    }

    public ArrayList<Date> getDuties() {
        return duties;
    }

    public Result addDuty(Date dutyDate) {
        if (duties.contains(dutyDate))
            return Result.error("Pracownik ma już dyżur tego dnia");

        if (duties.contains(DateUtil.addDays(dutyDate, 1))){
            return Result.error("Pracownik ma już dyżur następnego dnia");
        }

        if (duties.contains(DateUtil.addDays(dutyDate, -1))){
            return Result.error("Pracownik ma już dyżur poprzedniego dnia");
        }

        long dutiesInTheMonth = duties.stream().filter(i->DateUtil.getMonth(i) == DateUtil.getMonth(dutyDate)).count();
        if (dutiesInTheMonth>=10){
            return Result.error("Pracownik nie może mieć więcej niż 10 dyzurów w miesiącu");
        }

        duties.add(dutyDate);
        return Result.ok();
    }
}
