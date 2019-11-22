package cgol;

/*********
 * <p>
 * Title: Rules
 * </p>
 * 
 * <p>
 * Description: This class is responsible for implementation of the CGOL rules
 * on a 8x8 2D array.
 * </p>
 * 
 * @author Aman Verma
 * @version 1.2 (22-11-2019) Implementation of CGOL in Java
 *
 */

public class Rules extends Thread {

	// Variables to store the neighboring cell values
	int upLeft = 0;
	int up = 0;
	int upRight = 0;
	int right = 0;
	int left = 0;
	int downLeft = 0;
	int down = 0;
	int downRight = 0;

	// To count the number of alive cells
	int count = 0, genNum = 1;

	// A 2D array of original generation
	static int arr[][] = { { 0, 1, 0, 0, 1, 0, 0, 1 }, { 0, 0, 0, 1, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0 },
			{ 1, 1, 0, 0, 0, 0, 0, 1 }, { 1, 1, 0, 0, 0, 0, 0, 0 }, };

	// A 2D array to store next generations
	int[][] gen = new int[8][8];

	/***
	 * This method implements the CGOL rules: over-population, under-population,
	 * regeneration and survival.
	 */
	public void run() {
		// To calculate the thread execution time
		long startTime = System.nanoTime();
		long endTime = 0;

		int i, j;
		// Making the duplicate of array
		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++) {
				gen[i][j] = arr[i][j];
			}
		}

		// Logic for next generation
		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++) {
				count = 0;

				// Assigning values of neighboring cells
				// Verifying that the current index is not out of bounds of the array size
				if (i - 1 < 0 || j - 1 < 0)
					upLeft = 0;
				else
					upLeft = arr[i - 1][j - 1];

				if (i - 1 < 0)
					up = 0;
				else
					up = arr[i - 1][j];

				if (i - 1 < 0 || j + 1 > arr.length - 1)
					upRight = 0;
				else
					upRight = arr[i - 1][j + 1];

				if (j - 1 < 0)
					left = 0;
				else
					left = arr[i][j - 1];

				if (j + 1 > arr.length - 1)
					right = 0;
				else
					right = arr[i][j + 1];

				if (i + 1 > arr.length - 1 || j - 1 < 0)
					downLeft = 0;
				else
					downLeft = arr[i + 1][j - 1];

				if (i + 1 > arr.length - 1)
					down = 0;
				else
					down = arr[i + 1][j];

				if (i + 1 > arr.length - 1 || j + 1 > arr.length - 1)
					downRight = 0;
				else
					downRight = arr[i + 1][j + 1];

				// Applying CGOL rules for cell at row i and column j.
				nextGen(i, j);

			}

		}
		System.out.print("\nNext Generation is \n");

		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++) {
				System.out.print(gen[i][j] + " ");
			}
			System.out.print("\n");

		}

		// Get thread execution time
		endTime = System.nanoTime();
		double exeTime = (endTime - startTime)/1000000;
		System.out.println("\n" + currentThread().getName() + " executes in "
				+ exeTime + " ms");

		// Making original array equal to changed array
		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++) {
				arr[i][j] = gen[i][j];
			}
		}

	}

	public static void printOriginal() {
		int i, j;
		System.out.println("Original:");

		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public void nextGen(int i, int j) {

		// Counting number of alive cells
		if (upLeft == 1) {
			count++;
		}

		if (up == 1) {
			count++;
		}

		if (upRight == 1) {
			count++;
		}

		if (left == 1) {
			count++;
		}

		if (right == 1) {
			count++;
		}

		if (downLeft == 1) {
			count++;
		}

		if (down == 1) {
			count++;
		}

		if (downRight == 1) {
			count++;
		}

		// Under-population - Cell dies if there are less than 2 alive neighboring cells
		if (count < 2)
			gen[i][j] = 0;

		// Over-population - Cell dies if there are more than 3 alive neighboring cells
		else if (count > 3)
			gen[i][j] = 0;

		// Alive - Cell becomes alive if there are 3 alive neighboring cells
		else if (count == 3)
			gen[i][j] = 1;

		// Else the cell remain unchanged
		else
			gen[i][j] = arr[i][j];

	}

}