package com.kodilla.sudoku;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuTestSuite {

    SolvingMechanics solvingMechanics;
    SudokuBoard sudokuBoard;
    SudokuGame sudokuGame = new SudokuGame();

    @BeforeEach
    void createABoard() {
        solvingMechanics = new SolvingMechanics();
        sudokuBoard = new SudokuBoard();
        sudokuBoard.getSudokuRows().get(0).getSudokuElements().get(7).setValue(7);
        sudokuBoard.getSudokuRows().get(6).getSudokuElements().get(6).setValue(4);
        sudokuBoard.getSudokuRows().get(0).getSudokuElements().get(8).setValue(3);
        sudokuBoard.getSudokuRows().get(1).getSudokuElements().get(1).setValue(8);
        sudokuBoard.getSudokuRows().get(1).getSudokuElements().get(3).setValue(6);
        sudokuBoard.getSudokuRows().get(1).getSudokuElements().get(6).setValue(5);
        sudokuBoard.getSudokuRows().get(2).getSudokuElements().get(1).setValue(1);
        sudokuBoard.getSudokuRows().get(2).getSudokuElements().get(5).setValue(7);
        sudokuBoard.getSudokuRows().get(2).getSudokuElements().get(6).setValue(8);
        sudokuBoard.getSudokuRows().get(2).getSudokuElements().get(0).setValue(2);
        sudokuBoard.getSudokuRows().get(2).getSudokuElements().get(2).setValue(3);
        sudokuBoard.getSudokuRows().get(3).getSudokuElements().get(2).setValue(7);
        sudokuBoard.getSudokuRows().get(4).getSudokuElements().get(2).setValue(9);
        sudokuBoard.getSudokuRows().get(5).getSudokuElements().get(2).setValue(6);
        sudokuBoard.getSudokuRows().get(2).getSudokuElements().get(3).setValue(9);
        sudokuBoard.getSudokuRows().get(2).getSudokuElements().get(4).setValue(5);
        sudokuBoard.getSudokuRows().get(3).getSudokuElements().get(0).setValue(1);
        sudokuBoard.getSudokuRows().get(3).getSudokuElements().get(4).setValue(4);
//        sudokuBoard.getSudokuRows().get(3).getSudokuElements().get(6).setValue(6);
//        sudokuBoard.getSudokuRows().get(4).getSudokuElements().get(6).setValue(7);
//        sudokuBoard.getSudokuRows().get(5).getSudokuElements().get(1).setValue(2);
//        sudokuBoard.getSudokuRows().get(5).getSudokuElements().get(3).setValue(5);
//        sudokuBoard.getSudokuRows().get(5).getSudokuElements().get(6).setValue(9);
//        sudokuBoard.getSudokuRows().get(6).getSudokuElements().get(0).setValue(3);
//        sudokuBoard.getSudokuRows().get(6).getSudokuElements().get(3).setValue(7);
//        sudokuBoard.getSudokuRows().get(7).getSudokuElements().get(0).setValue(4);
//        sudokuBoard.getSudokuRows().get(7).getSudokuElements().get(5).setValue(5);
//        sudokuBoard.getSudokuRows().get(7).getSudokuElements().get(6).setValue(3);
//        sudokuBoard.getSudokuRows().get(7).getSudokuElements().get(7).setValue(9);
//        sudokuBoard.getSudokuRows().get(8).getSudokuElements().get(0).setValue(9);
//        sudokuBoard.getSudokuRows().get(8).getSudokuElements().get(3).setValue(2);
        sudokuBoard.getSudokuRows().get(8).getSudokuElements().get(6).setValue(1);
        sudokuBoard.getSudokuRows().get(2).getSudokuElements().get(7).setValue(4);
        sudokuBoard.getSudokuRows().get(7).getSudokuElements().get(2).setValue(2);
        sudokuBoard.getSudokuRows().get(7).getSudokuElements().get(3).setValue(1);
        sudokuBoard.getSudokuRows().get(7).getSudokuElements().get(4).setValue(8);
    }

    @Test
    void sudokuResolverUpdatingPossibleValuesTest() {
        //Given
        System.out.println(sudokuBoard);
        SudokuElement sudokuElement = sudokuBoard.getSudokuRows().get(0).getSudokuElements().get(3);

        //When
        System.out.println(sudokuElement.getPossibleValues());
        try {
            solvingMechanics.singleLoopSudokuSolver(sudokuBoard);
        }catch (NoPossibleValuesException e) {
            SudokuConsole.exceptionMessage(e.getMessage());
        }


        //Then
        System.out.println(sudokuElement.getPossibleValues());
        System.out.println(sudokuBoard);
    }

    @Test
    void sudokuResolverTest() throws IncorrectSudokuException {
        //Given
        System.out.println(sudokuBoard);
        sudokuGame.resolveSudoku(sudokuBoard);
        System.out.println(sudokuBoard);
    }

    @Test
    void backTrackTest() {
        //Given
        //When
        BackTrack backTrack = new BackTrack(0, 0, 1);
        backTrack.saveBoardCopy(sudokuBoard);
        SudokuBoard sudokuBoardCopy = backTrack.getSudokuBoardCopy();
        int i = backTrack.getiOfGuessingElement();
        int j = backTrack.getjOfGuessingElement();
        int value = backTrack.getGuessingValueOfElement();
        //Then
        assertEquals(0, i);
        assertEquals(0, j);
        assertEquals(1, value);
        assertEquals(sudokuBoard.getSudokuRows().get(5).getSudokuElements().get(5),
                sudokuBoardCopy.getSudokuRows().get(5).getSudokuElements().get(5));
        System.out.println(sudokuBoard);
        System.out.println(sudokuBoardCopy);
    }


    @Test
    void areBoardsTheSameTest() {
        //Given
        BackTrack backTrack = new BackTrack(0,0,1);
        backTrack.saveBoardCopy(sudokuBoard);
        SudokuBoard sudokuBoard2;
        sudokuBoard2 = backTrack.getSudokuBoardCopy();
        try {
            solvingMechanics.singleLoopSudokuSolver(sudokuBoard);
        }catch (NoPossibleValuesException e) {
            SudokuConsole.exceptionMessage(e.getMessage());
        }
        //When
        boolean areTheSame1 = sudokuGame.areBoardsTheSame(sudokuBoard, sudokuBoard);
        boolean areTheSame2 = sudokuGame.areBoardsTheSame(sudokuBoard, sudokuBoard2);
        //Then
        System.out.println(sudokuBoard);
        System.out.println(sudokuBoard2);
        assertTrue(areTheSame1);
        assertFalse(areTheSame2);


    }

    @Test
    void areElementsTheSameTest() {
        //Given
        BackTrack backTrack = new BackTrack(0,0,0);
        backTrack.saveBoardCopy(sudokuBoard);
        SudokuBoard sudokuBoardCopy = backTrack.getSudokuBoardCopy();
        //When
        sudokuBoardCopy.getSudokuRows().get(0).getSudokuElements().get(0).getPossibleValues().remove(0);
        SudokuElement sudokuElement1 = sudokuBoard.getSudokuRows().get(0).getSudokuElements().get(0);
        SudokuElement sudokuElement2 = sudokuBoardCopy.getSudokuRows().get(0).getSudokuElements().get(0);
        //Then
        assertNotEquals(sudokuElement1, sudokuElement2);
    }

    @Test
    void createBackTrackMethodTest() {
        //Given
        List<BackTrack> backTracks = new ArrayList<>();
        //When
        SudokuGame.createBackTrack(sudokuBoard, backTracks);
        System.out.println(backTracks.get(0).getSudokuBoardCopy());
        System.out.println(backTracks.get(0).getiOfGuessingElement());
        System.out.println(backTracks.get(0).getjOfGuessingElement());
        System.out.println(backTracks.get(0).getGuessingValueOfElement());
    }

    @Test
    void backTrackSudokuBoardReloadMethodTest() throws IncorrectSudokuException {
        //Given
        List<BackTrack> backTracks = new ArrayList<>();
        BackTrack backTrack = new BackTrack(0,4,8);
        backTrack.saveBoardCopy(sudokuBoard);
        backTracks.add(backTrack);
        //When
        SudokuBoard reloadedBoard = SudokuGame.backTrackSudokuBoardReload(backTracks);
        //Then
        assertEquals(0, backTracks.size());
        assertFalse(sudokuGame.areBoardsTheSame(sudokuBoard,reloadedBoard));
        System.out.println(sudokuBoard);
        System.out.println(reloadedBoard);
    }
}
