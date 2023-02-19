package com.isa.jjdzr;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    static Scanner in = new Scanner(System.in);

    static void nowyUzytkownik() {
        UserSignUp.createUser();
    }

    static void logowanieUzytkownika() {
        UserSignInAndLogIn.login();
    }

    static void bezLogowania() {
        System.out.println("Wybrano opcję bez zalogowania");
    }

    static void pokazMenu() {
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");

        System.out.print("Wybierz opcje:\n1. Tworzenie użytkownika\n2. Logowanie\n3. Bez zalogowania\n4. Koniec\n>> ");
    }

    static void menu() {
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
    public static void main(String[] args) {
        JavaFanaticsTerminalLogo.javaFanaticsLogo();
        menu();
    }
}