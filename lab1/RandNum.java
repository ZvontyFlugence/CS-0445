package cs445.lab1;

import java.util.Scanner;

public class RandNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean finished = false;

        int num = (int)(Math.random()*10)+1;

        while (!finished) {
            System.out.println("Pick a number 1-10");
            int guess = scanner.nextInt();

            if (guess == num) {
                System.out.println("Congrats, you win!");
                finished = true;
            } else {
                System.out.println("Oof, You were " + Math.abs(guess - num) + " away!");
                System.out.println("Try again!");
            }
        }
    }
}
