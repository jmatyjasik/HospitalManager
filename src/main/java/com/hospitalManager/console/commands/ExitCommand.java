package com.hospitalManager.console.commands;

import com.hospitalManager.model.Hospital;
import com.hospitalManager.model.workers.Worker;

public class ExitCommand implements ICommand{

    @Override
    public String getName() {
        return "Wyjdź z aplikacji";
    }

    @Override
    public boolean canExecute(Worker worker) {
        return true;
    }

    @Override
    public void execute(Hospital hospital) {
        //Save hospital in file
    }
}

