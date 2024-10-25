package com.university.csv;

import java.util.List;

public interface CSVWriter {
    void writeCSV(CSVWritable writable);
    void writeCSV(List<CSVWritable> writableList);
}
