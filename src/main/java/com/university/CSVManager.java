package com.university;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVManager {

    /**
     * Return a map with the name of the columns as keys
     * and the rest of the column in the value of each
     * key.
     **/
    public static Map<String, List<String>> getDataFromFileAsMap(final String _fileName)
    {
        try {
            List<String> buffer = new BufferedReader(new FileReader(_fileName)).lines().toList();
            List<String[]> formatedLines = buffer.stream().map(e -> e.split(",")).toList();
            String[] keys = formatedLines.getFirst();

            Map<String, List<String>> data = new LinkedHashMap<>();

            for (int i = 1; i < formatedLines.size(); i++)
                for (int x = 0; x < keys.length; x++){
                    data.computeIfAbsent(keys[x], k -> new ArrayList<>());
                    data.get(keys[x]).add(formatedLines.get(i)[x]);
                }

            return data;
        }catch (IOException exception) {
            System.out.printf("\n\t\t--- Error ---\n\t-> %s\n\t-> %s", exception.getMessage(), exception.getCause());
            return null;
        }
    }

    public static List<String> getDataFromFileAsList(final String _fileName)
    {
        try {
            return new BufferedReader(new FileReader(_fileName)).lines().toList();
        }catch (IOException exception) {
            System.out.printf("\n\t\t--- Error ---\n\t-> %s\n\t-> %s", exception.getMessage(), exception.getCause());
            return null;
        }
    }
}