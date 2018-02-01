package com.hospitalManager.console.utils;

import com.hospitalManager.model.utils.ConsoleColors;

import java.util.Scanner;

public class Console {
    public static void info(String message){
        System.out.println(ConsoleColors.BLUE + message + ConsoleColors.RESET);
    }

    public static void warning(String message){
        System.out.println(ConsoleColors.YELLOW + message + ConsoleColors.RESET);
    }

    public static void error(String message){
        System.out.println(ConsoleColors.RED + message + ConsoleColors.RESET);
    }

    public static String readLine(){
       Scanner console = new Scanner(System.in);
       return console.nextLine();
    }

    public static int readInt(){
        Scanner console = new Scanner(System.in);
        return console.nextInt();
    }

    public static void emptyLine() {
        System.out.println();
    }

    public static void question(String question) {
        System.out.println(ConsoleColors.CYAN + question + ConsoleColors.RESET);
    }

    public static void normal(String text) {
        System.out.println(text);
    }
}
