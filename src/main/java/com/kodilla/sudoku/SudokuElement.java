package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class SudokuElement {

    private int value;
    public static final int EMPTY = -1;
    private  List<Integer> possibleValues = new ArrayList<>();

    public SudokuElement(int value) {
        this.value = value;
        for (int i = 1; i < 10; i++) {
            possibleValues.add(i);
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Integer> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(List<Integer> possibleValues) {
        this.possibleValues = possibleValues;
    }

    @Override
    public String toString() {
        String result;
        if (value == EMPTY) {
            result = " ";
        } else {
            result = String.valueOf(value);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SudokuElement that = (SudokuElement) o;
        return value == that.value && Objects.equals(possibleValues, that.possibleValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, possibleValues);
    }
}
