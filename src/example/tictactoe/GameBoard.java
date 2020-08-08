package example.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;

public class GameBoard {
    public static final int DIMENSION = 3;
    public static final char EMPTY_CELL = '_';

    private final char[][] board;
    private final ArrayList<Integer[]> available;

    public ArrayList<Integer[]> getAvailable() {
        return available;
    }

    public GameBoard() {
        this.board = new char[][]{
                {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL},
                {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL},
                {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL}
        };

        this.available = new ArrayList<Integer[]>();

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j< DIMENSION; j++){
                Integer[] spot = new Integer[2];
                spot[0] = i;
                spot[1] = j;
                available.add(spot);
            }
        }
    }

    public void update(int i, int j, char token) {
        board[i][j] = token;
    }

    public void draw() {
        System.out.println("---------");
        for (int i = 0; i < DIMENSION; i++) {
            System.out.print("| ");
            for (int j = 0; j < DIMENSION; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public boolean isAvailable(int i, int j) {
        return board[i][j] == EMPTY_CELL;
    }

    public void removeFromAvailable(int i, int j) {
        if (available.size() > 0) {
            Integer[] spot = new Integer[2];
            spot[0] = i;
            spot[1] = j;
            available.remove(spot);
        }
    }

    public void addToAvailable(int i, int j) {
        Integer[] spot = new Integer[2];
        spot[0] = i;
        spot[1] = j;
        available.add(spot);
    }

    public boolean isFull() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++){
                if (board[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSpotAvailable(Integer[] spot) {
        for (Integer[] availableSpot : available) {
            if (Arrays.equals(availableSpot, spot)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSpotAvailableWithTwoInARow(Integer[] spot) {
        return checkHorizontals(spot) || checkVerticals(spot) || checkDiagonals(spot);
    }

    // check horizontals and return true if there is an empty spot with two tokens in a row
    private boolean checkHorizontals(Integer[] spot) {
        for(int i = 0; i < DIMENSION; i++) {
            if (board[i][0] == board[i][1] && board[i][0] != EMPTY_CELL && board[i][2] == EMPTY_CELL) {
                spot[0] = i;
                spot[1] = 2;
                return true;
            }

            if (board[i][0] == board[i][2] && board[i][0] != EMPTY_CELL && board[i][1] == EMPTY_CELL) {
                spot[0] = i;
                spot[1] = 1;
                return true;
            }

            if (board[i][1] == board[i][2] && board[i][1] != EMPTY_CELL && board[i][0] == EMPTY_CELL) {
                spot[0] = i;
                spot[1] = 0;
                return true;
            }
        }
        return false;
    }

    private boolean checkVerticals(Integer[] spot) {
        for (int j = 0; j < DIMENSION; j++) {
            if (board[0][j] == board[1][j] && board[0][j] != EMPTY_CELL && board[2][j] == EMPTY_CELL) {
                spot[0] = 2;
                spot[1] = j;
                return true;
            }

            if (board[0][j] == board[2][j] && board[0][j] != EMPTY_CELL && board[1][j] == EMPTY_CELL) {
                spot[0] = 1;
                spot[1] = j;
                return true;
            }

            if (board[1][j] == board[2][j] && board[1][j] != EMPTY_CELL && board[0][j] == EMPTY_CELL) {
                spot[0] = 0;
                spot[1] = j;
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(Integer[] spot) {
        if (board[0][0] == board[1][1] && board[0][0] != EMPTY_CELL && board[2][2] == EMPTY_CELL) {
            spot[0] = 2;
            spot[1] = 2;
            return true;
        }

        if (board[0][0] == board[2][2] && board[0][0] != EMPTY_CELL && board[1][1] == EMPTY_CELL) {
            spot[0] = 1;
            spot[1] = 1;
            return true;
        }

        if (board[2][0] == board[0][2] && board[2][0] != EMPTY_CELL && board[1][1] == EMPTY_CELL) {
            spot[0] = 1;
            spot[1] = 1;
            return true;
        }

        if (board[1][1] == board[2][2] && board[1][1] != EMPTY_CELL && board[0][0] == EMPTY_CELL) {
            spot[0] = 0;
            spot[1] = 0;
            return true;
        }

        if (board[0][2] == board[1][1] && board[1][1] != EMPTY_CELL && board[2][0] == EMPTY_CELL) {
            spot[0] = 2;
            spot[1] = 0;
            return true;
        }

        if (board[1][1] == board[2][0] && board[1][1] != EMPTY_CELL && board[0][2] == EMPTY_CELL) {
            spot[0] = 0;
            spot[1] = 2;
            return true;
        }

        return false;
    }

    public GameState determineState() {

        if (hasWinCondition('X')) {
            return GameState.X_WINS;
        }

        if (hasWinCondition('O')) {
            return GameState.O_WINS;
        }

        if (available.size() == 0) {
            return GameState.DRAW;
        } else {
            return GameState.GAME_NOT_FINISHED;
        }
    }

    public boolean hasWinCondition(char character) {
        for (int i = 0; i < DIMENSION; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] == character) {
                return true;
            }
        }

        for (int i = 0; i < DIMENSION; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] == character) {
                return true;
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == character) {
            return true;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] == character) {
            return true;
        }

        return false;
    }
}
