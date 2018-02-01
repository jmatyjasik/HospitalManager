package com.hospitalManager.console.commands;

import com.hospitalManager.console.utils.Console;
import com.hospitalManager.model.Hospital;
import com.hospitalManager.model.utils.Result;
import com.hospitalManager.model.workers.DutyWorker;
import com.hospitalManager.model.workers.Worker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListMyDutiesCommand implements ICommand{

    private DutyWorker dutyWorker;

    @Override
    public String getName() {
        return "Pokaż dyżury";
    }

    @Override
    public boolean canExecute(Worker worker) {
        boolean result = worker instanceof DutyWorker;
        if (result){
            this.dutyWorker = (DutyWorker) worker;
        }

        return result;
    }

    @Override
    public void execute(Hospital hospital) throws IOException {

        DutyWorker dw = askForDutyWorker(hospital);

        if (dw.getDuties().isEmpty()){
            Console.info("Brak dyzurów");
        }
        else {
            Console.info("Lista dyżurów: ");
            for (Date duty: dw.getDuties()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(duty);
                Console.info(String.format("\t- %s", date));
            }
        }
    }

    private DutyWorker askForDutyWorker(Hospital hospital) {
        DutyWorker dw = null;
        boolean repeatAction = false;
        do {
            Console.question("Podaj login użytkownika, dla kórgo chcesz wyświetlić dyżury, naciśnij <ENTER>, żeby wyświetlić swoje");
            String login = Console.readLine();
            if (login.isEmpty())
            {
                dw = this.dutyWorker;
            }
            else{
                Result<DutyWorker> result = hospital.getDutyWorker(login);
                if (result.isFailed()){
                    Console.warning(result.getMessage());
                    repeatAction = true;
                }
                else {
                    dw = result.getValue();
                    repeatAction = false;
                }
            }

        }while (repeatAction);

        return dw;
    }
}
