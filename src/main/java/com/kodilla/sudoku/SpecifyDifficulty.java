package com.kodilla.sudoku;

public class SpecifyDifficulty {

    public static String howDifficult(int loopsCounter, int backTracksCounter) {
        if (backTracksCounter == 0) {
            if (loopsCounter < 10) {
                return "Child's play.";
            } else {
                return "Child's work.";
            }
        } else if (backTracksCounter < 5) {
            if (loopsCounter < 20) {
                return "Easy.";
            } else {
                return "Not so easy anymore.";
            }
        } else if (backTracksCounter < 15) {
            if (loopsCounter < 30) {
                return "Good brain training.";
            } else {
                return "Challenging.";
            }
        } else if (backTracksCounter < 30) {
            return "Hard.";
        } else if (backTracksCounter < 40) {
            return "Ultra hard.";
        } else if (backTracksCounter > 40) {
            return "Impossible.";
        }
        return "Something went wrong";
    }
}
