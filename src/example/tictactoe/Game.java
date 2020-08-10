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

        GameState state = board.determineState();

        while(state == GameState.GAME_NOT_FINISHED) {
            players[currentPlayer].makeMove(board);
            board.draw();
            currentPlayer = (currentPlayer + 1) % players.length;
            state = board.determineState();
        }
        printMessage(state);
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
