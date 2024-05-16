package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public final class SudokuRow {

    private final List<SudokuElement> sudokuElements = new ArrayList<>();

    public SudokuRow() {
        for (int i = 0; i <9; i++) {
            sudokuElements.add(new SudokuElement(SudokuElement.EMPTY));
        }
    }

    public List<SudokuElement> getSudokuElements() {
        return sudokuElements;
    }


}
