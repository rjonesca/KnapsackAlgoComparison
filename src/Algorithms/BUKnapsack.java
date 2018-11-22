package Algorithms;

import TestRunner.ITestable;
import TestRunner.Result;
import java.util.ArrayList;
import java.util.List;

public class BUKnapsack implements ITestable {
    
    private int TAKE_ITEM = 1;
    private int LEAVE_ITEM = 0;
    private int[][] maxValue;
    private int[][] maxValueChoice;
    private List<Integer> items;
    private Result result;
    
    public BUKnapsack() {
        // Do nothing other than call super
    }
    
    private void createMatrix(int w, int n){
        maxValue = new int[n+1][w+1];
        maxValueChoice = new int[n+1][w+1];
        // Java automatically initializes all values to 0
        // Don't need to initialize column 0, row 0 to 0 manually
        // 0 for maxValueChoice stands for not taking the item, which makes 
        // sense for column/weight 0 and item/row 0
    }
    
    @Override
    public Result knapsack(int[] values, int[] weights, int w, int n) {
       
        items = new ArrayList<Integer>();
        
        // Set given values in result
        result = new Result();
        
        // Check if any parameters are invalid
        if (n <= 0 || w <= 0 || values.length == 0 || weights.length == 0) {
            // Return a result with an empty list
            result.setSelectedItemsList(items);
            result.setSelectedItemsValue(0);
            result.setSelectedItemsWeight(0);
            return result;  
        }
        
        // Matrix to hold max value for each weight and item
        createMatrix(w,n);
              
        // Temporary variables for readability
        int previous;
        int current;
        // Calculate the maximum value for every weight with 
        // a subset of the items
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= w; j++) {
                if (j - weights[i-1] < 0) { // Item doesn't fit
                    maxValue[i][j] = maxValue[i-1][j];
                    maxValueChoice[i][j] = LEAVE_ITEM;
                } else { // Item fits
                    previous = maxValue[i-1][j];
                    current = maxValue[i-1][j-weights[i-1]] + values[i-1];
                    if (previous > current) {
                        maxValue[i][j] = previous;
                        maxValueChoice[i][j] = LEAVE_ITEM;
                    } else {
                        maxValue[i][j] = current;
                        maxValueChoice[i][j] = TAKE_ITEM;
                    }
                }
            }
        } 
        
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
        
        // Would need to store/return list somehow
        return result;
    }
    
    @Override
    public String toString() {
        return "Bottom-Up Knapsack";
    }
    
}
