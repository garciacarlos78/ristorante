package com.cgrdev.ristorante;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainF3 {

	// Var to store the available ordered plates
	static int[] result = new int[]{0,0,0,0,0};

	// Vars to calculate the number of banknotes necessary to pay
	static int billet5, billet10, billet20, billet50, billet100, billet200, billet500;
	static double preuTotal;

	public static void main(String[] args) {

		// Var definitions
		String[] menu = new String[5];
		double[] preuPlat = new double[5];

		// Menu and prices initializations
		initializeMenu(menu, preuPlat);

		// List to save the ordered plates
		List<String> order = new ArrayList<>();

		// Var to know if the user wants more plates
		int more = 1;

		while (more == 1) {
			showMenu(menu, preuPlat);
			askForPlate(order);
			more = askForMore();
		}

		// Keep in 'order' only the non available plates and put in result the quantity of each available plate ordered
		order = checkOrder(menu,order);

		// Show on screen the ordered plates that are not available
		if (order.size()>0) System.out.println("Els següents plats que has demanat no estan disponibles: " + 
				order.toString());

		// Show total order, with plates, quantities and total amount (per plate and total)
		showBill(preuPlat, menu);

		// Calculate minimum number of banknotes necessary to pay
		calcPayment(preuTotal);

		// Show the amount of each banknote necessary to pay
		System.out.println("Pots pagar amb els següents billets:");
		if (billet500 > 0) System.out.println(billet500 + " billet(s) de 500€");
		if (billet200 > 0) System.out.println(billet200 + " billet(s) de 200€");
		if (billet100 > 0) System.out.println(billet100 + " billet(s) de 100€");
		if (billet50 > 0) System.out.println(billet50 + " billet(s) de 50€");
		if (billet20 > 0) System.out.println(billet20 + " billet(s) de 20€");
		if (billet10 > 0) System.out.println(billet10 + " billet(s) de 10€");
		if (billet5 > 0) System.out.println(billet5 + " billet(s) de 5€");
	}

	// Conditions:
	// 500€ banknote: > 485€
	// 200€ banknote: > 185€
	// 100€ banknote: > 85€
	// 50€ banknote: > 45€
	// 20€ banknote: > 15€
	// 10€ banknote: > 5€
	// 5€ banknote: <= 5€
	private static void calcPayment(double preuTotal) {

		// Round price to calculate number of banknotes
		int roundPrice=(int) preuTotal;
		if (preuTotal-roundPrice>0) roundPrice++;

		billet500=roundPrice/500;
		roundPrice%=500;
		if (roundPrice>485) {billet500++;return;}
		// 0 <= roundPrice < 485
		billet200=roundPrice/200;
		roundPrice%=200;
		// 0 <= roundPrice < 200, if billet200==2 --> roundPrice < 85
		if (roundPrice>185) {billet200++;return;}
		// 0 <= roundPrice <= 185, if roundPrice >= 85 --> billet200<2
		billet100=roundPrice/100;
		roundPrice%=100;
		// 0 <= roundPrice < 100
		if (roundPrice>85) {
			if (billet100 == 0) {
				billet100++;
				return;
			} else {
				billet100 = 0;
				billet200++;
				return;
			}
		}
		billet50=roundPrice/50;
		roundPrice%=50;
		// 0 <= roundPrice <= 49
		if (roundPrice>45) {billet50++; return;}
		billet20=roundPrice/20;
		roundPrice%=20;
		// 0 <= roundPrice <= 15
		if (roundPrice>15) {billet20++;return;}
		billet10=roundPrice/10;
		roundPrice%=10;
		if (roundPrice>5) {billet10++;return;}
		// 0 <= roundPrice <= 5
		billet5=roundPrice/5;
		if (roundPrice%5 > 0) billet5++;
	}

	private static void showBill(double[] preuPlat, String[] menu) {

		double platePrice=0;

		System.out.println("A continuació es mostraran els plats disponibles demanats, la seva quantitat, i el preu total.");
		System.out.println("=======================================");

		for (int i=0;i<result.length;i++) {
			if (result[i]>0) {
				platePrice=result[i]*preuPlat[i];
				System.out.println(menu[i] + " - " + result[i] + " ració(ns) - " + String.format("%.2f",platePrice) + " €");
				preuTotal+=platePrice;
			}
		}
		System.out.println("=======================================");
		System.out.println("Preu total de la comanda: " + String.format("%.2f",preuTotal) + " €");
	}


	/**
	 * Let the amount of available plates ordered in 'result', and return the non available plates ordered
	 * @param menu Array of available plates
	 * @param order List of ordered plates
	 * @return List of non available ordered plates
	 */
	private static List<String> checkOrder(String[] menu, List<String> order) {

		// List to save the non available ordered plates
		List<String> badOrdered = new ArrayList<>();

		for (String plate: order) {
			for (int i=0;i<menu.length;i++) {
				if (plate.equalsIgnoreCase(menu[i])) {
					result[i]++;
					break;
				}
				// The ordered plate is not in the available menu
				if (i==menu.length-1) badOrdered.add(plate);
			}
		}
		// We return the non available ordered plates
		return badOrdered;
	}

	private static int askForMore() {
		int option = -1;
		Scanner scanner;
		while (option != 0 && option != 1) {
			scanner = new Scanner(System.in);
			System.out.println("Vols demanar més plats? (1: Sí/ 0: No)");
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
		System.out.println("Introdueixi un plat del menu.");
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
