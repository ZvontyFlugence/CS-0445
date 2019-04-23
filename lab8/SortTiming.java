package cs445.lab8;

import java.util.Random;
import java.util.Arrays;

public class SortTiming {

    // Random number generator for shuffling
    private static Random rand = new Random();
    // Static array that will be reused through all tests
    private static Integer[] a;

    private static final String sep = ",";

    public static void main(String[] args) {
        // print data header
        System.out.println("size" + sep + "isort" + sep + "qsort" + sep + "qsort2");

        // number of trials to average for each test
        int numTrials = 1000;
        // min array size
        int minSize = 5;
        // max array size
        int maxSize = 100;
        // step size when increasing array size
        int step = 1;

        // test each size
        for (int size = minSize; size <= maxSize; size += step) {
            // build the array for the tests of this size
            a = buildArray(size);
            // print the current size
            System.out.print(size + sep);
            // print the time for insertion sort
            System.out.print(timeInsertionSort(numTrials) + sep);
            // print the time for original quick sort
            System.out.print(timeQuickSort(numTrials) + sep);
            // print the time for modified quick sort
            System.out.print(timeQuickSort2(numTrials) + sep);
            // end the line
            System.out.println("");
        }
    }

    /**
     * time improved quicksort for given number of trials
     */
    public static long timeQuickSort2(int numTrials) {
        long time = 0;
        for(int i = 0; i < numTrials; i++) {
            shuffle(a);
            long start = System.nanoTime();
            //Create subproblems via quicksort and a base case
            Sorting.quickSort(a, 50);
            // Finish sort using insertion sort
            Sorting.insertionSort(a);
            time += System.nanoTime() - start;
        }
        return time/numTrials;
    }

    /**
     * time insertion sort for given number of trials
     */
    public static long timeInsertionSort(int numTrials) {
        long time = 0;
        // repeat the test for the specified number of trials
        for (int trial = 0; trial < numTrials; trial++) {
            // shuffle the array
            shuffle(a);
            // determine the time right before calling insertion sort
            long start = System.nanoTime();
            // call insertionSort
            Sorting.insertionSort(a);
            // determine the time that's elapsed
            time += System.nanoTime() - start;
        }
        // return the average per trial
        return time/numTrials;
    }

    /**
     * time quicksort for given number of trials
     */
    public static long timeQuickSort(int numTrials) {
        long time = 0;
        // repeat the test for the specified number of trials
        for (int trial = 0; trial < numTrials; trial++) {
            // shuffle the array
            shuffle(a);
            // determine the time right before calling quickSort
            long start = System.nanoTime();
            // call quickSort
            Sorting.quickSort(a);
            // determine the time that's elapsed
            time += System.nanoTime() - start;
        }
        // return the average per trial
        return time/numTrials;
    }

    /**
     * generate an int array with values 0 to cap
     */
    public static Integer[] buildArray(int cap) {
        Integer[] result = new Integer[cap];
        for (int i = 0; i < cap; i++) {
            result[i] = i;
        }
        return result;
    }

    /**
     * Shuffle an array to a random permutation using the Fisher-Yates shuffle
     */
    public static <T> void shuffle(T[] a) {
        for (int i = 0; i < a.length; i++) {
            int r = rand.nextInt(i+1);
            Sorting.swap(a, i, r);
        }
    }

}

