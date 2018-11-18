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
        
        for (int i = 0; i < TESTS.size(); i++) {
            for (int j = 0; j < NUM_OF_RUNS; j++) {
                long startTime = System.currentTimeMillis();
                result = TESTS.get(i).run();
                long endTime = System.currentTimeMillis();
                result.setAlgoName(TESTS.get(i).toString());
                result.setRun(j + 1);
                result.setValue(0);
                result.setDuration(endTime - startTime);
                RESULTS.add(result);
            }
        }
    }
    
    public static void report() {
        for (Result result : RESULTS) {
            System.out.println("Algorithm: " + result.getAlgoName());
            System.out.println("Run #: " + result.getRun());
            System.out.println("Time to run: " + result.getDuration());
            System.out.println("Result: " + result.getValue());
            System.out.println("");
        }
    }
}
