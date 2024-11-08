package com.university;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.university.model.University;
import com.university.solution.ExerciseOne;
import com.university.solution.ExerciseThree;
import com.university.solution.ExerciseTwo;
import com.university.solution.Solution;
import com.university.cli.*;
import com.university.cli.menu.Menu;

public class App {

    static {
        setLog(true);
    }

    public static void main(String[] args) {
        University university = new University();
        HandlerCLI cli = new HandlerCLI(university);

        while (!HandlerCLI.state.equals("exit")) {
            HandlerCLI.clearConsole();
            cli.runCLI(null);
        }
    }

    private static void setLog(final boolean _enable) {
        if (_enable) {
            System.setOut(System.out);
            return;
        }

        System.setOut(new PrintStream(new java.io.OutputStream() {
            public void write(int b) {
            }
        }));
    }
}
