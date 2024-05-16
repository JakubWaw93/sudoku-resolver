package com.kodilla.sudoku;

import java.util.List;

public class SolvingMechanics {

    public SudokuBoard singleLoopSudokuSolver(SudokuBoard sudokuBoard) throws NoPossibleValuesException {
        List<SudokuRow> sudokuRows = sudokuBoard.getSudokuRows();
        for (int i = 0; i < sudokuRows.size(); i++) {
            for (int j = 0; j < sudokuRows.get(i).getSudokuElements().size(); j++) {
                SudokuRow sudokuRow = sudokuRows.get(i);
                SudokuElement sudokuElement = sudokuRow.getSudokuElements().get(j);
                if (sudokuElement.getValue() == SudokuElement.EMPTY) {
                    for (int k = 0; k < sudokuRows.size(); k++) {
                        sudokuElement.getPossibleValues().remove(Integer.valueOf(sudokuRow.getSudokuElements().get(k).getValue()));
                        sudokuElement.getPossibleValues().remove(Integer.valueOf(sudokuRows.get(k).getSudokuElements().get(j).getValue()));
                    }
                    if (i < 3) {
                        possibleValuesRemovalFromChosenRowOfSudokuBoxes(0, 3, j, sudokuElement, sudokuRows);
                    } else if (i < 6) {
                        possibleValuesRemovalFromChosenRowOfSudokuBoxes(3, 6, j, sudokuElement, sudokuRows);
                    } else {
                        possibleValuesRemovalFromChosenRowOfSudokuBoxes(6, 9, j, sudokuElement, sudokuRows);
                    }
                    if (sudokuElement.getPossibleValues().size() == 1) {
                        sudokuElement.setValue(sudokuElement.getPossibleValues().get(0));
                    } else if (sudokuElement.getPossibleValues().isEmpty()) {
                        throw new NoPossibleValuesException("No possible values for this field");

                    }
                }
            }
        }
        return sudokuBoard;
    }

    private static void possibleValuesRemovalFromChosenRowOfSudokuBoxes(int l, int x, int j, SudokuElement sudokuElement, List<SudokuRow> sudokuRows) {
        for (int l1 = l; l1 < x; l1++) {
            possibleValuesRemovalFromRowInChosenBox(j, sudokuElement, sudokuRows, l1);
        }
    }

    private static void possibleValuesRemovalFromRowInChosenBox(int j, SudokuElement sudokuElement, List<SudokuRow> sudokuRows, int l) {
        if (j < 3) {
            processPossibleValuesRemovalFromRowInBox(0, 3, sudokuElement, sudokuRows, l);
        } else if (j < 6) {
            processPossibleValuesRemovalFromRowInBox(3, 6, sudokuElement, sudokuRows, l);
        } else {
            processPossibleValuesRemovalFromRowInBox(6, 9, sudokuElement, sudokuRows, l);
        }
    }

    private static void processPossibleValuesRemovalFromRowInBox(int k, int x, SudokuElement sudokuElement, List<SudokuRow> sudokuRows, int l) {
        for (int k1 = k; k1 < x; k1++) {
            sudokuElement.getPossibleValues().remove(Integer.valueOf(sudokuRows.get(l).getSudokuElements().get(k1).getValue()));
        }
    }
}