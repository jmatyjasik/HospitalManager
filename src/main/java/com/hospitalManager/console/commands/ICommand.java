package com.hospitalManager.console.commands;

import com.hospitalManager.model.Hospital;
import com.hospitalManager.model.workers.Worker;

public interface ICommand {
    String getName();

    boolean canExecute(Worker worker);

    void execute(Hospital hospital);
}

