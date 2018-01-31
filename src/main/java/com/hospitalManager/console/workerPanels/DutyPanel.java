package com.hospitalManager.console.workerPanels;

import com.hospitalManager.console.commands.ICommand;
import com.hospitalManager.model.workers.Worker;

import java.util.Map;

public class DutyPanel extends WorkerPanel{

        public DutyPanel(Worker worker) {
                super(worker);
        }

        @Override
        protected void InitializeCommands(Map<String, ICommand> allCommand) {

        }
}
