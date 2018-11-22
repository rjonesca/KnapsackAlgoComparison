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
        int[] values = new int[10];
        int[] weights = new int[10] ;
        int w = 4, n = 4;
        
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
            System.out.println("Selected Items Weight: " + result.getSelectedItemsWeight());
            System.out.println("Selected Items Value: " + result.getSelectedItemsValue());
            System.out.println("");
        }
    }
}
