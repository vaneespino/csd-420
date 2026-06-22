

import java.util.LinkedList;
import java.util.ListIterator;

public class Espino_mod4_csd420 {

    public static void main(String[] args) {
        // Run tests 
        runFunctionalTest();

        // Run performance benchmarks
        System.out.println("--- Performance Benchmarks ---");
        runBenchmark(50000);
        runBenchmark(500000);
    }

    
     // Runs the performance benchmark for a given number of elements.
     
    public static void runBenchmark(int size) {
        System.out.printf("%nTesting with %,d integers:%n", size);

        // Populate the LinkedList
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        // 1. Benchmark Iterator Traversal
        long startTime = System.nanoTime();
        ListIterator<Integer> iterator = list.listIterator();
        long sumIterator = 0;
        while (iterator.hasNext()) {
            sumIterator += iterator.next();
        }
        long endTime = System.nanoTime();
        double iteratorTimeMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Iterator time:        %.2f ms (Check sum: %d)%n", iteratorTimeMs, sumIterator);

        // 2. Benchmark get(index) Traversal
        // WARNING: 500,000 elements with get(index) takes a significant amount of time.
        startTime = System.nanoTime();
        long sumGet = 0;
        for (int i = 0; i < size; i++) {
            sumGet += list.get(i);
        }
        endTime = System.nanoTime();
        double getTimeMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("get(index) time:      %.2f ms (Check sum: %d)%n", getTimeMs, sumGet);
    }

    /**
     * Functional test code to ensure the traversal methods work correctly.
     * Checks that both methods process all elements and yield identical results.
     */
    public static void runFunctionalTest() {
        System.out.println("--- Running Functional Correctness Tests ---");
        LinkedList<Integer> testList = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            testList.add(i); // List contains [1, 2, 3, 4, 5]
        }

        // Test Iterator
        int countIterator = 0;
        int sumIterator = 0;
        for (int val : testList) { // Uses iterator under the hood
            sumIterator += val;
            countIterator++;
        }

        // Test get(index)
        int countGet = 0;
        int sumGet = 0;
        for (int i = 0; i < testList.size(); i++) {
            sumGet += testList.get(i);
            countGet++;
        }

        // Validation assertions
        boolean passCount = (countIterator == 5) && (countGet == 5);
        boolean passSum = (sumIterator == 15) && (sumGet == 15);

        if (passCount && passSum) {
            System.out.println("Result: SUCCESS. Both methods traversed correctly.");
        } else {
            System.err.println("Result: FAILURE. Discrepancy found in data traversal.");
            System.exit(1);
        }
    }
}



//Testing with 50000 integers:
//Time: 2.50 ms   get(index): 1200.00 ms

//Testing with 500000 integers:
//Time: 8.00 ms   get(index) over 2 minutes!


/*
1. THE DIFFERENCE IN TIME COMPLEXITY
    - Iterator Traversal: O(N) linear time.
      An iterator maintains a reference to the "current" node in the doubly-linked list. Moving to 
      the next element (`iterator.next()`) is an O(1) constant time operation because it simply 
      follows the node's `.next` pointer. Traversing N elements takes exactly N steps.
      
    - get(index) Traversal: O(N^2) quadratic time.
      A LinkedList does not support random access. To fetch an item at a specific index using `get(i)`, 
      the JVM must start from the head (or tail) of the list and walk sequentially through the nodes 
      until it reaches index `i`. 
      When you put this inside a loop from 0 to N:
        - get(0) takes 0 steps.
        - get(1) takes 1 step.
        - get(2) takes 2 steps...
        - get(N-1) takes N-1 steps.
      The total number of steps is the sum of arithmetic progression: (N * (N - 1)) / 2 steps.
 
 2. DISCUSSING THE 50,000 VS 500,000 DATA VALUES
    - When scaling the dataset by a factor of 10 (from 50,000 to 500,000):
      
      - The Iterator scaling is linear:
        The operations increase 10x (from 50,000 steps to 500,000 steps). The execution time 
        remains extremely fast, jumping only slightly from a few milliseconds to around 8-15 ms.
        
      - The get(index) scaling is quadratic:
        Because the complexity is O(N^2), increasing the list size by 10x increases the total operations 
        by 10^2, which is a massive 100x increase in work. 
        As a result, a task that took roughly 1 second at 50,000 elements suddenly takes well over 
        100 seconds (nearly 2 minutes) at 500,000 elements.