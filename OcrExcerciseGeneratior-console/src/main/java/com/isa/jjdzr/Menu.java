package com.isa.jjdzr;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Menu
{
    static Scanner in = new Scanner( System.in );

    static void nowyUzytkownik()
    {
        System.out.println( "Wybrano tworzenie nowego użytkownika" );
    }

    static void logowanieUzytkownika()
    {
        System.out.println( "Wybrano opcję zalogowania" );
    }
    static void bezLogowania()
    {
        System.out.println( "Wybrano opcję bez zalogowania" );
    }
    static void pokazMenu()
    {
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");

        System.out.print( "Wybierz opcje:\n1. Tworzenie użytkownika\n2. Logowanie\n3. Bez zalogowania\n4. Koniec\n>> " );
    }

    static void menu()
    {
        try {
        int nrOpcji;
        do {
            pokazMenu();
            nrOpcji = in.nextInt();
            switch (nrOpcji) {
                case 1:
                    nowyUzytkownik();
                    break;
                case 2:
                    logowanieUzytkownika();
                    break;
                case 3:
                    bezLogowania();
                    break;


            }
        }

        while( nrOpcji != 4 );

            } catch (InputMismatchException e) {
            System.out.println("Niepoprawna opcja");
        }

    }

    public static void main(String[] args)
    {
        menu();
    }
}