package com.hospitalManager.console.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospitalManager.console.utils.Console;
import com.hospitalManager.model.Hospital;
import com.hospitalManager.model.workers.Worker;

import java.io.*;

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
    public void execute(Hospital hospital, Worker loggedWorker){

        Console.info("Zapisywanie stanu aplikacjoi...");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        try {
            String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hospital);
            PrintStream out = new PrintStream(new FileOutputStream("target/szpital.json"));

            out.print(jsonResult);

            Console.info("Pomyslnie zapisano stan");
        } catch (Exception e) {
            e.printStackTrace();
            Console.error("Błąd: " + e.getMessage());
        }


    }
}

