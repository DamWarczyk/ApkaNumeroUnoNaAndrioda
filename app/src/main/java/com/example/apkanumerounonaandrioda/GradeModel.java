package com.example.apkanumerounonaandrioda;

public class GradeModel {
    private String subjecName;
    private int value;

    public GradeModel(String subjecName, int value) {
        this.subjecName = subjecName;
        this.value = value;
    }

    public String getSubjecName() {
        return subjecName;
    }

    public void setSubjecName(String subjecName) {
        this.subjecName = subjecName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
