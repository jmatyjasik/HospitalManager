package com.hospitalManager.console.commands.duties;

import com.hospitalManager.console.commands.ICommand;
import com.hospitalManager.console.utils.Console;
import com.hospitalManager.model.Hospital;
import com.hospitalManager.model.utils.DateUtil;
import com.hospitalManager.model.utils.Result;
import com.hospitalManager.model.workers.DutyWorker;
import com.hospitalManager.model.workers.Worker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListMyDutiesCommand implements ICommand {

    private Worker loggedWorker;

    @Override
    public String getName() {
        return "Pokaż dyżury";
    }

    @Override
    public boolean canExecute(Worker worker) {
        return worker instanceof DutyWorker;
    }

    @Override
    public void execute(Hospital hospital, Worker loggedWorker) throws IOException {
        this.loggedWorker = loggedWorker;

        DutyWorker dw = askForDutyWorker(hospital);

        if (dw.getDuties().isEmpty()){
            Console.info("Brak dyzurów");
        }
        else {
            Console.info("Lista dyżurów: ");
            for (Date duty: dw.getDuties()) {
                Console.info(String.format("\t- %s", DateUtil.toString(duty)));
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
                dw = (DutyWorker) this.loggedWorker;
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
