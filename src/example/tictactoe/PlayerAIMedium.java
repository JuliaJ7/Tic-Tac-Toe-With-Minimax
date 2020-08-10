package example.tictactoe;

import java.util.Random;

public class PlayerAIMedium extends Player {
    static Random random;

    public PlayerAIMedium(char playerToken) {
        super(playerToken);
        random = new Random();
    }

    @Override
    public void makeMove(GameBoard board) {
        if (!board.isFull()) {
            System.out.println("Making move level \"medium\"");
            Integer[] spot = new Integer[2];
            int i, j = 0;

            if (board.isSpotAvailableWithTwoInARow(spot)) {
                i = spot[0];
                j = spot[1];
            } else {
                int index = random.nextInt(board.getAvailableSpots().size());
                Integer[] randomSpot = board.getAvailableSpots().remove(index);
                i = randomSpot[0];
                j = randomSpot[1];
            }
            board.update(i, j, this.getToken());
        }
    }
}
