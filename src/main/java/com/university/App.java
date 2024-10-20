package com.university;

import com.university.Universidad.University;

import java.io.PrintStream;

import static com.university.Solution.exerciseOne;
import static com.university.Solution.exerciseTwo;

public class App {

    static
    {
        setLog(true);
    }

    public static void main(String[] args)
    {
        University university = new University();

//        if (exerciseOne(university))
//            System.out.println("The exercise one was done correctly!");
//        else
//            System.out.println("The exercise one was not done correctly!");

        if (exerciseTwo(university))
            System.out.println("The exercise two was done correctly!");
        else
            System.out.println("The exercise two was not done correctly!");

    }

    private static void setLog(final boolean _enable)
    {
        if (_enable) {
            System.setOut(System.out);
            return;
        }

        System.setOut(new PrintStream(new java.io.OutputStream() {
            public void write(int b) {}
        }));
    }
}