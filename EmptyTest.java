package edu.iastate.cs2280.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmptyTest {

    /**
     * Tests the next method when the population around the Empty cell includes more than one Rabbit.
     */
    @Test
    void testNextWithMoreThanOneRabbit() {
        Plain initialPlain = new Plain(3);
        Plain newPlain = new Plain(3);

        initialPlain.grid[1][1] = new Empty(initialPlain, 1, 1);
        initialPlain.grid[0][0] = new Rabbit(initialPlain, 0, 0, 1);
        initialPlain.grid[0][1] = new Rabbit(initialPlain, 0, 1, 1);

        Living nextState = initialPlain.grid[1][1].next(newPlain);

        assertTrue(nextState instanceof Rabbit, "Expected cell to turn into a Rabbit.");
        assertEquals(0, ((Rabbit) nextState).age, "Expected new Rabbit to have age 0.");
    }

    /**
     * Tests the next method when the population around the Empty cell includes more than one Fox.
     */
    @Test
    void testNextWithMoreThanOneFox() {
        Plain initialPlain = new Plain(3);
        Plain newPlain = new Plain(3);

        initialPlain.grid[1][1] = new Empty(initialPlain, 1, 1);
        initialPlain.grid[0][0] = new Fox(initialPlain, 0, 0, 1);
        initialPlain.grid[0][1] = new Fox(initialPlain, 0, 1, 1);

        Living nextState = initialPlain.grid[1][1].next(newPlain);

        assertTrue(nextState instanceof Fox, "Expected cell to turn into a Fox.");
        assertEquals(0, ((Fox) nextState).age, "Expected new Fox to have age 0.");
    }

    /**
     * Tests the next method when the population around the Empty cell includes more than one Badger.
     */
    @Test
    void testNextWithMoreThanOneBadger() {
        Plain initialPlain = new Plain(3);
        Plain newPlain = new Plain(3);

        initialPlain.grid[1][1] = new Empty(initialPlain, 1, 1);
        initialPlain.grid[0][0] = new Badger(initialPlain, 0, 0, 1);
        initialPlain.grid[0][1] = new Badger(initialPlain, 0, 1, 1);

        Living nextState = initialPlain.grid[1][1].next(newPlain);

        assertTrue(nextState instanceof Badger, "Expected cell to turn into a Badger.");
        assertEquals(0, ((Badger) nextState).age, "Expected new Badger to have age 0.");
    }

    /**
     * Tests the next method when the population around the Empty cell includes some Grass.
     */
    @Test
    void testNextWithGrass() {
        Plain initialPlain = new Plain(3);
        Plain newPlain = new Plain(3);

        initialPlain.grid[1][1] = new Empty(initialPlain, 1, 1);
        initialPlain.grid[0][0] = new Grass(initialPlain, 0, 0);

        Living nextState = initialPlain.grid[1][1].next(newPlain);

        assertTrue(nextState instanceof Grass, "Expected cell to turn into Grass.");
    }

    /**
     * Tests the next method when the population around the Empty cell does not satisfy any transformation conditions.
     */
    @Test
    void testNextWithRemainEmpty() {
        Plain initialPlain = new Plain(3);
        Plain newPlain = new Plain(3);

        initialPlain.grid[1][1] = new Empty(initialPlain, 1, 1);

        Living nextState = initialPlain.grid[1][1].next(newPlain);

        assertTrue(nextState instanceof Empty, "Expected cell to remain Empty.");
    }
}