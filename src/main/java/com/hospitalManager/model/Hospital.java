package com.hospitalManager.model;

import com.hospitalManager.model.utils.Result;
import com.hospitalManager.model.workers.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Hospital {

    private List<Worker> allWorkers;

    public Hospital() {
        allWorkers = new ArrayList<Worker>();
    }

    public Result addWorker(Worker worker) {

        worker.Validate();

        if (isLoginAlreadyExists(worker.getLogin())){
            return Result.error("Użytkownik już istnieje");
        }

        allWorkers.add(worker);
        return Result.ok();
    }

    public Worker getWorker(String login){
        Stream<Worker> stream = allWorkers.stream();
        Optional<Worker> worker = stream.filter(i->i.getLogin().equalsIgnoreCase(login)).findFirst();

        return worker.orElse(null);
    }

    public Result<Worker> login(String login, String password){
        Worker worker = getWorker(login);

        if (worker == null){
            return Result.error("Brak użytkownika o loginie " + login);
        }

        if (worker.getPassword().equals(password))
            return Result.okWithResult(worker);
        else
            return Result.error("Nieprawidłowe hasło");
    }

    public List<Worker> getAllWorkers() {
        return allWorkers;
    }

    public void setAllWorkers(List<Worker> allWorkers) {
        this.allWorkers = allWorkers;
    }

    public boolean isLoginAlreadyExists(String login) {
        return allWorkers.stream().anyMatch(i->i.getLogin().equals(login));
    }
}
