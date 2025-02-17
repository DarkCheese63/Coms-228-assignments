package edu.iastate.cs2280.hw1;

/**
 *  
 * @author Matthew Estes
 *
 */

/*
 * This class is to be extended by the Badger, Fox, and Rabbit classes. 
 */
public abstract class Animal extends Living implements MyAge
{
	protected int age;   // age of the animal
	public void animal(Plain p, int c, int r, int a){
		age = a;
		column = c;
		row = r;
		plain = p;


	}

	@Override
	/**
	 * 
	 * @return age of the animal 
	 */
	public int myAge()
	{
		// TODO 
		return age;
	}
}
