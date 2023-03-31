package com.isa.jjdzr;

import com.isa.jjdzr.user.model.User;
import com.isa.jjdzr.user.service.AdvancementLevelCategory;

import java.util.InputMismatchException;
import java.util.Scanner;


public class AdvancementLevelForm {
    static Printable menu = new Menu();

    public static void showAdvancementLevelMenu() {
        menu.printActualLine("     ****************************************");
        menu.printActualLine("     *   POZIOM ZAAWANSOWANIA UŻYTKOWNIKA    ");
        menu.printActualLine("     ****************************************");
        menu.printActualLine("""
                  Wybierz poziom zaawansowania:
                1. Początkujący
                2. Zaawansowany
                3. Profesjonalny
                4. Wróć
                >>""");
    }

    public static AdvancementLevelCategory advancementLevelMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        try {
            int optionNumber;
            do {
                showAdvancementLevelMenu();
                optionNumber = scanner.nextInt();
                switch (optionNumber) {
                    case 1 -> {
                        user.setUserAdvancementLevel(AdvancementLevelCategory.BEGINNER);
                        menu.printActualLine("Wybrano poziom: POCZĄTKUJĄCY");
                    }
                    case 2 -> {
                        user.setUserAdvancementLevel(AdvancementLevelCategory.ADVANCE);
                        menu.printActualLine("Wybrano poziom: ZAAWANSOWANY");
                    }
                    case 3 -> {
                        user.setUserAdvancementLevel(AdvancementLevelCategory.PROFESSIONAL);
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

    public static int convertAdvancementLevel(AdvancementLevelCategory category){
        if(category == null) {
            return 0;
        }
        else if(category.equals(AdvancementLevelCategory.BEGINNER)) {
            return 50;
        } else if (category.equals(AdvancementLevelCategory.ADVANCE)) {
            return 100;
        } else {
            return 150;
        }
    }
}
