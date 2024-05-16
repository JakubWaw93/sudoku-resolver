package com.kodilla.sudoku;

import java.util.Objects;

public class SudokuBoardCreator {

    public static SudokuBoard setVisibleNumbers(SudokuBoard sudokuBoard) throws WrongCommendException {
        boolean sudokuBoardCreated = false;
        while (!sudokuBoardCreated) {
            try {
                sudokuBoardCreated = isSudokuBoardCreated(sudokuBoard, sudokuBoardCreated);
            } catch (WrongCommendException e) {
                SudokuConsole.exceptionMessage(e.getMessage());
                sudokuBoardCreated = true;
                setVisibleNumbers(sudokuBoard);
            }
        }
    return sudokuBoard;
    }

    private static boolean isSudokuBoardCreated(SudokuBoard sudokuBoard, boolean sudokuBoardCreated) throws WrongCommendException {
        String userChoice;
        int line;
        int element;
        int number;
        userChoice = SudokuConsole.chooseAnElementAndNumber();
        try {
            if (Objects.equals(userChoice, "x")) {
                sudokuBoardCreated = true;
            } else if (Integer.parseInt(userChoice) < 1000 && Integer.parseInt(userChoice) > 110) {
                line = (Integer.parseInt(userChoice.substring(0, 1))) - 1;
                element = (Integer.parseInt(userChoice.substring(1, 2))) - 1;
                number = Integer.parseInt(userChoice.substring(2));
                sudokuBoard.getSudokuRows().get(line).getSudokuElements().get(element).setValue(number);
                SudokuConsole.showSudokuBoard(sudokuBoard);
            } else {
                throw new WrongCommendException("Choose numbers between 111 and 999 to set a Sudoku, or 'x' to end creating a board");
            }
        }catch (NumberFormatException e) {
            SudokuConsole.exceptionMessage("Choose numbers between 111 and 999 to set a Sudoku, or 'x' to end creating a board");
        }
        return sudokuBoardCreated;
    }

    public static boolean isSolvingEnd(boolean end) {
        try {
            String userChoice = SudokuConsole.solveNextSudoku();
            if (userChoice.equalsIgnoreCase("Y")) {
                end = false;
            } else if (userChoice.equalsIgnoreCase("N")) {
                end = true;
            } else {
                throw new WrongCommendException("Press 'Y' to solve next sudoku, or 'N' to end program.");
            }
        } catch (WrongCommendException e) {
            SudokuConsole.exceptionMessage(e.getMessage());
            isSolvingEnd(end);
        }
        return end;
    }
}
