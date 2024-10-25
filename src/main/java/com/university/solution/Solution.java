package com.university.solution;

import com.university.University;

public interface Solution {
    String resourcesPath = "src/main/resources/";
    enum IndexData {};
    // con abstract no se pueden hacer funciones estaticas
    static void solution (University university) {};
    static void exportAsCSV(University university) {};
}
