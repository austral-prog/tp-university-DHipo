package com.university.solution;

import com.university.University;

public interface Solution {
    String resourcesPath = "src/main/resources/";
    enum IndexData {};
    // con abstract no se pueden hacer funciones estaticas
    void solution (University university);
    void exportAsCSV(University university);
}
