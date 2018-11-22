package Algorithms;

import TestRunner.Result;
import java.util.ArrayList;
import java.util.List;
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
        assertEquals(result.getSelectedItemsValue(), 0);
    }

    /**
     * Test of toString method, of class TDKnapsack.
     */
    @Test
    public void testToString() {
        assertEquals("Top-Down Knapsack", td.toString());
    
    }
    
        /**
     * Test correct value return with only single max value item that fits, 
     * for class TDKnapsack.
     */
    @Test
    public void testCorrectValueSingleItem() {
        int[] values = {4, 34, 5, 8, 2, 23, 58, 9, 65, 1};
        int[] weights = {100, 50, 20, 43, 56, 87, 8, 54, 45, 76};
        int w = 50, n = 10;
        
        Result result = td.knapsack(values, weights, w, n);
        assertEquals(65, result.getSelectedItemsValue());
    }
    
    /**
     * Test correct item number return with only single max value item that fits, 
     * for class TDKnapsack.
     */
    @Test
    public void testCorrectItemSingleItem() {
        int[] values = {4, 34, 5, 8, 2, 23, 58, 9, 65, 1};
        int[] weights = {100, 50, 20, 43, 56, 87, 8, 54, 45, 76};
        int w = 50, n = 10;
        List<Integer> items = new ArrayList<>();
        items.add(9);
        
        Result result = td.knapsack(values, weights, w, n);
        assertEquals(items, result.getSelectedItemsList());
    }
    
        /**
     * Test correct item weight return with only single max value item that fits, 
     * for class TDKnapsack.
     */
    @Test
    public void testCorrectWeightSingleItem() {
        int[] values = {4, 34, 5, 8, 2, 23, 58, 9, 65, 1};
        int[] weights = {100, 50, 20, 43, 56, 87, 8, 54, 45, 76};
        int w = 50, n = 10;
        
        Result result = td.knapsack(values, weights, w, n);
        assertEquals(45, result.getSelectedItemsWeight());
    }
    
        /**
     * Test correct value return with multiple items that fit, 
     * for class TDKnapsack.
     */
    @Test
    public void testCorrectValueMultipleItems() {
        int[] values = {4, 34, 5, 8, 2, 23, 58, 9, 65, 1};
        int[] weights = {100, 50, 5, 43, 56, 87, 8, 54, 45, 76};
        int w = 50, n = 10;
        
        Result result = td.knapsack(values, weights, w, n);
        assertEquals(70, result.getSelectedItemsValue());
    }
    
    /**
     * Test correct item number return with multiple items that fit, 
     * for class TDKnapsack.
     */
    @Test
    public void testCorrectItemMultipleItems() {
        int[] values = {4, 34, 5, 8, 2, 23, 58, 9, 65, 1};
        int[] weights = {100, 50, 5, 43, 56, 87, 8, 54, 45, 76};
        int w = 50, n = 10;
        List<Integer> items = new ArrayList<>();
        items.add(9);
        items.add(3);
        
        Result result = td.knapsack(values, weights, w, n);
        assertEquals(items, result.getSelectedItemsList());
    }
    
        /**
     * Test correct item weight return with multiple items that fit, 
     * for class TDKnapsack.
     */
    @Test
    public void testCorrectWeightMultipleItems() {
        int[] values = {4, 34, 5, 8, 2, 23, 58, 9, 65, 1};
        int[] weights = {100, 50, 5, 43, 56, 87, 8, 54, 45, 76};
        int w = 50, n = 10;
        
        Result result = td.knapsack(values, weights, w, n);
        assertEquals(50, result.getSelectedItemsWeight());
    }
    
}
