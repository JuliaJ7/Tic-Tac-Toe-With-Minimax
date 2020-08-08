package example.tictactoe;

public class PlayerAIHard extends Player {
    public PlayerAIHard(char playerToken) {
        super(playerToken);
    }

    @Override
    public void makeMove(GameBoard board) {
        if (board.getAvailable().size() > 0) {
            System.out.println("Making move level \"hard\"");
            Integer[] spot = {0,0};
            int bestScore = -100;
            int score = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isAvailable(i, j)) {
                        board.update(i, j, this.getToken());
                        board.removeFromAvailable(i, j);
                        score = minimax(board, 0, false);
                        board.update(i, j, '_');
                        board.addToAvailable(i, j);

                        if (score > bestScore) {
                            bestScore = score;
                            spot[0] = i;
                            spot[1] = j;
                        }
                    }
                }
            }
            board.getAvailable().remove(spot);
            board.update(spot[0], spot[1], this.getToken());
        }
    }

    private int minimax(GameBoard board, int depth, boolean isMaximizing) {

        char ai = this.getToken();
        char opponent = this.getToken() == 'X' ? 'O' : 'X';

        if (board.hasWinCondition(ai)) {
            return 10;
        }

        if (board.hasWinCondition(opponent)) {
            return -10;
        }

        if (board.isFull()) {
            return 0;
        }

        int score = 0;
        int bestScore = 0;

        if (isMaximizing) {
            bestScore = -100;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isAvailable(i, j)) {
                        board.update(i, j, ai);
                        board.removeFromAvailable(i, j);
                        score = minimax(board, depth + 1, false);
                        board.update(i, j, '_');
                        board.addToAvailable(i, j);
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
        } else {
            bestScore = 100;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++){
                    if (board.isAvailable(i, j)) {
                        board.update(i, j, opponent);
                        board.removeFromAvailable(i, j);
                        score = minimax(board, depth + 1, true);
                        board.update(i, j, '_');
                        board.addToAvailable(i, j);
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }
}
