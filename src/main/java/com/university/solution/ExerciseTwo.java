package com.university.solution;

import com.university.Evaluation;
import com.university.University;
import com.university.csv.CSVManager;
import com.university.person.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.university.solution.ExerciseTwo.IndexData.*;
import static java.lang.Float.parseFloat;

public class ExerciseTwo implements Solution {

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
        IndexData(int value) {this.value = value;}
    };

    /* --- private : Methods - Exercise Two --- */
    private static void extractSubjectsWithGrades(University _university)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(solutionFile_2));
            Map<String, List<Evaluation>> evaluations = _university.getEvaluations();
            List<String> subjectsSorted = evaluations.keySet().stream().sorted().toList();

            writer.write("Subject_Name,Evaluation_Name,Student_Name,Grade\n");

            for (String subject : subjectsSorted){
                List<Evaluation> evaluationSorted = evaluations.get(subject)
                        .stream().sorted().toList();

                for (Evaluation e : evaluationSorted)
                    writer.write(
                            String.format("%s,%s,%s,%s\n",subject, e.getName(), e.getStudent(), e.getAverage())
                    );

            }

            writer.close();

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Evaluation addEvaluationToStudent(Student _student, String[] _line)
    {
        Evaluation eval = new Evaluation();

        eval.setName    (_line[EVALUATION_NAME.value]);
        eval.setType    (_line[EVALUATION_TYPE.value]);
        eval.setSubject (_line[SUBJECT_NAME.value]);
        eval.setStudent (_line[STUDENT_NAME.value]);

        Map<String, Float> results = new HashMap<>();
        results.put(_line[EXERCISE_NAME.value], parseFloat(_line[GRADE.value]));
        eval.setResults(results);

        _student.addEvaluation(eval);
        return eval;
    }

    /* --- public : Methods - Exercise Two --- */
    @Override
    public void solution (University university) {
        List<String> rawData = CSVManager.getDataFromFileAsList(inputFile_2);
        if (rawData == null || rawData.isEmpty()) return;

        Map<String, Student> students = university.getStudents();
        for (int i = 1; i < rawData.size(); i++) {
            String[] line = rawData.get(i).split(",");

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
        extractSubjectsWithGrades(university);
    }

    @Override
    public void exportAsCSV(University university) {

    }
}
