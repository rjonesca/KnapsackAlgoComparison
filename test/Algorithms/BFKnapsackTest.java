package Algorithms;

import TestRunner.Result;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class BFKnapsackTest {
    private BFKnapsack bf;
    
    public BFKnapsackTest() {
    }

    @Before
    public void setUp() {
        bf = new BFKnapsack();
    }
    
    /**
     * Test of knapsack method, of class BFKnapsack.
     */
    @Test
    public void testKnapsack() {
        Result result = bf.knapsack(new int[4], new int[4], 0, 0);
        assertEquals(result.getSelectedItemsValue(), 0);
    }

    /**
     * Test of toString method, of class BFKnapsack.
     */
    @Test
    public void testToString() {
        assertEquals("Brute Force Knapsack", bf.toString());
    }
    
}
