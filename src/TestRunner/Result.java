package TestRunner;

import java.util.List;

public class Result {
    private String algorithmName;
    private int testNumber;
    private long testDuration;
    private String testName;
    private int numOfItems;
    private int knapsackCapacity;
    private List<Integer> selectedItemsList;
    private int selectedItemsWeight;
    private int selectedItemsValue;
    
    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algoName) {
        this.algorithmName = algoName;
    }

    public int getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(int testNumber) {
        this.testNumber = testNumber;
    }
    
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
    
    public long getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(long testDuration) {
        this.testDuration = testDuration;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    public int getKnapsackCapacity() {
        return knapsackCapacity;
    }

    public void setKnapsackCapacity(int knapsackCapacity) {
        this.knapsackCapacity = knapsackCapacity;
    }

    public List<Integer> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<Integer> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public int getSelectedItemsWeight() {
        return selectedItemsWeight;
    }

    public void setSelectedItemsWeight(int selectedItemsWeight) {
        this.selectedItemsWeight = selectedItemsWeight;
    }

    public int getSelectedItemsValue() {
        return selectedItemsValue;
    }

    public void setSelectedItemsValue(int selectedItemsValue) {
        this.selectedItemsValue = selectedItemsValue;
    }
}
