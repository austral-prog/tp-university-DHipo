package com.university.cli.menu;

import com.university.cli.CommandHandler;
import com.university.cli.HandlerCLI;
import com.university.model.University;
import com.university.solution.Solution;

import java.util.Map;

public class SolutionMenu extends Menu{
    public SolutionMenu(University _university, Map<String, Solution> _solutions) {
        super(_university);
        for (String key : _solutions.keySet())
            this.options.put(key.toLowerCase(), () -> _solutions.get(key).solution());
    }

    @Override
    public void print() {
        System.out.print("----- Solutions -----\n\nOptions:\n");

        for (String key : options.keySet().stream().toList())
            System.out.println("\t-> " + key.toUpperCase());

        System.out.println();
    }

    @Override
    public void run() {
        HandlerCLI.clearConsole();
        print();
        scene = cHandler.handleInput(options);

        if (scene == null)
            return;

        HandlerCLI.clearConsole();
        scene.run();
        CommandHandler.waitUntilKeyPressed();
    }
}
