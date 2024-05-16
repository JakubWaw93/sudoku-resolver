package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.List;

public final class SudokuBoard extends Prototype<SudokuBoard> {

    private List<SudokuRow> sudokuRows = new ArrayList<>();


    public SudokuBoard() {
        for (int i = 0; i < 9; i++) {
            sudokuRows.add(new SudokuRow());
        }
    }

    public SudokuBoard deepCopy() throws CloneNotSupportedException {
        SudokuBoard clonedSudokuBoard = super.clone();
        clonedSudokuBoard.sudokuRows = new ArrayList<>();
        for (SudokuRow sudokuRow : sudokuRows) {
            SudokuRow clonedRow = new SudokuRow();
            clonedRow.getSudokuElements().clear();
            for (SudokuElement sudokuElement : sudokuRow.getSudokuElements()) {
                SudokuElement clonedElement = new SudokuElement(sudokuElement.getValue());
                clonedElement.setPossibleValues(new ArrayList<>(sudokuElement.getPossibleValues()));
                clonedRow.getSudokuElements().add(clonedElement);
            }
            clonedSudokuBoard.getSudokuRows().add(clonedRow);
        }
        return clonedSudokuBoard;
    }

    public List<SudokuRow> getSudokuRows() {
        return sudokuRows;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("    ||");
        for (int j = 1; j <= sudokuRows.size(); j++) {
            if (j == 3 || j == 6 || j == 9) {
                sb.append(" (").append(j).append(") ||");
            } else {
                sb.append(" (").append(j).append(") |");
            }
        }
        sb.append("\n");
        for (int k = 0; k < sudokuRows.size(); k++) {
            sb.append("(").append(k + 1).append(")").append(" ||");
            for (int l = 0; l < sudokuRows.size(); l++) {
                if (l == 2 || l == 5 || l == 8) {
                    sb.append("  ").append(sudokuRows.get(k).getSudokuElements().get(l)).append("  ||");
                } else {
                    sb.append("  ").append(sudokuRows.get(k).getSudokuElements().get(l)).append("  |");
                }
            }
            sb.append("\n");
            if (k == 2 || k == 5) {
                sb.append("----------------------------------------------------------------\n");
            }

        }
        return sb.toString();
    }

    public void setSudokuRows(List<SudokuRow> sudokuRows) {
        this.sudokuRows = sudokuRows;
    }
}
