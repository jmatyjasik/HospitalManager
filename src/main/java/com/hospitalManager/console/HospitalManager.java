package com.hospitalManager.console;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospitalManager.console.utils.Console;
import com.hospitalManager.console.workerPanels.AdminPanel;
import com.hospitalManager.console.workerPanels.DutyPanel;
import com.hospitalManager.console.workerPanels.WorkerPanel;
import com.hospitalManager.model.Hospital;
import com.hospitalManager.model.utils.Result;
import com.hospitalManager.model.workers.Administrator;
import com.hospitalManager.model.workers.Worker;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class HospitalManager {

    private Hospital hospital;

    public HospitalManager() {

        String path = "target/szpital.json";

        if (Files.exists(Paths.get(path))) {
            Console.info("Pobieram baze z dysku");
            this.hospital = LoadFromDisk(path);
        } else {
            Console.info("Nie znaleziono bazy, tworzę nową");
            hospital = new Hospital();
            Worker admin = new Administrator("admin", "admin", "Jan Kowalski");
            hospital.addWorker(admin);
        }
    }

    private Hospital LoadFromDisk(String path) {
        Hospital hospital;
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        try {

            String str = FileUtils.readFileToString(new File(path) , "utf-8");

            hospital = mapper.reader().forType(Hospital.class).readValue(str);

           // List<Hospital> users = mapper.reader()
           //         .forType(new TypeReference<List<Hospital>>() {})
           //         .readValue(str);


            return hospital;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


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
            if (loginResult.isFailed())
                Console.warning(loginResult.getMessage());
            System.out.println();

        } while (loginResult.isFailed());

        return loginResult.getValue();
    }
}
