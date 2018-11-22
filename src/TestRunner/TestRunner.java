package TestRunner;

import Algorithms.BFKnapsack;
import Algorithms.BUKnapsack;
import Algorithms.TDKnapsack;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    private static final List<ITestable> TESTS = new ArrayList<>();
    private static final List<Result> RESULTS = new ArrayList<>();
    
    private static final int NUM_OF_RUNS = 3;
    
    static {
        TESTS.add(new BFKnapsack());
        TESTS.add(new BUKnapsack());
        TESTS.add(new TDKnapsack());
    }
    
    public static void main(String[] args) {
        TestRunner.run();
        TestRunner.report();
    }
    
    public static void run() {
        Result result;
        
        //Dummy data
        int[] values = {4, 34, 5, 8, 2, 23, 58, 9, 65, 1};
        int[] weights = {100, 50, 20, 43, 56, 87, 8, 54, 45, 76};
        int w = 50, n = 10;
        
        for (int i = 0; i < TESTS.size(); i++) {
            for (int j = 0; j < NUM_OF_RUNS; j++) {
                long startTime = System.currentTimeMillis();
                result = TESTS.get(i).knapsack(values, weights, w, n);
                long endTime = System.currentTimeMillis();
                result.setAlgorithmName(TESTS.get(i).toString());
                result.setTestNumber(j + 1);
                result.setNumOfItems(n);
                result.setKnapsackCapacity(w);
                result.setTestDuration(endTime - startTime);
                RESULTS.add(result);
            }
        }
    }
    
    public static void report() {
        for (Result result : RESULTS) {
            System.out.println("Algorithm: " + result.getAlgorithmName());
            System.out.println("Test #: " + result.getTestNumber());
            System.out.println("Time to run: " + result.getTestDuration());
            System.out.println("Number of items to choose from: " + result.getNumOfItems());
            System.out.println("Knapsack Capacity: " + result.getKnapsackCapacity());
            System.out.println("Selected Items Weight: " + result.getSelectedItemsWeight());
            System.out.println("Selected Items Value: " + result.getSelectedItemsValue());
            System.out.println("Selected Items: " + result.getSelectedItemsList());
            System.out.println("");
        }
    }
}
