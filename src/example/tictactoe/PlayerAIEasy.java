package example.tictactoe;

import java.util.Random;

public class PlayerAIEasy extends Player {
    static Random random;

    public PlayerAIEasy(char playerToken) {
        super(playerToken);
        random = new Random();
    }

    @Override
    public void makeMove(GameBoard board) {
        if (board.getAvailable().size() > 0) {
            System.out.println("Making move level \"easy\"");
            int index = random.nextInt(board.getAvailable().size());
            Integer[] spot = board.getAvailable().remove(index);
            int i = spot[0];
            int j = spot[1];
            board.update(i, j, this.getToken());
        }
    }
}

