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
        if (!board.isFull()) {
            System.out.println("Making move level \"easy\"");
            int index = random.nextInt(board.getAvailableSpots().size());
            Integer[] spot = board.getAvailableSpots().remove(index);
            int i = spot[0];
            int j = spot[1];
            board.update(i, j, this.getToken());
        }
    }
}

