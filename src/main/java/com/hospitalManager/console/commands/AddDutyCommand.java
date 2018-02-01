package com.hospitalManager.console.commands;

import com.hospitalManager.console.utils.Console;
import com.hospitalManager.model.Hospital;
import com.hospitalManager.model.utils.Result;
import com.hospitalManager.model.workers.Administrator;
import com.hospitalManager.model.workers.DutyWorker;
import com.hospitalManager.model.workers.Worker;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddDutyCommand implements ICommand{

    @Override
    public String getName() {
        return "Dodaj dyżur";
    }

    @Override
    public boolean canExecute(Worker worker) {
        return worker instanceof Administrator;
    }

    @Override
    public void execute(Hospital hospital) throws IOException {
       DutyWorker dutyWorker = askForLogin(hospital);

       boolean repeatAction = false;
       do {
           Date dutyDate = askForDate();

           Result result = hospital.addDuty(dutyWorker, dutyDate);
           if (result.isFailed()){
               Console.warning(result.getMessage());
           }
           else {
               Console.info("Pomyślnie dodano dyżur.");
           }

       } while (repeatAction);
    }

    private Date askForDate() {
        boolean repeatAction = true;
        Date date = null;
        do {
            Console.question("Podaj datę dyżuru");
            String dateString = Console.readLine();

            DateFormat df = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
            try {
                date =  df.parse(dateString);
                repeatAction = false;
            } catch (ParseException e) {
                Console.warning("Nie rozpoznano formatu daty [rrrr-mm-ddd]");
            }
        } while (repeatAction);

        return date;
    }

    private DutyWorker askForLogin(Hospital hospital) {
        boolean repeatAction = true;
        DutyWorker dutyWorker = null;
        do {
            Console.question("Podaj login pracownika");
            String login = Console.readLine();

            Result<DutyWorker> result = hospital.getDutyWorker(login);
            if (result.isFailed()){
                Console.warning(result.getMessage());
            }
            else {
                dutyWorker = result.getValue();
                repeatAction = false;
            }
        } while (repeatAction);

        return dutyWorker;
    }
}
