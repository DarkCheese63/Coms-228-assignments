package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FoxTest {

    /**
     * Test for the Fox `next` method when the fox reaches its maximum age.
     * The fox should die and be replaced by an Empty object.
     */
    @Test
    public void testFoxReachesMaxAge() {
        int width = 3;
        Plain oldPlain = new Plain(width);
        Plain newPlain = new Plain(width);

        Fox fox = new Fox(oldPlain, 1, 1, Living.FOX_MAX_AGE);
        oldPlain.grid[1][1] = fox;

        Living nextState = fox.next(newPlain);

        assertTrue(nextState instanceof Empty);
        assertEquals(1, nextState.row);
        assertEquals(1, nextState.column);
        assertEquals(State.EMPTY, nextState.who());
    }

    /**
     * Test for the Fox `next` method when the adjacent badger population
     * exceeds the fox population. Fox should be replaced by a Badger object.
     */
    @Test
    public void testFoxOutnumberedByBadgers() {
        int width = 3;
        Plain oldPlain = new Plain(width);
        Plain newPlain = new Plain(width);

        Fox fox = new Fox(oldPlain, 1, 1, 2);
        oldPlain.grid[1][1] = fox;

        oldPlain.grid[0][1] = new Badger(oldPlain, 0, 1, 1);
        oldPlain.grid[1][0] = new Badger(oldPlain, 1, 0, 2);
        oldPlain.grid[2][2] = new Rabbit(oldPlain, 2, 2, 1);

        Living nextState = fox.next(newPlain);

        assertTrue(nextState instanceof Badger);
        assertEquals(1, nextState.row);
        assertEquals(1, nextState.column);
        assertEquals(State.BADGER, nextState.who());
    }

    /**
     * Test for the Fox `next` method when combined badger and fox populations
     * exceed the rabbit population. Fox should be replaced by an Empty object.
     */
    @Test
    public void testFoxOutnumberedByBadgerAndFoxCombined() {
        int width = 3;
        Plain oldPlain = new Plain(width);
        Plain newPlain = new Plain(width);

        Fox fox = new Fox(oldPlain, 1, 1, 3);
        oldPlain.grid[1][1] = fox;

        oldPlain.grid[0][1] = new Badger(oldPlain, 0, 1, 2);
        oldPlain.grid[2][2] = new Fox(oldPlain, 2, 2, 1);
        oldPlain.grid[1][0] = new Rabbit(oldPlain, 1, 0, 1);

        Living nextState = fox.next(newPlain);

        assertTrue(nextState instanceof Empty);
        assertEquals(1, nextState.row);
        assertEquals(1, nextState.column);
        assertEquals(State.EMPTY, nextState.who());
    }

    /**
     * Test for the Fox `next` method when no special conditions are met.
     * The fox should simply age by 1 and remain as a Fox object.
     */
    @Test
    public void testFoxNormalAging() {
        int width = 3;
        Plain oldPlain = new Plain(width);
        Plain newPlain = new Plain(width);

        Fox fox = new Fox(oldPlain, 1, 1, 2);
        oldPlain.grid[1][1] = fox;

        oldPlain.grid[0][1] = new Rabbit(oldPlain, 0, 1, 1);
        oldPlain.grid[1][0] = new Grass(oldPlain, 1, 0);
        oldPlain.grid[2][2] = new Grass(oldPlain, 2, 2);

        Living nextState = fox.next(newPlain);

        assertTrue(nextState instanceof Fox);
        assertEquals(1, nextState.row);
        assertEquals(1, nextState.column);
        assertEquals(3, ((Fox) nextState).age);
        assertEquals(State.FOX, nextState.who());
    }
}