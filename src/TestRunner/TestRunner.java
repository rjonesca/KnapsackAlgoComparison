package TestRunner;

import Algorithms.BFKnapsack;
import Algorithms.BUKnapsack;
import Algorithms.TDKnapsack;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestRunner {
    private static final List<ITestable> TESTS = new ArrayList<>();
    private static final List<Result> RESULTS = new ArrayList<>();
    
//    private static final int NUM_DATA_POINTS = 5;
//    private static final int TESTS_CONSTANT_N = 10;
//    private static final int TESTS_CONSTANT_W = 65536;
//    private static final int[] TESTS_NUM_ITEMS = {4, 8, 12, 16, 20};
//    private static final int[] TESTS_MAX_WEIGHT_1000N = {4000, 8000, 12000, 16000, 20000};
//    private static final int[] TESTS_MAX_WEIGHT_EXPNm2 = {4, 64, 1024, 16384, 262144};
//    private static final int[] TESTS_MAX_WEIGHT_EXPN = {16, 256, 4096, 65536, 1048576};
//    private static final int[] TESTS_MAX_WEIGHT_EXPNp2 = {64, 1024, 16384, 262144, 4194304};
//    private static final int[] TESTS_MAX_WEIGHT_MIX = {64, 256, 1024, 4096, 16384};
    
//    private static final int NUM_DATA_POINTS = 5;
//    private static final int TESTS_CONSTANT_N = 7;
//    private static final int TESTS_CONSTANT_W = 128;
//    private static final int[] TESTS_NUM_ITEMS = {5, 6, 7, 8, 9};
//    private static final int[] TESTS_MAX_WEIGHT_100N = {500, 600, 700, 800, 900};
//    private static final int[] TESTS_MAX_WEIGHT_EXPNm2 = {8, 16, 32, 64, 128};
//    private static final int[] TESTS_MAX_WEIGHT_EXPNm1 = {16, 32, 64, 128, 256};
//    private static final int[] TESTS_MAX_WEIGHT_EXPN = {32, 64, 128, 256, 512};
//    private static final int[] TESTS_MAX_WEIGHT_EXPNp1 = {64, 128, 256, 512, 1024};
//    private static final int[] TESTS_MAX_WEIGHT_EXPNp2 = {128, 256, 512, 1024, 2048};
//    private static final int[] TESTS_MAX_WEIGHT_MIX = {32, 64, 256, 512, 1024};
    
    private static final int NUM_DATA_POINTS = 5;
    private static final int TESTS_CONSTANT_N = 11;
    private static final int TESTS_CONSTANT_W = 2048;
    private static final int[] TESTS_NUM_ITEMS = {10, 11, 12, 13, 14};
    private static final int[] TESTS_MAX_WEIGHT_100N = {500, 600, 700, 800, 900};
    private static final int[] TESTS_MAX_WEIGHT_EXPNm2 = {256, 512, 1024, 2048, 4096};
    private static final int[] TESTS_MAX_WEIGHT_EXPNm1 = {512, 1024, 2048, 4096, 8192};
    private static final int[] TESTS_MAX_WEIGHT_EXPN = {1024, 2048, 4096, 8192, 16384};
    private static final int[] TESTS_MAX_WEIGHT_EXPNp1 = {2048, 4096, 8192, 16384, 32768};
    private static final int[] TESTS_MAX_WEIGHT_EXPNp2 = {4096, 8192, 16384, 32768, 65536};
    private static final int[] TESTS_MAX_WEIGHT_MIX = {512, 1024, 2048, 4096, 16384};
    
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
        String testName;
        
        //Dummy data test
        testName = "Dummy Data";
        int[] values = {4, 34, 5, 8, 2, 23, 58, 9, 65, 1};
        int[] weights = {100, 50, 20, 43, 56, 87, 8, 54, 45, 76};
        int w = 50, n = 10;
        
        for (int i = 0; i < TESTS.size(); i++) {
            for (int j = 0; j < NUM_OF_RUNS; j++) {
                long startTime = System.nanoTime();
                result = TESTS.get(i).knapsack(values, weights, w, n);
                long endTime = System.nanoTime();
                result.setTestName(testName);
                result.setAlgorithmName(TESTS.get(i).toString());
                result.setTestNumber(j + 1);
                result.setNumOfItems(n);
                result.setKnapsackCapacity(w);
                result.setTestDuration(endTime - startTime);
                RESULTS.add(result);
            }
        }
        
        testName = "w = 2^(n-2)";
        for (int t = 0; t < NUM_DATA_POINTS; t++){
            n = TESTS_NUM_ITEMS[t];
            w = TESTS_MAX_WEIGHT_EXPNm2[t];
            
            values = new int[n];
            weights = new int[n];
            for (int k = 0; k < n; k++) {
                values[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
                weights[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
            }
            
            for (int i = 0; i < TESTS.size(); i++) {
                for (int j = 0; j < NUM_OF_RUNS; j++) {
                    long startTime = System.nanoTime();
                    result = TESTS.get(i).knapsack(values, weights, w, n);
                    long endTime = System.nanoTime();
                    result.setTestName(testName);
                    result.setAlgorithmName(TESTS.get(i).toString());
                    result.setTestNumber(j + 1);
                    result.setNumOfItems(n);
                    result.setKnapsackCapacity(w);
                    result.setTestDuration(endTime - startTime);
                    RESULTS.add(result);
                }
            }
        }
        
        testName = "w = 2^(n-1)";
        for (int t = 0; t < NUM_DATA_POINTS; t++){
            n = TESTS_NUM_ITEMS[t];
            w = TESTS_MAX_WEIGHT_EXPNm1[t];
            
            values = new int[n];
            weights = new int[n];
            for (int k = 0; k < n; k++) {
                values[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
                weights[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
            }
            
            for (int i = 0; i < TESTS.size(); i++) {
                for (int j = 0; j < NUM_OF_RUNS; j++) {
                    long startTime = System.nanoTime();
                    result = TESTS.get(i).knapsack(values, weights, w, n);
                    long endTime = System.nanoTime();
                    result.setTestName(testName);
                    result.setAlgorithmName(TESTS.get(i).toString());
                    result.setTestNumber(j + 1);
                    result.setNumOfItems(n);
                    result.setKnapsackCapacity(w);
                    result.setTestDuration(endTime - startTime);
                    RESULTS.add(result);
                }
            }
        }

        testName = "w = 2^n";
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
                    long startTime = System.nanoTime();
                    result = TESTS.get(i).knapsack(values, weights, w, n);
                    long endTime = System.nanoTime();
                    result.setTestName(testName);
                    result.setAlgorithmName(TESTS.get(i).toString());
                    result.setTestNumber(j + 1);
                    result.setNumOfItems(n);
                    result.setKnapsackCapacity(w);
                    result.setTestDuration(endTime - startTime);
                    RESULTS.add(result);
                }
            }
        }

        testName = "w = 2^(n+1)";
        for (int t = 0; t < NUM_DATA_POINTS; t++){
            n = TESTS_NUM_ITEMS[t];
            w = TESTS_MAX_WEIGHT_EXPNp1[t];
            
            values = new int[n];
            weights = new int[n];
            for (int k = 0; k < n; k++) {
                values[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
                weights[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
            }
            
            for (int i = 0; i < TESTS.size(); i++) {
                for (int j = 0; j < NUM_OF_RUNS; j++) {
                    long startTime = System.nanoTime();
                    result = TESTS.get(i).knapsack(values, weights, w, n);
                    long endTime = System.nanoTime();
                    result.setTestName(testName);
                    result.setAlgorithmName(TESTS.get(i).toString());
                    result.setTestNumber(j + 1);
                    result.setNumOfItems(n);
                    result.setKnapsackCapacity(w);
                    result.setTestDuration(endTime - startTime);
                    RESULTS.add(result);
                }
            }
        }

        testName = "w = 2^(n+2)";
        for (int t = 0; t < NUM_DATA_POINTS; t++){
            n = TESTS_NUM_ITEMS[t];
            w = TESTS_MAX_WEIGHT_EXPNp2[t];
            
            values = new int[n];
            weights = new int[n];
            for (int k = 0; k < n; k++) {
                values[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
                weights[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
            }
            
            for (int i = 0; i < TESTS.size(); i++) {
                for (int j = 0; j < NUM_OF_RUNS; j++) {
                    long startTime = System.nanoTime();
                    result = TESTS.get(i).knapsack(values, weights, w, n);
                    long endTime = System.nanoTime();
                    result.setTestName(testName);
                    result.setAlgorithmName(TESTS.get(i).toString());
                    result.setTestNumber(j + 1);
                    result.setNumOfItems(n);
                    result.setKnapsackCapacity(w);
                    result.setTestDuration(endTime - startTime);
                    RESULTS.add(result);
                }
            }
        }

        testName = "w is 100n";
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
                    long startTime = System.nanoTime();
                    result = TESTS.get(i).knapsack(values, weights, w, n);
                    long endTime = System.nanoTime();
                    result.setTestName(testName);
                    result.setAlgorithmName(TESTS.get(i).toString());
                    result.setTestNumber(j + 1);
                    result.setNumOfItems(n);
                    result.setKnapsackCapacity(w);
                    result.setTestDuration(endTime - startTime);
                    RESULTS.add(result);
                }
            }
        }
        
        
        testName = "w is constant";
        for (int t = 0; t < NUM_DATA_POINTS; t++){
            n = TESTS_NUM_ITEMS[t];
            w = TESTS_CONSTANT_W;
            
            values = new int[n];
            weights = new int[n];
            for (int k = 0; k < n; k++) {
                values[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
                weights[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
            }
            
            for (int i = 0; i < TESTS.size(); i++) {
                for (int j = 0; j < NUM_OF_RUNS; j++) {
                    long startTime = System.nanoTime();
                    result = TESTS.get(i).knapsack(values, weights, w, n);
                    long endTime = System.nanoTime();
                    result.setTestName(testName);
                    result.setAlgorithmName(TESTS.get(i).toString());
                    result.setTestNumber(j + 1);
                    result.setNumOfItems(n);
                    result.setKnapsackCapacity(w);
                    result.setTestDuration(endTime - startTime);
                    RESULTS.add(result);
                }
            }
        }
        
        testName = "n is constant";
        for (int t = 0; t < NUM_DATA_POINTS; t++){
            n = TESTS_CONSTANT_N;
            w = TESTS_MAX_WEIGHT_MIX[t];
            
            values = new int[n];
            weights = new int[n];
            for (int k = 0; k < n; k++) {
                values[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
                weights[k] = ThreadLocalRandom.current().nextInt(0, w + 1);
            }
            
            for (int i = 0; i < TESTS.size(); i++) {
                for (int j = 0; j < NUM_OF_RUNS; j++) {
                    long startTime = System.nanoTime();
                    result = TESTS.get(i).knapsack(values, weights, w, n);
                    long endTime = System.nanoTime();
                    result.setTestName(testName);
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
            fileWriter = new FileWriter("output2.csv");
            final String COMMA_DELIMITER = ",";
            final String NEW_LINE_SEPARATOR = "\n";
            final String FILE_HEADER = "Test Name, Test Number,Algorithm Name,N,W,Time to Run";
            
            fileWriter.append(FILE_HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);
                
            for (Result result : RESULTS) {       
                fileWriter.append(result.getTestName());
                fileWriter.append(COMMA_DELIMITER);
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
