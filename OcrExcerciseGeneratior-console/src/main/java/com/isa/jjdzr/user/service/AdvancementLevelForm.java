package com.isa.jjdzr.user.service;

import com.isa.jjdzr.console.Menu;
import com.isa.jjdzr.console.UserPanel;
import com.isa.jjdzr.interfaces.Printable;
import com.isa.jjdzr.user.model.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdvancementLevelForm {
    static Printable menu = new Menu();
    private static final int beginner = 50;
    private static final int advanced = 100;
    private static final int professional = 150;

    public static void showAdvancementLevelMenu() {
        menu.printActualLine("     ****************************************");
        menu.printActualLine("     *   POZIOM ZAAWANSOWANIA UŻYTKOWNIKA    ");
        menu.printActualLine("     ****************************************");
        menu.printActualLine("Wybierz poziom zaawansowania:\n1. Początkujący (50pkt)\n2. Zaawansowany (100pkt)\n3. Profesjonalny (150pkt)\n4. Wróć\n>>");
    }

    public static int advancementLevelMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        try {
            int optionNumber;
            do {
                showAdvancementLevelMenu();
                optionNumber = scanner.nextInt();
                switch (optionNumber) {
                    case 1 -> {
                        user.setUserAdvancementLevel(beginner);
                        menu.printActualLine("Wybrano poziom: POCZĄTKUJĄCY");
                    }
                    case 2 -> {
                        user.setUserAdvancementLevel(advanced);
                        menu.printActualLine("Wybrano poziom: ZAAWANSOWANY");
                    }
                    case 3 -> {
                        user.setUserAdvancementLevel(professional);
                        menu.printActualLine("Wybrano poziom: PROFESJONALNY");
                    }
                    default -> menu.printActualLine(UserPanel.wrongInput);
                }
            }
            while (optionNumber != 4 && optionNumber != 1 && optionNumber != 2 && optionNumber != 3);
        } catch (InputMismatchException e) {
            menu.printActualLine(UserPanel.wrongInput);
        }
        return user.getUserAdvancementLevel();
    }
}
