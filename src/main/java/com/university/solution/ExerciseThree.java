package com.university.solution;

public class ExerciseThree extends Solution{

    /* --- exercise two : Variables --- */
    private static final String solutionFile = resourcesPath.concat("solution_3.csv");
    private static final String inputFile = resourcesPath.concat("input_3.csv");

    // Subject_Name,Criteria_Type,Criteria_Value,Evaluation_Name
    enum IndexData {
        SUBJECT_NAME(0),
        CRITERIA_TYPE(1),
        CRITERIA_VALUE(2),
        EVALUATION_NAME(3);

        public int value = 0;
        IndexData(int value) {this.value = value;}
    }

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
    public void processData(){return;}
    @Override
    public void exportAsCSV() {return;}
}
