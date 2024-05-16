package com.kodilla.sudoku;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class SudokuGame {

    private static final List<BackTrack> backTracks = new ArrayList<>();
    private int backTracksCounter = 0;
    private int loopCounter = 0;
    SolvingMechanics solvingMechanics = new SolvingMechanics();


    public static void theSudoku() throws WrongCommendException, IncorrectSudokuException {
        boolean end = false;
        while (!end) {
            SudokuBoard sudokuBoard = new SudokuBoard();
            SudokuBoardCreator.setVisibleNumbers(sudokuBoard);
            SudokuGame game = new SudokuGame();
            game.resolveSudoku(sudokuBoard);
            SudokuConsole.showSudokuBoard(sudokuBoard);
            end = SudokuBoardCreator.isSolvingEnd(end);
        }
    }
    public void resolveSudoku(SudokuBoard sudoku) throws IncorrectSudokuException {
        SudokuBoard sudokuBoard = sudoku;
        boolean end = false;
        while(!end) {
            BackTrack backTrackForChangesCheck = new BackTrack(0, 0, 0);
            backTrackForChangesCheck.saveBoardCopy(sudokuBoard);
            SudokuBoard sudokuBoardForChangesCheck = backTrackForChangesCheck.getSudokuBoardCopy();

            try {
                sudokuBoard = solvingMechanics.singleLoopSudokuSolver(sudokuBoard);
                loopCounter++;
                if (isSudokuBoardCompleted(sudokuBoard)) {
                    sudoku.setSudokuRows(sudokuBoard.getSudokuRows());
                    end = true;
                }
                if (areBoardsTheSame(sudokuBoard, sudokuBoardForChangesCheck)) {
                    createBackTrack(sudokuBoard, SudokuGame.backTracks);
                    backTracksCounter++;
                }
                if (backTracksCounter > 1000) {
                    backTracks.clear();
                }
            } catch (NoPossibleValuesException e) {
                try {
                    sudokuBoard = backTrackSudokuBoardReload(SudokuGame.backTracks);
                } catch (IncorrectSudokuException x) {
                    SudokuConsole.exceptionMessage(x.getMessage());
                    end = true;
                }
            }
        }
        SudokuConsole.howDifficult(SpecifyDifficulty.howDifficult(loopCounter, backTracksCounter)
                + " Loops: " + loopCounter + " BackTracks: " + backTracksCounter);
    }

    public static void createBackTrack(SudokuBoard sudokuBoard, List<BackTrack> backTracks) {
        outerLoop:
        for (int i = 0; i < sudokuBoard.getSudokuRows().size(); i++) {
            SudokuRow sudokuRow = sudokuBoard.getSudokuRows().get(i);
            for (int j = 0; j < sudokuRow.getSudokuElements().size(); j++) {
                SudokuElement sudokuElement = sudokuRow.getSudokuElements().get(j);
                if (sudokuElement.getValue() == SudokuElement.EMPTY && !sudokuElement.getPossibleValues().isEmpty()) {
                    List<Integer> possibleValues = sudokuElement.getPossibleValues();
                    BackTrack backTrack = new BackTrack(i, j, possibleValues.get(0));
                    backTrack.saveBoardCopy(sudokuBoard);
                    backTracks.add(backTrack);
                    sudokuElement.setValue(possibleValues.get(0));
                    break outerLoop;
                }
            }

        }
    }

        private static boolean isSudokuBoardCompleted(SudokuBoard sudokuBoard ){
            for (SudokuRow sudokuRow : sudokuBoard.getSudokuRows()) {
                for (SudokuElement sudokuElement : sudokuRow.getSudokuElements()) {
                    if (sudokuElement.getValue() == SudokuElement.EMPTY) {
                        return false;
                    }
                }
            }
            return true;
        }

   public static SudokuBoard backTrackSudokuBoardReload(List<BackTrack> backTracks) throws IncorrectSudokuException {
       SudokuBoard sudokuBoard = null;
        if (!backTracks.isEmpty() && !backTracks.get(backTracks.size() - 1).getSudokuBoardCopy().getSudokuRows().isEmpty()) {
            BackTrack lastBackTrack = backTracks.remove(backTracks.size() - 1);
            sudokuBoard = lastBackTrack.getSudokuBoardCopy();
            int i = lastBackTrack.getiOfGuessingElement();
            int j = lastBackTrack.getjOfGuessingElement();
            int shootValue = lastBackTrack.getGuessingValueOfElement();
            SudokuElement guessedElement = sudokuBoard.getSudokuRows().get(i).getSudokuElements().get(j);
            guessedElement.getPossibleValues().remove(Integer.valueOf(shootValue));
        } else {
            throw new IncorrectSudokuException ("Incorrect sudoku, no possible solutions.");
        }
        return sudokuBoard;
    }

    public boolean areBoardsTheSame(SudokuBoard sudokuBoard, SudokuBoard sudokuBoardAfterLoop) {
        List<SudokuElement> sudokuBoardFlatElements = sudokuBoard.getSudokuRows().stream()
                .flatMap(sudokuRow -> sudokuRow.getSudokuElements().stream())
                .toList();
        List<SudokuElement> sudokuBoardAfterLoopFlatElements = sudokuBoardAfterLoop.getSudokuRows().stream()
                .flatMap(sudokuRow -> sudokuRow.getSudokuElements().stream())
                .toList();

        return IntStream.range(0, sudokuBoardFlatElements.size())
                .allMatch(i -> sudokuBoardFlatElements.get(i).equals(sudokuBoardAfterLoopFlatElements.get(i)));
    }

}
