package cs445.lab1;

import java.util.Scanner;

public class Lab1Pt1 {
    public static void main(String[] args) {
        System.out.println("You should see this message in your terminal!");
        Scanner stdin = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = stdin.nextLine();
        System.out.println("Enter your age: ");
        int age = stdin.nextInt();
        System.out.println("Hello, " + name + "! You are " + age + " years old.");
    }
}

