package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RabbitTest {

    /**
     * Tests the next method of the Rabbit class.
     * The next method determines the next state of the Rabbit based on
     * its age, surrounding population, and other rules.
     */

    @Test
    void testRabbitAgesAndSurvives() {
        Plain currentPlain = new Plain(3);
        Plain nextPlain = new Plain(3);

        Rabbit rabbit = new Rabbit(currentPlain, 1, 1, 2); // Rabbit with age 2
        currentPlain.grid[1][1] = rabbit;
        currentPlain.grid[0][0] = new Grass(currentPlain, 0, 0);

        Living result = rabbit.next(nextPlain);

        assertTrue(result instanceof Rabbit);
        assertEquals(3, ((Rabbit) result).age);
    }

    @Test
    void testRabbitDiesOfOldAge() {
        Plain currentPlain = new Plain(3);
        Plain nextPlain = new Plain(3);

        Rabbit rabbit = new Rabbit(currentPlain, 1, 1, 3); // Rabbit with age 3 (max age)
        currentPlain.grid[1][1] = rabbit;

        Living result = rabbit.next(nextPlain);

        assertTrue(result instanceof Empty);
    }

    @Test
    void testRabbitDiesDueToNoGrass() {
        Plain currentPlain = new Plain(3);
        Plain nextPlain = new Plain(3);

        Rabbit rabbit = new Rabbit(currentPlain, 1, 1, 1);
        currentPlain.grid[1][1] = rabbit;

        Living result = rabbit.next(nextPlain);

        assertTrue(result instanceof Empty);
    }

    @Test
    void testRabbitEatenByFox() {
        Plain currentPlain = new Plain(3);
        Plain nextPlain = new Plain(3);

        Rabbit rabbit = new Rabbit(currentPlain, 1, 1, 1);
        currentPlain.grid[1][1] = rabbit;
        currentPlain.grid[0][0] = new Fox(currentPlain, 0, 0, 2);
        currentPlain.grid[0][1] = new Fox(currentPlain, 0, 1, 1);
        currentPlain.grid[0][2] = new Fox(currentPlain, 0, 2, 3);

        Living result = rabbit.next(nextPlain);

        assertTrue(result instanceof Fox);
    }

    @Test
    void testRabbitReplacedByBadger() {
        Plain currentPlain = new Plain(3);
        Plain nextPlain = new Plain(3);

        // Initialize currentPlain grid with Empty objects to avoid null
        for (int i = 0; i < currentPlain.getWidth(); i++) {
            for (int j = 0; j < currentPlain.getWidth(); j++) {
                currentPlain.grid[i][j] = new Empty(currentPlain, i, j);
            }
        }

        // Initialize nextPlain grid with Empty objects to avoid null
        for (int i = 0; i < nextPlain.getWidth(); i++) {
            for (int j = 0; j < nextPlain.getWidth(); j++) {
                nextPlain.grid[i][j] = new Empty(nextPlain, i, j);
            }
        }

        System.out.println("Grid before test:");
        for (int i = 0; i < currentPlain.getWidth(); i++) {
            for (int j = 0; j < currentPlain.getWidth(); j++) {
                System.out.print((currentPlain.grid[i][j] == null ? "null" : currentPlain.grid[i][j].who()) + " ");
            }
            System.out.println();
        }

        Living rabbit = new Rabbit(currentPlain, 1, 1, 1);
        currentPlain.grid[1][1] = rabbit;
        currentPlain.grid[0][0] = new Badger(currentPlain, 0, 0, 2);
        currentPlain.grid[0][1] = new Badger(currentPlain, 0, 1, 3);
        currentPlain.grid[0][2] = new Badger(currentPlain, 0, 2, 1);

        Living result = rabbit.next(nextPlain);

        assertTrue(result instanceof Badger);
    }
   

    @Test
    void testRabbitSurvivesAndAgesWithGrass() {
        Plain currentPlain = new Plain(3);
        Plain nextPlain = new Plain(3);

        Rabbit rabbit = new Rabbit(currentPlain, 1, 1, 1);
        currentPlain.grid[1][1] = rabbit;
        currentPlain.grid[0][0] = new Grass(currentPlain, 0, 0);
        currentPlain.grid[0][1] = new Grass(currentPlain, 0, 1);

        Living result = rabbit.next(nextPlain);

        assertTrue(result instanceof Rabbit);
        assertEquals(2, ((Rabbit) result).age);
    }
}