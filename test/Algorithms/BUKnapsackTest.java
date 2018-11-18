package Algorithms;

import TestRunner.Result;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class BUKnapsackTest {
    private BUKnapsack bf;
    
    public BUKnapsackTest() {
    }
    
    @Before
    public void setUp() {
        bf = new BUKnapsack();
    }
    
    /**
     * Test of knapsack method, of class BUKnapsack.
     */
    @Test
    public void testKnapsack() {
        Result result = bf.knapsack(new int[4], new int[4], 0, 0);
        assertNotNull(result.getValue());
    }

    /**
     * Test of toString method, of class BUKnapsack.
     */
    @Test
    public void testToString() {
        assertEquals("Bottom-Up Knapsack", bf.toString());
    }
    
}
