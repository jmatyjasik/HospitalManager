package com.hospitalManager.console.commands;

import com.hospitalManager.console.utils.Console;
import com.hospitalManager.model.Hospital;
import com.hospitalManager.model.workers.Administrator;
import com.hospitalManager.model.workers.Doctor;
import com.hospitalManager.model.workers.Nurse;
import com.hospitalManager.model.workers.Worker;

public class AddNewWorkerCommand implements ICommand{

    @Override
    public String getName() {
        return "Dodaj nowego pracownika";
    }

    @Override
    public boolean canExecute(Worker worker) {
        return true;
    }

    @Override
    public void execute(Hospital hospital) {
        Console.info("Dodawanie nego pracownika:");

        String name = askForProp("Imie i nazwisko");
        String login = askForLogin(hospital);
        String password = askForProp("hasło");

        Worker worker = null;
        boolean objectWasCreated = true;
        do {
            int proffesion = askForProffesion();
            switch (proffesion){
                case 1:
                    worker = new Administrator(login, password, name);
                    break;

                case  2:
                    worker = new Nurse(login, password, name);
                    break;

                case 3:
                    String specialization = askForProp("Specjalizacja");
                    String number = askForProp("Numer PZW");
                    worker = new Doctor(login, password, name, specialization, number);
                    break;

                default:
                    Console.warning("Nie rozpoznano zawodu [1-3]");
                    objectWasCreated = false;
                    break;
            }
        } while (! objectWasCreated);

        hospital.addWorker(worker);
        Console.info("Pracownik został pomyślnie dodany do bazy");

        System.out.println();
    }

    private int askForProffesion() {
        Console.info("Podaj zawód:");
        Console.info("--------------------");
        Console.info("[1] - Admin");
        Console.info("[2] - Pielęgniarka");
        Console.info("[3] - Lekarz");

        return Console.readInt();
    }

    private String askForLogin(Hospital hospital) {
        String login = "";
        boolean loginIsOk = false;
        do {
            login = askForProp("login");
            loginIsOk = ! hospital.isLoginAlreadyExists(login);
            if (!loginIsOk){
                Console.warning("Login jest już zajęty");
            }

        } while (! loginIsOk);

        return login;
    }

    private String askForProp(String propName){
        Console.info(String.format("Podsj %s:", propName));
        return Console.readLine();
    }
}
