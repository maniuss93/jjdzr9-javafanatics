package com.isa.jjdzr.console;

import com.isa.jjdzr.UserSignInAndLogIn;
import com.isa.jjdzr.UserSignUp;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu implements Printable {
    static Scanner in = new Scanner(System.in);

    static void nowyUzytkownik() {
        UserSignUp.createUser();
    }

    static void logowanieUzytkownika() {
        UserSignInAndLogIn.login();
    }

    static void bezLogowania() {
        System.out.println("Dostępne wkrótce");
    }

    static void pokazMenu() {
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");

        System.out.print("Wybierz opcje:\n1. Tworzenie użytkownika\n2. Logowanie\n3. Bez zalogowania\n4. Koniec\n>> ");
    }

    public static void menu() {
        int nrOpcji = 0;
        while (nrOpcji != 4) {
            try {
                pokazMenu();
                nrOpcji = in.nextInt();
                switch (nrOpcji) {
                    case 1 -> nowyUzytkownik();
                    case 2 -> logowanieUzytkownika();
                    case 3 -> bezLogowania();
                    case 4 -> System.out.println("Koniec programu");
                    default -> System.out.println("Niepoprawna opcja");
                }
            } catch (InputMismatchException e) {
                System.out.println("Niepoprawna opcja");
                in.nextLine();
            }
        }
    }

    @Override
    public void printActualLine(String line) {
        System.out.println(line);
    }

    @Override
    public void printUserNameInBrackes(String line) {
        System.out.println("Utworzono użytkownika '" + line + "'");
    }

    @Override
    public void printExerciseName(String line) {
        System.out.println("\nNowe ćwiczenie: " + line + ", zostało dodane :)");
    }

    @Override
    public void printExercise(String line1, String line2, String line3) {
        System.out.println("Kategoria: " + line1 + " \nNazwa ćwiczenia: " + line2 + "\nOpis:  " + line3 + "\n");
    }
}