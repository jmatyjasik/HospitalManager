package com.hospitalManager.console.workerPanels;

import com.hospitalManager.console.commands.ExitCommand;
import com.hospitalManager.console.commands.ICommand;
import com.hospitalManager.console.commands.workers.ListAllWorkersCommand;
import com.hospitalManager.console.commands.duties.ListMyDutiesCommand;
import com.hospitalManager.model.workers.Worker;

import java.util.Map;

public class DutyPanel extends WorkerPanel{

        public DutyPanel(Worker worker) {
                super(worker);
        }

        @Override
        protected void InitializeCommands(Map<String, ICommand> allCommand) {
                allCommand.put("1", new ListAllWorkersCommand());
                allCommand.put("2", new ListMyDutiesCommand());

                allCommand.put("exit", new ExitCommand());
        }
}
