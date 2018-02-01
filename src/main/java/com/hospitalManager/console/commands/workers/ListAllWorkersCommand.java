package com.hospitalManager.console.commands.workers;

import com.hospitalManager.console.commands.ICommand;
import com.hospitalManager.console.utils.Console;
import com.hospitalManager.model.Hospital;
import com.hospitalManager.model.workers.Worker;

public class ListAllWorkersCommand implements ICommand {

    @Override
    public String getName() {
        return "Lista wszystkich pracowników szpitala";
    }

    @Override
    public boolean canExecute(Worker worker) {
        return true;
    }

    @Override
    public void execute(Hospital hospital, Worker loggedWorker) {
        Console.info("Lista wszystkich pracowników szpitala: ");
        for (Worker worker: hospital.getAllWorkers()) {
            Console.info("\t" + worker.getInfo());
        }

        System.out.println();
    }
}

