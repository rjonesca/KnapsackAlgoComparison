package Algorithms;

import TestRunner.Result;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TDKnapsackTest {
    private TDKnapsack td;
    
    public TDKnapsackTest() {
    }
    
    @Before
    public void setUp() {
        td = new TDKnapsack();
    }
    
    /**
     * Test of knapsack method, of class TDKnapsack.
     */
    @Test
    public void testKnapsack() {
        Result result = td.knapsack(new int[4], new int[4], 0, 0);
        assertNotNull(result.getValue());
    }

    /**
     * Test of toString method, of class TDKnapsack.
     */
    @Test
    public void testToString() {
        assertEquals("Top-Down Knapsack", td.toString());
    
    }
    
}
