package cs445.lab7;

public class DivideAndConquer {
    /**
     * Reverses the order of the objects in an array, using divide and conquer
     * recursion.
     */
    public static <T> void reverse(T[] a) {
        // Cannot reverse array of length 1 or less
        if(a.length <= 1) return;
        else {
            // Call helper function
            reverse(a, 0, a.length-1);
        }
    }

    private static <T> void reverse(T[] a, int start, int end) {
        if(start > end) return;
        else {
            T temp = a[start];
            a[start++] = a[end];
            a[end--] = temp;
            reverse(a, start, end);
        }
    }

    /**
     * Replaces each instance of character before with character after within
     * str, and returns the resulting string. This uses divide and conquer
     * recursion.
     */
    public static String replace(String str, char before, char after) {
        // Cannot reverse string of length 1 or less
        if(str.length() <= 1) return str;
        else {
            if(str.indexOf(before) != -1) {
                return replace(str.substring(0, str.indexOf(before)) + after +
                        str.substring(str.indexOf(before)+1), before, after);
            }
            return str;
        }
    }
}

