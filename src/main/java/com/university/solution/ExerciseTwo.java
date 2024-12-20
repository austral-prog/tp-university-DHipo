package com.university.solution;

import com.university.model.Evaluation;
import com.university.model.University;
import com.university.csv.CSVManager;
import com.university.model.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.university.solution.ExerciseTwo.IndexData.*;
import static java.lang.Float.parseFloat;

public class ExerciseTwo extends Solution {

    /* --- exercise two : Variables --- */
    private static final String solutionFile_2 = resourcesPath.concat("solution_2.csv");
    private static final String inputFile_2 = resourcesPath.concat("input_2.csv");

    // Student,Subject,Evaluation_Type,Evaluation_Name,Exercise_Name,Grade
    public enum IndexData {
        STUDENT_NAME(0),
        SUBJECT_NAME(1),
        EVALUATION_TYPE(2),
        EVALUATION_NAME(3),
        EXERCISE_NAME(4),
        GRADE(5);

        public final int value;

        IndexData(int value) {
            this.value = value;
        }
    }

    public ExerciseTwo(University _university) {
        university = _university;
    }

    /* --- private : Methods - Exercise Two --- */
    private static Evaluation addEvaluationToStudent(Student _student, String[] _line) {
        Evaluation eval = new Evaluation();

        eval.setName(_line[EVALUATION_NAME.value]);
        eval.setType(_line[EVALUATION_TYPE.value]);
        eval.setSubject(_line[SUBJECT_NAME.value]);
        eval.setStudent(_line[STUDENT_NAME.value]);

        Map<String, Float> results = new HashMap<>();
        results.put(_line[EXERCISE_NAME.value], parseFloat(_line[GRADE.value]));
        eval.setResults(results);

        _student.addEvaluation(eval);
        return eval;
    }

    /* --- public : Methods - Exercise Two --- */
    @Override
    public void solution() {
        System.out.println("Doing exercise two...\n");
        processData();
        if (data == null) System.out.println("Impossible to process the data!");
        exportAsCSV();
        System.out.println("Exercise two completed!");
    }

    @Override
    public void processData() {
        data = CSVManager.getDataFromFileAsList(inputFile_2);
        if (data == null || data.isEmpty()) return;

        Map<String, Student> students = university.getStudents();
        for (int i = 1; i < data.size(); i++) {
            String[] line = data.get(i).split(",");

            Student student = students.get(line[STUDENT_NAME.value]);

            if (student == null) {
                /// Genero el estudiante, agrego la evaluacion y lo agrego a la universidad
                student = new Student(line[STUDENT_NAME.value]);
                students.put(student.getName(), student);
                students.get(student.getName()).addEvaluation(addEvaluationToStudent(student, line));
                continue;
            }

            university.addEvaluation(
                    addEvaluationToStudent(student, line)
            );
        }
    }

    @Override
    public void exportAsCSV() {
        System.out.print("Exporting the solution into solution.csv file --> ");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(solutionFile_2));
            Map<String, List<Evaluation>> evaluations = university.getEvaluations();
            List<String> subjectsSorted = evaluations.keySet().stream().sorted().toList();

            writer.write("Subject_Name,Evaluation_Name,Student_Name,Grade\n");

            for (String subject : subjectsSorted) {
                List<Evaluation> evaluationSorted = evaluations.get(subject)
                        .stream().sorted().toList();

                for (Evaluation e : evaluationSorted)
                    writer.write(
                            String.format("%s,%s,%s,%s\n", subject, e.getName(), e.getStudent(), e.getAverageByType())
                    );

            }

            writer.close();
            System.out.println("Success!");

        } catch (IOException e) {
            System.out.println("Error!");
            System.out.println(e.getMessage());
        }
    }
}