package Algorithms;

import TestRunner.ITestable;
import TestRunner.Result;
import java.util.ArrayList;

/**
 * Implements 0/1 Knapsack using Brute Force
 */
public class BFKnapsack implements ITestable {

    @Override
    public Result knapsack(int[] values, int[] weights, int w, int n) {
        int max_value = 0, temp_weight_sum, temp_value_sum;
        
        ArrayList<Integer> temp_item_list = new ArrayList<>();
        Result result = new Result();
        
        //Loop over 2 ^ N combinations, store max weight, value and items 
        for (int i = 0; i < (Math.pow(2, values.length)); i++) {
           temp_weight_sum = 0;
           temp_value_sum = 0;
           
           //We look at the binary representation of the int store in i each iteration and treat each
           //bit as a bucket. We are only concerned about the LSB's from 0 to length - 1.
           for (int j = 0; j < values.length; j++) {
               //We shift the bits of i for each j value and if the bit is 1, we "put" that item
               //In the knapsack. At this point, we aren't concerned about over capacity.
               if (((i >> j) & 1) == 1) {
                   temp_weight_sum += weights[j];
                   temp_value_sum += values[j];
                   temp_item_list.add(j+1);
               }
           }
           
           //Here is where we care about capacity. If the temp weight is greater than W,
           //it isn't a valid combination and we move to the next combination. If it is a valid one,
           //we compare it with the previously store max value. If the new value is greater, we update our 
           //current max. We are also keeping track of the current items list and discarding as necessary.
           if (temp_weight_sum <= w && temp_value_sum > max_value) {
               max_value = temp_value_sum;
               result.setSelectedItemsList((ArrayList<Integer>)temp_item_list.clone());
               result.setSelectedItemsWeight(temp_weight_sum);
               result.setSelectedItemsValue(max_value);
           }
           
            temp_item_list.clear();
        }
        
        return result;
    }
    
    @Override
    public String toString() {
        return "Brute Force Knapsack";
    }
    
}
