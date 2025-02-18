package edu.iastate.cs2280.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BadgerTest {

    /**
     * Tests the next method in the Badger class.
     * The method determines the next state of the Badger based on the surrounding population and its age.
     */

    @Test
    public void testBadgerDiesOfOldAge() {
        Plain p = new Plain(3);
        Plain pNew = new Plain(3);
        p.grid[1][1] = new Badger(p, 1, 1, Living.BADGER_MAX_AGE);

        Living result = p.grid[1][1].next(pNew);

        assertTrue(result instanceof Empty);
    }

    @Test
    public void testBadgerReplacedByFox() {
        Plain p = new Plain(3);
        Plain pNew = new Plain(3);

        p.grid[1][1] = new Badger(p, 1, 1, 2);
        p.grid[0][1] = new Fox(p, 0, 1, 1);
        p.grid[1][0] = new Fox(p, 1, 0, 1);

        Living result = p.grid[1][1].next(pNew);

        assertTrue(result instanceof Fox);
    }

    @Test
    public void testBadgerReplacedByEmptyDueToRabbits() {
        Plain p = new Plain(3);
        Plain pNew = new Plain(3);

        p.grid[1][1] = new Badger(p, 1, 1, 2);
        p.grid[0][1] = new Rabbit(p, 0, 1, 1);
        p.grid[1][0] = new Rabbit(p, 1, 0, 1);
        p.grid[0][0] = new Rabbit(p, 0, 0, 1);

        Living result = p.grid[1][1].next(pNew);

        assertTrue(result instanceof Empty);
    }

    @Test
    public void testBadgerAges() {
        Plain p = new Plain(3);
        Plain pNew = new Plain(3);

        p.grid[1][1] = new Badger(p, 1, 1, 2);
        p.grid[0][1] = new Rabbit(p, 0, 1, 1);
        p.grid[1][0] = new Grass(p, 1, 0);
        p.grid[0][0] = new Empty(p, 0, 0);

        Living result = p.grid[1][1].next(pNew);

        assertTrue(result instanceof Badger);
        assertEquals(3, ((Badger) result).age);
    }
}