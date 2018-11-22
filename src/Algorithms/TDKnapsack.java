package Algorithms;

import TestRunner.ITestable;
import TestRunner.Result;
import java.util.ArrayList;
import java.util.List;

public class TDKnapsack implements ITestable {
    
    private int NOT_CALCULATED = -1;
    private int TAKE_ITEM = 1;
    private int LEAVE_ITEM = 0;
    private int[][] maxValue;
    private int[][] maxValueChoice;
    private int[] weights;
    private int[] values;
    private List<Integer> items;
    private Result result;
    
    public TDKnapsack() {
        // Do nothing other than call super
    }    
    
    private void createMatrix(int w, int n){
        maxValue = new int[n+1][w+1];
        maxValueChoice = new int[n+1][w+1];
        // Java automatically initializes all values to 0
        // Don't need to initialize column 0, row 0 to 0 manually
        // 0 for maxValueChoice stands for not taking the item, which makes 
        // sense for column/weight 0 and item/row 0
        
        // Do need to initialize all other values to -1, however...
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= w; j++) {
                maxValue[i][j] = NOT_CALCULATED;
                maxValueChoice[i][j] = NOT_CALCULATED;
            }
        }
    }
    
    @Override
    public Result knapsack(int[] values, int[] weights, int w, int n) {
        
        items = new ArrayList<>();
        this.weights = weights;
        this.values = values;
        
        // Set given values in result
        result = new Result();
        
        // Check if parameters are invalid
        if (n <= 0 || w <= 0 || values.length == 0 || weights.length == 0) {
            // Return a result with an empty list
            result.setSelectedItemsList(items);
            result.setSelectedItemsValue(0);
            result.setSelectedItemsWeight(0);
            return result;  
        }
        
        createMatrix(w, n);
        
        // Do top-down knapsack
        maxValue[n][w] = knapsackTD(n, w);
        
        /* Get item list */
        
        // Holds the total weight and value of all chosen items
        int totalWeight = 0;
        
        // Iteration variables
        int i = n;
        int j = w;
        while (i >= 0) {
            if (maxValueChoice[i][j] == TAKE_ITEM) {
                items.add(i);
                totalWeight = totalWeight + weights[i-1];
                j = j - weights[i-1];
                i--;
                if (j <= 0) {
                    break;
                }
            } else {
                // Must have not taken item; check previous item at that weight
                i--;
            }
        }
        
        result.setSelectedItemsWeight(totalWeight);
        result.setSelectedItemsValue(maxValue[n][w]);
        result.setSelectedItemsList(items);
        
        return result;
    }
    
    private int knapsackTD(int index, int capacity) {

        // Base case
        if (index <= 0 || capacity <= 0) {
            return 0;
        }
        
        // Already calculated
        if (maxValue[index][capacity] > NOT_CALCULATED) {
            return maxValue[index][capacity];
        } else { // If haven't calculated the value, yet
            if (capacity < weights[index-1]) {
                // Item doesn't fit
                maxValue[index][capacity] = knapsackTD(index-1, capacity);
                maxValueChoice[index][capacity] = LEAVE_ITEM;
            } else { // Item fits
                int previous = knapsackTD(index-1, capacity);
                int current = knapsackTD(index-1, capacity - weights[index-1]) 
                        + values[index-1];
                if (previous > current) {
                    maxValue[index][capacity] = previous;
                    maxValueChoice[index][capacity] = LEAVE_ITEM;
                } else {
                    maxValue[index][capacity] = current;
                    maxValueChoice[index][capacity] = TAKE_ITEM;
                }
            }
        } 
        return maxValue[index][capacity];
    }
    
    @Override
    public String toString() {
        return "Top-Down Knapsack";
    }
}
