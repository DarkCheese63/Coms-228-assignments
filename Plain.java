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
		grid = new Living[width][width];
		LineScanner.close();
		input.close();
		input = new Scanner(inputFile);
		int col = 0;
		int row = 0;

		while(input.hasNextLine()){
			String line = input.nextLine();
			Scanner CharacterScanner = new Scanner(line);
			while(CharacterScanner.hasNext()){
				String character = CharacterScanner.next();
				if(character.charAt(0) == 'B'){
					int age = Character.getNumericValue(character.charAt(1));
					grid[row][col] = new Badger(this, row, col, age);
					col++;
				}else if(character.charAt(0) == 'F'){
					int age = Character.getNumericValue(character.charAt(1));
					grid[row][col] = new Fox(this, row, col, age);
					col++;
				}else if(character.charAt(0) == 'R'){
					int age = Character.getNumericValue(character.charAt(1));
					grid[row][col] = new Rabbit(this, row, col, age);
					col++;
				}else if(character.charAt(0) == 'G'){
					grid[row][col] = new Grass(this, row, col);
					col++;
				}else if(character.charAt(0) == 'E'){
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
		grid = new Living[width][width];
		for(int i = 0; i < width; i++){
			for(int j = 0; j < width; j++){
				int fill = generator.nextInt(5);
				if(fill == 0){
					grid[i][j] = new Badger(this, i, j, 0);
				}else if(fill == 1){
					grid[i][j] = new Fox(this, i, j, 0);
				}else if(fill == 2){
					grid[i][j] = new Rabbit(this, i, j, 0);
				}else if(fill == 3){
					grid[i][j] = new Grass(this, i, j);
				}else if (fill == 4){
					grid[i][j] = new Empty(this, i, j);
				}
			}

		}
		// TODO 
	}
	
	
	/**
	 * Output the plain grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		String string = "";
		for(int i=0; i<grid.length; i++){
			for(int j = 0; j<grid[0].length; j++){
				int age = 0;
				if(grid[i][j].who() == State.BADGER){
					string += "B" + (((Animal) grid[i][j]).myAge()) + " ";
				}else if(grid[i][j].who() == State.FOX){
					string += "F" + (((Animal) grid[i][j]).myAge()) + " ";
				}else if(grid[i][j].who() == State.RABBIT){
					string += "R" + (((Animal) grid[i][j]).myAge()) + " ";
				}else if(grid[i][j].who() == State.GRASS){
					string += "G ";
				}else if(grid[i][j].who() == State.EMPTY){
					string += "E ";
				}

			}
			string += "\n";
		}
		return string;
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
		File file = new File(outputFileName);
		PrintWriter output = new PrintWriter(file);

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].who() == State.BADGER) {
					output.print("B" + ((Animal) grid[i][j]).myAge() + " ");
				} else if (grid[i][j].who() == State.FOX) {
					output.print("F" + ((Animal) grid[i][j]).myAge() + " ");
				} else if (grid[i][j].who() == State.RABBIT) {
					output.print("R" + ((Animal) grid[i][j]).myAge() + " ");
				} else if (grid[i][j].who() == State.EMPTY) {
					output.print("E  ");
				} else if (grid[i][j].who() == State.GRASS) {
					output.print("G  ");
				}
			}
			output.println();
		}

		output.close();
	}			
}
