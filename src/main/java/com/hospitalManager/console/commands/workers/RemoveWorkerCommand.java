package com.hospitalManager.console.commands.workers;

import com.hospitalManager.console.commands.ICommand;
import com.hospitalManager.console.utils.Console;
import com.hospitalManager.model.Hospital;
import com.hospitalManager.model.utils.Result;
import com.hospitalManager.model.workers.Administrator;
import com.hospitalManager.model.workers.DutyWorker;
import com.hospitalManager.model.workers.Worker;
import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;

import java.io.IOException;

public class RemoveWorkerCommand implements ICommand {

    private Worker loggedWorker;

    @Override
    public String getName() {
        return "Usuń pracownika";
    }

    @Override
    public boolean canExecute(Worker worker) {
        return worker instanceof Administrator;
    }

    @Override
    public void execute(Hospital hospital, Worker loggedWorker) throws IOException {
        this.loggedWorker = loggedWorker;

        Worker worker = askForLogin(hospital);

        hospital.removeWorker(worker);
        Console.info("Pomyślnie usunięto pracownika z bazy");
    }

    private Worker askForLogin(Hospital hospital) {
        boolean repeatAction = true;
        Worker worker = null;
        do {
            Console.question("Podaj login pracownika do usunięcia");
            String login = Console.readLine();

            if (login.equals(this.loggedWorker)){
                Console.warning("Nie możesz usunąć samego siebie");
                repeatAction = true;
            }
            else {
                Worker foundWorker = hospital.getWorker(login);
                if (foundWorker == null){
                    Console.warning("Nie znaleziono pracownika o podanym loginie");
                    repeatAction = true;
                }
                else    {
                    worker = foundWorker;
                    repeatAction = false;
                }
            }
        } while (repeatAction);

        return worker;
    }
}
