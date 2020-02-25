package com.cgrdev.ristorante;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainF2 {

    public static void main(String[] args) {

        /**
         * Fase 1
         */

        // Var definitions
        int billet5, billet10, billet50, billet100, billet200, billet500;
        int preuTotal;
        String[] menu = new String[5];
        double[] preuPlat = new double[5];

        /**
         * Fase 2
         */


        // Menu and prices initializations
        initializeMenu(menu, preuPlat);

        // List to save the ordered plates
        List<String> order = new ArrayList<>();

        // Var to know if the user wants more plates
        int more = 1;

        while (more == 1) {
            showMenu(menu, preuPlat);
            askForPlate(order);
            more=askForMore();
        }
    }

    private static int askForMore() {
        int option = -1;
        Scanner scanner;
        while (option != 0 && option != 1) {
            scanner = new Scanner(System.in);
            System.out.println("Vols demanar mes plats? (1: Sí/ 0: No)");
            try {
                option = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Opció escollida no vàlida, torna a provar.");
                option=-1;
            }
        }
        return option;
    }

    private static void askForPlate(List<String> order) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introdueix un plat del menu:");
        order.add(scanner.nextLine());
    }

    private static void initializeMenu(String[] menu, double[] preuPlat) {

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < menu.length; i++) {
            scanner = new Scanner(System.in);
            System.out.println("Introdueix el nom del plat " + (i + 1));
            menu[i] = scanner.nextLine();
            System.out.println("Introdueix el seu preu.");
            preuPlat[i] = scanner.nextDouble();
        }
    }

    private static void showMenu(String[] menu, double[] preuPlat) {
        System.out.println("Aquest és el menú del dia:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(menu[i] + " - " + String.format("%.2f", preuPlat[i]) + " €");
        }

    }

}
