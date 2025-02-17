package edu.iastate.cs2280.hw1;

/**
 *  
 * @author Matthew Estes
 *
 */

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner; 
import java.util.Random; 

/**
 * 
 * The plain is represented as a square grid of size width x width. 
 *
 */
public class Plain 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid; 
	
	/**
	 *  Default constructor reads from a file 
	 */
	public Plain(String inputFileName) throws FileNotFoundException
	{		
        // TODO 
		// 
		// Assumption: The input file is in correct format. 
		// 
		// You may create the grid plain in the following steps: 
		// 
		// 1) Reads the first line to determine the width of the grid.
		// 
		// 2) Creates a grid object. 
		// 
		// 3) Fills in the grid according to the input file. 
		// 
		// Be sure to close the input file when you are done.
		File inputFile = new File(inputFileName);
		Scanner input = new Scanner(inputFile);
		String Line = input.nextLine();
		width = 0;
		Scanner LineScanner = new Scanner(Line);
		while(LineScanner.hasNext()){
			width++;
			LineScanner.next();
		}
		LineScanner.close();
		grid = new Living[width][width];
		input.close();
		input = new Scanner(inputFile);
		int col = 0;
		int row = 0;

		while(input.hasNext()){
			String Character = input.nextLine();
			Scanner CharacterScanner = new Scanner(Character);
			while(CharacterScanner.hasNext()){
				String character = CharacterScanner.next();
				if(character.equals("B")){
					int age = CharacterScanner.nextInt();
					grid[row][col] = new Badger(this, row, col, age);
					col++;
				}else if(character.equals("F")){
					int age = CharacterScanner.nextInt();
					grid[row][col] = new Fox(this, row, col, age);
					col++;
				}else if(character.equals("R")){
					int age = CharacterScanner.nextInt();
					grid[row][col] = new Rabbit(this, row, col, age);
					col++;
				}else if(character.equals("G")){
					grid[row][col] = new Grass(this, row, col);
					col++;
				}else if(character.equals("E")){
					grid[row][col] = new Empty(this, row, col);
					col++;
				}

			}
			CharacterScanner.close();
			row++;
			col = 0;
		}
		input.close();
	}
	
	/**
	 * Constructor that builds a w x w grid without initializing it. 
	 * @param width  the grid 
	 */
	public Plain(int w)
	{
		width =w;
		grid = new Living[width][width];
		// TODO 
	}
	
	
	public int getWidth()
	{
		// TODO  
		return width;  // to be modified
	}
	
	/**
	 * Initialize the plain by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		Random generator = new Random(); 
		 
		// TODO 
	}
	
	
	/**
	 * Output the plain grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		// TODO
		return null; 
	}
	

	/**
	 * Write the plain grid to an output file.  Also useful for saving a randomly 
	 * generated plain for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException
	{
		// TODO 
		// 
		// 1. Open the file. 
		// 
		// 2. Write to the file. The five life forms are represented by characters 
		//    B, E, F, G, R. Leave one blank space in between. Examples are given in
		//    the project description. 
		// 
		// 3. Close the file. 
	}			
}
