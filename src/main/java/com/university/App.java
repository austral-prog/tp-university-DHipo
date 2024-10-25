package com.university;

import java.io.PrintStream;

import com.university.solution.ExerciseTwo;

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

        ExerciseTwo.solution(university);
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