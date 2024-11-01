package com.university.solution;

import com.university.model.Course;
import com.university.model.Criteria;
import com.university.model.University;
import com.university.csv.CSVManager;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExerciseThree extends Solution{

    /* --- exercise two : Variables --- */
    private static final String solutionFile = resourcesPath.concat("solution_3.csv");
    private static final String inputFile = resourcesPath.concat("input_3.csv");

    // Subject_Name,Criteria_Type,Criteria_Value,Evaluation_Name
    public enum IndexData {
        SUBJECT_NAME(0),
        CRITERIA_TYPE(1),
        CRITERIA_VALUE(2),
        EVALUATION_NAME(3);

        public int value;
        IndexData(int value) {this.value = value;}
    }

    public ExerciseThree(University _university) { university = _university; }
    /*----- Main functions -----*/
    @Override
    public void solution()
    {
        System.out.println("Doing exercise three...");
        processData();

        if (data == null) {
            System.out.println("Impossible to process the data");
            return;
        }

        exportAsCSV();
        System.out.println("Exercise three completed!");
    }
    @Override
    public void processData()
    {
        data = CSVManager.getDataFromFileAsList(inputFile);

        if (data == null) return;

        Map<String, Course> courses = university.getCourses();
        Set<String> criterios = new HashSet<>();
        // Primero le agrego el critero de aprobacion a cada materia
        for (String line : data.subList(1, data.size() -2)) {
            // recorro la cantidad de columnas que hayan en el csv
            int cols = data.getFirst().split(",").length;
            for (int i = 0; i < cols; i++) {
                String[] parts = line.split(",");
                String subjectName = parts[IndexData.SUBJECT_NAME.value];
                if (!courses.containsKey(subjectName))
                    courses.put(subjectName, new Course(subjectName));

                // ahora recorro todos las evaluaciones con las que se aplican el criterio
                // le resto tres por las primeras tres columnas que no son evaluaciones
                for (int j = 0; j < parts.length - 3; j++)
                    courses.get(subjectName).getCriteriaType().put(
                            parts[IndexData.EVALUATION_NAME.value + j],
                            new Criteria(parts[IndexData.CRITERIA_TYPE.value], parts[IndexData.CRITERIA_VALUE.value])
                    );
            }

            System.out.println(courses.values());
        }
    }

    @Override
    public void exportAsCSV() {return;}
}
