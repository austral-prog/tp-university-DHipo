package com.university.solution;

import com.university.model.University;

import java.util.ArrayList;
import java.util.List;

public abstract class Solution {
    protected static String resourcesPath = "src/main/resources/";
    protected static University university;
    protected static List<String> data = new ArrayList<>();
    public abstract void solution();
    public abstract void processData();
    public abstract void exportAsCSV();
}
