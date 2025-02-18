package edu.iastate.cs2280.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrassTest {

    /**
     * Tests the next() method of the Grass class.
     * This method determines the life form that will occupy the current cell
     * in the next cycle based on the neighboring population.
     */

    @Test
    public void testNext_BecomesEmpty() {
        // Initialize current Plain
        Plain p = new Plain(3);
        p.grid[1][1] = new Grass(p, 1, 1);
        p.grid[0][0] = new Rabbit(p, 0, 0, 0);
        p.grid[0][1] = new Rabbit(p, 0, 1, 0);
        p.grid[0][2] = new Rabbit(p, 0, 2, 0);
        p.grid[1][0] = new Rabbit(p, 1, 0, 0);

        // Add other cells if necessary (Empty for simplicity)
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (p.grid[r][c] == null)
                    p.grid[r][c] = new Empty(p, r, c);
            }
        }

        // Initialize new Plain for the next cycle
        Plain pNew = new Plain(3);

        // Call next()
        Living result = p.grid[1][1].next(pNew);

        // Verify it becomes Empty
        assertTrue(result instanceof Empty);
    }

    @Test
    public void testNext_BecomesRabbit() {
        // Initialize current Plain
        Plain p = new Plain(3);
        p.grid[1][1] = new Grass(p, 1, 1);
        p.grid[0][0] = new Rabbit(p, 0, 0, 0);
        p.grid[0][1] = new Rabbit(p, 0, 1, 0);
        p.grid[0][2] = new Rabbit(p, 0, 2, 0);

        // Add other cells if necessary (Empty for simplicity)
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (p.grid[r][c] == null)
                    p.grid[r][c] = new Empty(p, r, c);
            }
        }

        // Initialize new Plain for the next cycle
        Plain pNew = new Plain(3);

        // Call next()
        Living result = p.grid[1][1].next(pNew);

        // Verify it becomes Rabbit
        assertTrue(result instanceof Rabbit);
    }

    @Test
    public void testNext_RemainsGrass() {
        // Initialize current Plain
        Plain p = new Plain(3);
        p.grid[1][1] = new Grass(p, 1, 1);

        // Add Grass and other life forms
        p.grid[0][0] = new Grass(p, 0, 0);
        p.grid[0][1] = new Grass(p, 0, 1);
        p.grid[0][2] = new Grass(p, 0, 2);
        p.grid[1][0] = new Grass(p, 1, 0);
        p.grid[1][2] = new Empty(p, 1, 2);
        p.grid[2][0] = new Empty(p, 2, 0);
        p.grid[2][1] = new Empty(p, 2, 1);
        p.grid[2][2] = new Rabbit(p, 2, 2, 0);

        // Initialize new Plain for the next cycle
        Plain pNew = new Plain(3);

        // Call next()
        Living result = p.grid[1][1].next(pNew);

        // Verify it remains Grass
        assertTrue(result instanceof Grass);
    }
}