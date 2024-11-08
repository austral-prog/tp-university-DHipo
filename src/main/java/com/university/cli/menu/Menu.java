package com.university.cli.menu;

import java.util.HashMap;
import java.util.Map;

import com.university.model.University;
import com.university.cli.CommandHandler;
import com.university.services.Service;

public abstract class Menu {
    protected University university = null;
    protected Map<String, Runnable> options = new HashMap<String, Runnable>();
    protected Runnable scene = null;
    protected CommandHandler cHandler = new CommandHandler();

    public Menu(University university) {
        this.university = university;
    }

    public Service getService() {return null;};

    public Runnable getScene() {
        return scene;
    }

    public Map<String, Runnable> getOptions() {
        return this.options;
    }

    public abstract void print();

    public abstract void run();
}
