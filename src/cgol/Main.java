package cgol;

import java.util.Scanner;

/*********
 * <p>
 * Title: Main
 * </p>
 * 
 * <p>
 * Description: This class is responsible for execution of the threads and
 * taking input from the user.
 * </p>
 * 
 * @author Aman Verma
 * @version 1.2 (22-11-2019) Implementation of CGOL in Java
 *
 */
public class Main {
	public static void main(String args[]) throws InterruptedException {
		// Print the original generation
		Rules.printOriginal();

		// Choice for the next generation
		char choice;
		boolean nxtGen = true;

		// Scanner for next generation character input
		Scanner temp = new Scanner(System.in);

		// Threading loop
		while (nxtGen) {
			System.out.println("\nDo you want to see the next generation? (y/n): ");
			choice = temp.next().charAt(0);

			// Create new threads and apply CGOL rules
			if (choice == 'y' || choice == 'Y') {
				Rules thread = new Rules();
				thread.start();
				thread.join();
			} else {
				System.out.println("THE END!");
				nxtGen = false;
			}
		}
		temp.close();
	}
}
