package edu.iastate.cs2280.hw1;

/**
 *  
 * @author Matthew Estes
 *
 */

/**
 * Grass remains if more than rabbits in the neighborhood; otherwise, it is eaten. 
 *
 */
public class Grass extends Living 
{
	public Grass (Plain p, int r, int c) 
	{
		plain = p;
		row = r;
		column = c;

		// TODO 
	}
	
	public State who()
	{
		// TODO  
		return State.GRASS;
	}
	
	/**
	 * Grass can be eaten out by too many rabbits. Rabbits may also multiply fast enough to take over Grass.
	 */
	/**
	 *
	 *a) Empty if at least three times as many Rabbits as Grasses in the neighborhood;
	 * b) otherwise, Rabbit if there are at least three Rabbits in the neighborhood;
	 * c) otherwise, Grass
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for grass.
		int[] population = new int[NUM_LIFE_FORMS];
		this.census(population);
		if (3*population[GRASS] <= population[RABBIT]){
			pNew.grid[row][column] = new Empty(pNew, row, column);
		}else if(population[RABBIT] >= 3){
			pNew.grid[row][column] = new Rabbit(pNew, row, column, 0);
		}else{
			pNew.grid[row][column] = new Grass(pNew, row, column);
		}
		return pNew.grid[row][column];
	}
}
