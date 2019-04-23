package cs445.lab8;

public class Sorting {

    /**
     * basic swap method
     */
    static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * regular old insertion sort
     */
    public static <T extends Comparable<? super T>>
    void insertionSort(T[] a) {
        insertionSort(a, 0, a.length);
    }

    private static <T extends Comparable<? super T>>
    void insertionSort(T[] a, int start, int end) {
        for (int unsorted = start + 1; unsorted < end; unsorted++) {
            T nextUnsorted = a[unsorted];
            insertInOrder(nextUnsorted, a, start, unsorted);
        }
    }

    private static <T extends Comparable<? super T>>
    void insertInOrder(T entry, T[] a, int start, int end) {
        int index = end - 1;
        while (index >= start && entry.compareTo(a[index]) < 1) {
            a[index + 1] = a[index];
            index--;
        }
        a[index + 1] = entry;
    }

    /**
     * quicksort wrapper
     */
    public static <T extends Comparable<? super T>>
    void quickSort(T[] a) {
        quickSort(a, 0, a.length, 1);
    }

    /**
     * quicksort wrapper with base case size param
     */
    public static <T extends Comparable<? super T>>
    void quickSort(T[] a, int baseCase) {
        quickSort(a, 0, a.length, baseCase);
    }

    /**
     * quicksort helper method that accepts base case size param
     */
    private static <T extends Comparable<? super T>>
    void quickSort(T[] a, int start, int end, int baseCase) {
        if (end - start > baseCase) {
            int pivotIndex = partition(a, start, end);
            quickSort(a, start, pivotIndex, baseCase);
            quickSort(a, pivotIndex + 1, end, baseCase);
        }
    }

    /**
     * quicksort partition method
     */
    private static <T extends Comparable<? super T>>
    int partition(T[] a, int start, int end) {
        int pivotIndex = end - 1, lo = start, hi = end - 2;
        T pivot = a[pivotIndex];
        boolean done = false;

        while (!done) {
            while (a[lo].compareTo(pivot) < 0) {
                lo++;
            }

            while (a[hi].compareTo(pivot) > 0 && hi > start) {
                hi--;
            }

            if (lo < hi) {
                swap(a, lo, hi);
                lo++;
                hi--;
            } else {
                done = true;
            }
        }

        swap(a, pivotIndex, lo);
        pivotIndex = lo;

        return pivotIndex;
    }

}

