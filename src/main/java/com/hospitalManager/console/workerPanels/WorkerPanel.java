package com.hospitalManager.console.workerPanels;

import com.hospitalManager.console.commands.ICommand;
import com.hospitalManager.console.utils.Console;
import com.hospitalManager.model.Hospital;
import com.hospitalManager.model.workers.Worker;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class WorkerPanel{
        private Worker worker;
        private Scanner console;
        private Map<String, ICommand> AllCommand;

        protected WorkerPanel(Worker worker) {
                this.worker = worker;
                console = new Scanner(System.in);
                AllCommand = new HashMap<>();
        }

        public void ShowPanel(Hospital hospital){

                if (AllCommand.isEmpty()){
                        InitializeCommands(AllCommand);
                }

                boolean didNotPressedExit = true;
                do {
                        DisplayMenu();

                        String chosenOption = console.nextLine();

                        if (! AllCommand.containsKey(chosenOption)){
                                Console.warning("Nie rozpoznano komendy: '" + chosenOption+"'");
                        }
                        else {
                                ICommand command = AllCommand.get(chosenOption);
                                if (! command.canExecute(worker))
                                        Console.warning("Brak wystarczających uprawnień do wykonania operacji");
                                else
                                        command.execute(hospital);
                        }
                }  while (didNotPressedExit);
        }

        protected void DisplayMenu(){
                System.out.flush();
                System.out.println(String.format("Zalogowano jako %s", worker.getInfo()));
                for(Map.Entry<String, ICommand> entry : AllCommand.entrySet()){
                        System.out.println(String.format("%s - %s", entry.getKey(), entry.getValue().getName()));
                }
                System.out.println(String.format("Wybierz opcję [%s]", String.join(", ", AllCommand.keySet())));
                System.out.println();
        }

        protected abstract void InitializeCommands(Map<String, ICommand> allCommand);
}

