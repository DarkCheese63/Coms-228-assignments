package edu.iastate.cs2280.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author Matthew Estes
 *
 */

/**
 * 
 * The Wildlife class performs a simulation of a grid plain with
 * squares inhabited by badgers, foxes, rabbits, grass, or none. 
 *
 */
public class Wildlife 
{
	/**
	 * Update the new plain from the old plain in one cycle. 
	 * @param pOld  old plain
	 * @param pNew  new plain 
	 */
	public static void updatePlain(Plain pOld, Plain pNew)
	{
		// TODO 
		// 
		// For every life form (i.e., a Living object) in the grid pOld, generate  
		// a Living object in the grid pNew at the corresponding location such that 
		// the former life form changes into the latter life form. 
		// 
		// Employ the method next() of the Living class.
		if(pOld.grid == null){
			pNew.grid = new Living[pOld.grid.length][pOld.grid[0].length];
		}
		for (int i = 0; i < pOld.grid.length; i++)
		{
			for (int j = 0; j < pOld.grid[0].length; j++) {
					pNew.grid[i][j]=pOld.grid[i][j].next(pOld);
			}
		}

	}


	/**
	 * Repeatedly generates plains either randomly or from reading files. 
	 * Over each plain, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		// TODO 
		// 
		// Generate wildlife simulations repeatedly like shown in the 
		// sample run in the project description. 
		// 
		// 1. Enter 1 to generate a random plain, 2 to read a plain from an input
		//    file, and 3 to end the simulation. (An input file always ends with 
		//    the suffix .txt.)
		// 
		// 2. Print out standard messages as given in the project description. 
		// 
		// 3. For convenience, you may define two plains even and odd as below. 
		//    In an even numbered cycle (starting at zero), generate the plain 
		//    odd from the plain even; in an odd numbered cycle, generate even 
		//    from odd. 
		
		Plain even;   				 // the plain after an even number of cycles 
		Plain odd;                   // the plain after an odd number of cycles
		
		// 4. Print out initial and final plains only.  No intermediate plains should
		//    appear in the standard output.  (When debugging your program, you can 
		//    print intermediate plains.)
		// 
		// 5. You may save some randomly generated plains as your own test cases. 
		// 
		// 6. It is not necessary to handle file input & output exceptions for this 
		//    project. Assume data in an input file to be correctly formated.
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		int choice;
		System.out.println("Simulation of Wildlife of the Plain");
		System.out.println("options: 1 (random plain) 2 (file input) 3 (exit)");
		int trialTracker = 1;
		System.out.print("Trial " + trialTracker + ": ");
		choice = input.nextInt();
		// if choice is 3 skips game loop
		while(choice == 1 || choice == 2){
			trialTracker++;
			if(choice == 1){
				System.out.println("Random Plain");
				System.out.println("Enter grid width: ");
				int width = input.nextInt();

				System.out.println("Enter the amount of cycles: ");
				int cycles = input.nextInt();
				System.out.println("Initial Plain");

				even = new Plain(width);
				odd = new Plain(width);
				odd.randomInit();
				// make initial grid
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < width; j++) {
						even.grid[i][j] = odd.grid[i][j];
					}
				}
				// print the initial grid
				System.out.println(even.toString());

				// check for even or odd cycles and update plain
				for(int i = 0; i< cycles; i++){
					if(i%2 == 0){
						updatePlain(even,odd);
					}else{
						updatePlain(odd,even);
					}
				}
				System.out.println("Final plain");
				if(cycles%2 == 0){
					System.out.println(even.toString());
				}else{
					System.out.println(odd.toString());
				}

			}else if(choice == 2){
				System.out.println("File Input");
				System.out.println("Enter File name: ");
				String fileName = input2.nextLine();

				even = new Plain(fileName);
				odd = new Plain(fileName);

				System.out.println("Enter the amount of cycles: ");
				int cycles = input2.nextInt();

				for(int i = 0; i< cycles; i++) {
					if (i % 2 == 0) {
						updatePlain(even, odd);
					} else {
						updatePlain(odd, even);
					}
				}

				System.out.println("Final plain");
				if(cycles%2 == 0){
					System.out.println(even.toString());
				}else{
					System.out.println(odd.toString());
				}

			}
			System.out.println("Trial " + trialTracker + ": ");
			choice = input.nextInt();
		}
		input2.close();
		input.close();
	}
}
