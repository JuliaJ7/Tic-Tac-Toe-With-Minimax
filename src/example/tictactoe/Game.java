package example.tictactoe;

public class Game {
    private final Player[] players;
    private final GameBoard board;

    public Game(String playerTypeX, String playerTypeO) {
        players = new Player[2];

        if (playerTypeX.equals("easy")) {
            this.players[0] = new PlayerAIEasy('X');
        } else if (playerTypeX.equals("medium")) {
            this.players[0] = new PlayerAIMedium('X');
        } else if (playerTypeX.equals("hard")) {
            this.players[0] = new PlayerAIHard('X');
        } else {
            this.players[0] = new PlayerUser('X');
        }

        if (playerTypeO.equals("easy")) {
            this.players[1] = new PlayerAIEasy('O');
        } else if (playerTypeO.equals("medium")) {
            this.players[1] = new PlayerAIMedium('O');
        } else if (playerTypeO.equals("hard")) {
            this.players[1] = new PlayerAIHard('O');
        } else {
            this.players[1] = new PlayerUser('O');
        }

        this.board = new GameBoard();
    }

    public void run() {
        int currentPlayer = 0;

        for(;;) {
            players[currentPlayer].makeMove(board);
            board.draw();
            GameState state = board.determineState();

            if (state == GameState.DRAW || state == GameState.X_WINS || state == GameState.O_WINS) {
                printMessage(state);
                return;
            }

            currentPlayer = (currentPlayer + 1) % players.length;
        }
    }

    private void printMessage(GameState state) {
        if (state == GameState.GAME_NOT_FINISHED) {
            System.out.println("Game not finished");
        }

        if (state == GameState.X_WINS) {
            System.out.println("X wins");
        }

        if (state == GameState.O_WINS) {
            System.out.println("O wins");
        }

        if (state == GameState.DRAW) {
            System.out.println("Draw");
        }
    }
}
