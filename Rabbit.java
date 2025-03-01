package edu.iastate.cs2280.hw1;

/**
 *  
 * @author Matthew Estes
 *
 */

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal 
{	
	/**
	 * Creates a Rabbit object.
	 * @param p: plain  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Rabbit (Plain p, int r, int c, int a) 
	{
		plain = p;
		row = r;
		column = c;
		age = a;
		// TODO 
	}
		
	// Rabbit occupies the square.
	public State who()
	{
		// TODO  
		return State.RABBIT;
	}
	
	/**
	 * A rabbit dies of old age or hunger. It may also be eaten by a badger or a fox.  
	 * @param pNew     plain of the next cycle 
	 * @return Living  new life form occupying the same square
	 */

	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a rabbit.
		int[] population = new int[NUM_LIFE_FORMS];
		this.census(population);

		if(age == RABBIT_MAX_AGE){
			pNew.grid[row][column] = new Empty(pNew, row, column);
		}else if(population[GRASS] == 0){
			pNew.grid[row][column] = new Empty(pNew, row, column);
		}else if((population[FOX] + population[BADGER]) > population[RABBIT] && population[FOX] > population[BADGER]){
			pNew.grid[row][column] = new Fox(pNew, row, column, age);
		}else if(population[BADGER] > population[RABBIT]){
			pNew.grid[row][column] = new Badger(pNew, row, column, age);
		}else{
			pNew.grid[row][column] = new Rabbit(pNew, row, column, age + 1);
		}
		return pNew.grid[row][column];
	}
}
