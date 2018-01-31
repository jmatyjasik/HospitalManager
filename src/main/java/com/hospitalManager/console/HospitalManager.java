package com.hospitalManager.console;

import com.hospitalManager.console.utils.Console;
import com.hospitalManager.console.workerPanels.AdminPanel;
import com.hospitalManager.console.workerPanels.DutyPanel;
import com.hospitalManager.console.workerPanels.WorkerPanel;
import com.hospitalManager.model.Hospital;
import com.hospitalManager.model.utils.Result;
import com.hospitalManager.model.workers.Administrator;
import com.hospitalManager.model.workers.Worker;

import javax.print.Doc;

public class HospitalManager {

    private Hospital hospital;

    public HospitalManager() {
        hospital = new Hospital();

        Worker admin = new Administrator("admin", "admin", "Jan Kowalski");
        hospital.addWorker(admin);
    }

    public HospitalManager(Hospital hospital) {
        this.hospital = hospital;
    }

    public void Run() {
        Worker loggedWorker = Login();

        WorkerPanel workerPanel = getPanel(loggedWorker);
        workerPanel.ShowPanel(this.hospital);
    }

    private WorkerPanel getPanel(Worker loggedWorker){
        WorkerPanel workerPanel;
        if (loggedWorker instanceof Administrator){
            workerPanel = new AdminPanel(loggedWorker);
        }
        else {
            workerPanel = new DutyPanel(loggedWorker);
        }
        return workerPanel;
    }

    private Worker Login() {

        Console.info("Logowanie do systemu");
        Result<Worker> loginResult;

        do {
            Console.info("Podaj login: ");
            String login = Console.readLine();

            Console.info("Podaj hasło: ");
            String password = Console.readLine();


            loginResult = hospital.login(login, password);
            if (loginResult.isFalse())
                Console.warning(loginResult.getMessage());
            System.out.println();

        } while (loginResult.isFalse());

        return loginResult.getValue();
    }
}
