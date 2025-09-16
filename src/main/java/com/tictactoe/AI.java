package com.tictactoe;

import java.util.Random;

public class AI {
    private final Random random = new Random();

    private int difficulty = 1; // 1 = easy, 2 = normal, 3 = hard


    public int getDifficulty()
    {
        return difficulty;
    }

    public void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }

    public int computeMove(Field field) {
        if (!field.hasEmptyCell()) return -1;

        // Легкий — случайный
        if (difficulty == 1) {
            return field.getEmptyRandomCell();
        }

        // Средний — чаще случайный
        if (difficulty == 2 && random.nextInt(100) < 75) {
            return field.getEmptyRandomCell();
        }

        // Сложный — minimax
        return findBestMove(field);
    }

    private int findBestMove(Field field) {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < 9; i++) {
            if (field.isEmpty(i)) {
                field.setCell(i, Sign.NOUGHT);
                int score = minimax(field, false);
                field.setCell(i, Sign.EMPTY);

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    private int minimax(Field field, boolean isMaximizing) {
        Sign winner = field.checkWin();
        if (winner == Sign.NOUGHT) return 1;
        if (winner == Sign.CROSS) return -1;
        if (!field.hasEmptyCell()) return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (field.isEmpty(i)) {
                    field.setCell(i, Sign.NOUGHT);
                    bestScore = Math.max(bestScore, minimax(field, false));
                    field.setCell(i, Sign.EMPTY);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (field.isEmpty(i)) {
                    field.setCell(i, Sign.CROSS);
                    bestScore = Math.min(bestScore, minimax(field, true));
                    field.setCell(i, Sign.EMPTY);
                }
            }
            return bestScore;
        }
    }
}

