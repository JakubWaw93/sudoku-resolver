package com.kodilla.sudoku;

import java.util.Scanner;


public class SudokuConsole {

    private static final Scanner input = new Scanner(System.in);


    public static String chooseAnElementAndNumber() {
        System.out.println("Choose a row (1-9), an element (1-9), a number to put in (1-9)" +
                "\nor 'x' to end creation of sudoku board: ");
        return input.nextLine();
    }

    public static void showSudokuBoard(SudokuBoard sudokuBoard) {
        System.out.println(sudokuBoard);
    }

    public static void exceptionMessage(String message) {
        System.out.println("Exception message: " + message);
    }

    public static String solveNextSudoku() {
        System.out.println("Do You want to solve another Sudoku? 'Y/N'");
        return input.nextLine();
    }

    public static void howDifficult(String difficulty) {
        System.out.println("That was " + difficulty);
    }



}
