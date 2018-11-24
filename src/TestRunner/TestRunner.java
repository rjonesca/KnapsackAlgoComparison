package TestRunner;

import Algorithms.BFKnapsack;
import Algorithms.BUKnapsack;
import Algorithms.TDKnapsack;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.MAX_VALUE;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestRunner {
    private static final List<ITestable> TESTS = new ArrayList<>();
    private static final List<Result> RESULTS = new ArrayList<>();
    
    private static final int NUM_DATA_POINTS = 5;
    private static final int[] TESTS_NUM_ITEMS = {10, 15, 20, 25, 30};
    private static final int[] TESTS_MAX_WEIGHT_HALFN = {5, 7, 10, 12, 15};
    private static final int[] TESTS_MAX_WEIGHT_SAMEN = {10, 15, 20, 25, 30};
    private static final int[] TESTS_MAX_WEIGHT_DOUBLEN = {20, 30, 40, 50, 60};
    private static final int[] TESTS_MAX_WEIGHT_100N = {1000, 1500, 2000, 2500, 3000};
    private static final int[] TESTS_MAX_WEIGHT_EXPN = {1024, 32768, 1048576, 33554432, 1073741824};
    
    // Four runs so we can skip the "cold" run when doing the analysis
    private static final int NUM_OF_RUNS = 4;

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
        
        //Dummy data test
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
        
        // Test set with weight as half n
        for (int t = 0; t < NUM_DATA_POINTS; t++){
            n = TESTS_NUM_ITEMS[t];
            w = TESTS_MAX_WEIGHT_HALFN[t];
            
            values = new int[n];
            weights = new int[n];
            for (int k = 0; k < n; k++) {
                values[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
                weights[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
            }
            
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
            
        // Test set with weight as same n
        for (int t = 0; t < NUM_DATA_POINTS; t++){
            n = TESTS_NUM_ITEMS[t];
            w = TESTS_MAX_WEIGHT_SAMEN[t];
            
            values = new int[n];
            weights = new int[n];
            for (int k = 0; k < n; k++) {
                values[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
                weights[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
            }
            
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
        
        // Test set with weight as double n
        for (int t = 0; t < NUM_DATA_POINTS; t++){
            n = TESTS_NUM_ITEMS[t];
            w = TESTS_MAX_WEIGHT_DOUBLEN[t];
            
            values = new int[n];
            weights = new int[n];
            for (int k = 0; k < n; k++) {
                values[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
                weights[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
            }
            
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
        
        // Test set with weight as 100*n
        for (int t = 0; t < NUM_DATA_POINTS; t++){
            n = TESTS_NUM_ITEMS[t];
            w = TESTS_MAX_WEIGHT_100N[t];
            
            values = new int[n];
            weights = new int[n];
            for (int k = 0; k < n; k++) {
                values[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
                weights[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
            }
            
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
        
        // Test set with weight as 2^n
        for (int t = 0; t < NUM_DATA_POINTS; t++){
            n = TESTS_NUM_ITEMS[t];
            w = TESTS_MAX_WEIGHT_EXPN[t];
            
            values = new int[n];
            weights = new int[n];
            for (int k = 0; k < n; k++) {
                values[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
                weights[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
            }
            
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
    }
    
    public static void report() {  
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("output.csv");;
            final String COMMA_DELIMITER = ",";
            final String NEW_LINE_SEPARATOR = "\n";
            final String FILE_HEADER = "Test Number,Algorithm Name,N,W,Time to Run";
            
            fileWriter.append(FILE_HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);
                
            for (Result result : RESULTS) {                    
                fileWriter.append(String.valueOf(result.getTestNumber()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(result.getAlgorithmName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(result.getNumOfItems()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(result.getKnapsackCapacity()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(result.getTestDuration())); 
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        } catch (IOException e) {
            System.out.println("An error has occurred saving file.");
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();

            } catch (IOException e) {
                System.out.println("An error has occurred closing file.");
            }
        }            
    } 
}
