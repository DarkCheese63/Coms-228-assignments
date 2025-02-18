package edu.iastate.cs2280.hw1;

/**
 *  
 * @author Matthew Estes
 *
 */

/**
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (Plain p, int r, int c, int a) 
	{
		plain = p;
		row = r;
		column = c;
		age = a;
		// TODO 
	}
	
	/**
	 * A badger occupies the square. 	 
	 */
	public State who()
	{
		// TODO 
		return State.BADGER;
	}
	
	/**
	 * A badger dies of old age or hunger, or from isolation and attack by a group of foxes. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a badger.
		int[] population = new int[NUM_LIFE_FORMS];
		this.census(population);

		if(age == BADGER_MAX_AGE){
			pNew.grid[row][column] = new Empty(pNew, row, column);
		} else if(population[FOX] > 1 && population[BADGER] == 1){
			pNew.grid[row][column] = new Fox(pNew, row, column, age);
		} else if(population[RABBIT] > (population[FOX] + population[BADGER]) ){
			pNew.grid[row][column] = new Empty(pNew, row, column);
		}else{
			pNew.grid[row][column] = new Badger(pNew, row, column, age + 1);
		}
		return pNew.grid[row][column];
	}
}
