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
        if (board.getAvailable().size() > 0) {
            System.out.println("Making move level \"medium\"");
            Integer[] spot = new Integer[2];
            int i, j = 0;

            if (board.isSpotAvailableWithTwoInARow(spot)) {
                board.getAvailable().remove(spot);
                i = spot[0];
                j = spot[1];
            } else {
                int index = random.nextInt(board.getAvailable().size());
                Integer[] randomSpot = board.getAvailable().remove(index);
                i = randomSpot[0];
                j = randomSpot[1];
            }
            board.update(i, j, this.getToken());
        }
    }
}
