package cs445.lab7;

import java.util.Arrays;

public class Testers {
    public static void main(String[] args) {
        // Test reverse() for an integer array
        Integer[] intArray = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        DivideAndConquer.reverse(intArray);
        System.out.println(Arrays.toString(intArray));

        // Test reverse() for a double array
        Double[] doubleArray = new Double[] {1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0};
        DivideAndConquer.reverse(doubleArray);
        System.out.println(Arrays.toString(doubleArray));

        // Test several replacements on a sample string
        String name = "bill garrison";
        name = DivideAndConquer.replace(name, 'g', 'b'); // bill barrison
        name = DivideAndConquer.replace(name, 'r', 'l'); // bill ballison
        name = DivideAndConquer.replace(name, 'b', 'j'); // jill jallison
        System.out.println(name);
    }
}

