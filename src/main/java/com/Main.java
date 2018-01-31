package com;

import com.hospitalManager.console.HospitalManager;
import com.hospitalManager.console.utils.Console;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Console.info("Hospital Manager 1.0 app");

        HospitalManager manager = new HospitalManager();
        manager.Run();

        Console.info("Naciśnij dowolny klawisz, aby zakończyć...");
        Console.readLine();
    }
}
