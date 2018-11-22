package Algorithms;

import TestRunner.ITestable;
import TestRunner.Result;
import java.util.ArrayList;

public class BFKnapsack implements ITestable {

    @Override
    public Result knapsack(int[] values, int[] weights, int w, int n) {
        int max_value = 0, temp_weight_sum, temp_value_sum;
        
        ArrayList<Integer> temp_item_list = new ArrayList<>();
        Result result = new Result();
        
        for (int i = 0; i < (Math.pow(2, values.length)); i++) {
           temp_weight_sum = 0;
           temp_value_sum = 0;
           
           for (int j = 0; j < values.length; j++) {
               if (((i >> j) & 1) == 1) {
                   temp_weight_sum += weights[j];
                   temp_value_sum += values[j];
                   temp_item_list.add(j);
               }
           }
           
           if (temp_weight_sum <= w && temp_value_sum > max_value) {
               max_value = temp_value_sum;
               result.setSelectedItemsList(temp_item_list);
               result.setSelectedItemsWeight(temp_weight_sum);
               result.setSelectedItemsValue(max_value);
               temp_item_list.clear();
           }
           
           System.out.println(i);
        }
        
        return result;
    }
    
    @Override
    public String toString() {
        return "Brute Force Knapsack";
    }
    
}
