package org.hyperskill;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UserMenu {

    public static final String MENU_COMMAND = "Choose your action:\n" +
            "1) Add income\n" +
            "2) Add purchase\n" +
            "3) Show list of purchases\n" +
            "4) Balance\n" +
            "0) Exit";
    public static final String ENTER_INCOME = "Enter income:";
    public static final String INCOME_ADDED = "Income was added!";
    public static final String BALANCE = "Balance: $%.2f" + System.lineSeparator();
    public static final String PURCHASE_LIST_EMPTY = "Purchase list is empty";
    public static final String ENTER_PURCHASE_NAME = "Enter purchase name:";
    public static final String ENTER_PURCHASE_PRICE = "Enter its price:";
    public static final String PURCHASE_ADDED = "Purchase was added!";
    public static final String PURCHASE_ENTRY = "%s $%.2f" + System.lineSeparator();
    public static final String PURCHASE_TOTAL = "Total sum: $%.2f" + System.lineSeparator();
    public static final String EXIT = "Bye!";
    private static Price balance = Price.FREE;
    private static final Set<Purchase> purchases = new HashSet<>();


    public static void manageInput(Scanner scanner){
        String command = "";
        while (!UserMenuOptions.EXIT.getValue().equals(command)){
            UserMenuOptions option = UserMenuOptions.getByValue(command);
            switch (option){
                case ADD_INCOME:
                    addIncome(scanner);
                    break;
                case ADD_PURCHASE:
                    addPurchase(scanner);
                    break;
                case SHOW_LIST_OF_PURCHASES:
                    showPurchasesList();
                    break;
                case BALANCE:
                    showBalance();
                    break;
                default:
            }
            System.out.println(MENU_COMMAND);
            command = scanner.nextLine();


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

    private static void addIncome(Scanner scanner){
        System.out.println(ENTER_INCOME);
        int income = scanner.nextInt();
        balance = balance.add(Price.build(income, 0));
        System.out.println(INCOME_ADDED);
    }

    private static void addPurchase(Scanner scanner){
        System.out.println(ENTER_PURCHASE_NAME);
        String purchaseName = scanner.nextLine();
        System.out.println(ENTER_PURCHASE_PRICE);
        double purchasePrice = scanner.nextDouble();
        Purchase thisPurchase = new Purchase(purchaseName, purchasePrice);
        purchases.add(thisPurchase);
        System.out.println(PURCHASE_ADDED);
    }

    private static void showPurchasesList(){
        if (purchases.isEmpty()){
            System.out.println(PURCHASE_LIST_EMPTY);
            return;
        }
        System.out.println("");
        Price totalNet = Price.FREE;
        for (Purchase purchase : purchases){
            System.out.printf(PURCHASE_ENTRY, purchase.getName(), purchase.getPrice());
            totalNet = totalNet.add(Price.build(purchase.getPrice()));
        }
        System.out.printf(PURCHASE_TOTAL, totalNet.getDecimal());
    }

}
