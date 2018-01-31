package com.hospitalManager.console.workerPanels;

import com.hospitalManager.console.commands.AddNewWorkerCommand;
import com.hospitalManager.console.commands.ExitCommand;
import com.hospitalManager.console.commands.ICommand;
import com.hospitalManager.console.commands.ListAllWorkersCommand;
import com.hospitalManager.model.workers.Worker;

import java.util.Map;

public class AdminPanel extends WorkerPanel{
        public AdminPanel(Worker worker) {
                super(worker);
        }

        @Override
        protected void InitializeCommands(Map<String, ICommand> allCommand) {
                allCommand.put("1", new ListAllWorkersCommand());
                allCommand.put("2", new AddNewWorkerCommand());
                allCommand.put("3", new ExitCommand());
        }
}

