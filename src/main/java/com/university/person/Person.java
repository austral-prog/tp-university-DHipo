package com.university.person;

import com.university.csv.CSVWritable;

import java.util.List;

public abstract class Person implements CSVWritable {

    protected String name;
    protected String email;
    protected int id;

    public Person(){};

    public String getName() {return name;}

    @Override
    public abstract List<String> toCSV();

}
