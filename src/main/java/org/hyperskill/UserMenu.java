package org.hyperskill;

import java.util.Scanner;

public class UserMenu {

    public static final String MENU_COMMAND = "Choose your action:\n" +
            "1) Add income\n" +
            "2) Add purchase\n" +
            "3) Show list of purchases\n" +
            "4) Balance\n" +
            "0) Exit";
    public static final String ENTER_INCOME = "Enter income:";
    public static final String INCOME_ADDED = "Income was added!";
    public static final String BALANCE = "Balance: $%2f";
    public static final String PURCHASE_LIST_EMPTY = "Purchase list is empty";
    public static final String ENTER_PURCHASE_NAME = "Enter purchase name:";
    public static final String ENTER_PURCHASE_PRICE = "Enter its price:";
    public static final String PURCHASE_ADDED = "Purchase was added!";
    public static final String PURCHASE_ENTRY = "%s $%2f";
    public static final String PURCHASE_TOTAL = "Total sum: $%2f";
    public static final String EXIT = "Bye!";
    private static Price balance = Price.FREE;

    public static void manageInput(Scanner scanner){
        String command = "";
        while (UserMenuOptions.EXIT.getValue().equals(command)){
            UserMenuOptions option = UserMenuOptions.getByValue(command);
            switch (option){
                case ADD_INCOME:

                    break;
                case ADD_PURCHASE:

                    break;
                case SHOW_LIST_OF_PURCHASES:

                    break;
                case BALANCE:
                    showBalance();
                    break;
                default:
            }
            command = scanner.nextLine();
            System.out.println(MENU_COMMAND);
        }
        exitConsole();
    }

    private static void exitConsole(){
        System.out.println(EXIT);
        System.exit(0);
    }

    private static void showBalance(){
        System.out.printf(BALANCE, balance.getDecimal());
    }
}
