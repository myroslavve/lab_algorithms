package org.verstyukhnutov.kmateam.utils;

public class Debug {
    public static void error(Object obj) {
        System.out.println(ConsoleColor.RED + obj.toString() + ConsoleColor.RESET);
    }

    public static void error(String msg) {
        System.out.println(ConsoleColor.RED + msg + ConsoleColor.RESET);
    }

    public static void info(Object obj) {
        System.out.println(ConsoleColor.GREEN + obj.toString() + ConsoleColor.RESET);
    }

    public static void info(String msg) {
        System.out.println(ConsoleColor.GREEN + msg + ConsoleColor.RESET);
    }
}