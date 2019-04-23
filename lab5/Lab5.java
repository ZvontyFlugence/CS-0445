package cs445.lab5;

import java.util.Arrays;

public class Lab5 {
    /**
     * Reverses of the objects in an array using
     * recursion
     */
    static <T> void reverse(T[] a) {
        if(a.length < 2) return;
        else if(a.length == 2) {
            T temp = a[0];
            a[0] = a[1];
            a[1] = temp;
        }
        else {
            T temp = a[0];
            a[0] = a[a.length-1];
            a[a.length-1] = temp;
            T[] b = (T[]) new Object[a.length-2];
            for(int i=0; i<b.length; i++) {
                b[i] = a[i+1];
            }
            reverse(b);
            for(int i=0; i<b.length; i++) {
                a[i+1] = b[i];
            }
        }
    }

    /**
     * Replaces each instance of character `before` with
     * character `after` within string `str`, and returns the
     * resulting string (using recursion)
     */
    static String replace(String str, char before, char after) {
        if(str.equals("")) return "";
        else {
            if(str.charAt(0) == before) return after + replace(str.substring(1), before, after);
            else return str.charAt(0) + replace(str.substring(1), before, after);
        }
    }
}
