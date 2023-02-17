package com.isa.jjdzr;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdvancementLevelForm {
    static Scanner scanner = new Scanner(System.in);
    Integer beginner = 50;
    Integer advanced = 100;
    Integer professional = 150;
    private Integer userAdvancementLevel = 0;

    public Integer getUserAdvancementLevel() {
        return userAdvancementLevel;
    }

    public void setUserAdvancementLevel(Integer userAdvancementLevel) {
        this.userAdvancementLevel = userAdvancementLevel;
    }

    static void showAdvancementLevelMenu() {
        System.out.println("     ****************************************");
        System.out.println("     *   POZIOM ZAAWANSOWANIA UŻYTKOWNIKA    ");
        System.out.println("     ****************************************");
        System.out.print("Wybierz poziom zaawansowania:\n1. Początkujący (50pkt)\n2. Zaawansowany (100pkt)\n3. Profesjonalny (150pkt)\n4. Wróć\n>>");
    }

    void advancementLevelMenu() {
        try {
            int optionNumber;
            do {
                showAdvancementLevelMenu();
                optionNumber = scanner.nextInt();
                switch (optionNumber) {
                    case 1 -> {
                        setUserAdvancementLevel(beginner);
                        System.out.println("Wybrano poziom: POCZĄTKUJĄCY");
                    }
                    case 2 -> {
                        setUserAdvancementLevel(advanced);
                        System.out.println("Wybrano poziom: ZAAWANSOWANY");
                    }
                    case 3 -> {
                        setUserAdvancementLevel(professional);
                        System.out.println("Wybrano poziom: PROFESJONALNY");
                    }
                    default -> System.out.println("Nieznana opcja");
                }
            }
            while (optionNumber != 4 && optionNumber != 1 && optionNumber != 2 && optionNumber != 3);
        } catch (InputMismatchException e) {
            System.out.println("Niepoprawna opcja");
        }
    }
}
