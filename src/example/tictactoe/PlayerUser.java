package example.tictactoe;

import java.util.Scanner;

public class PlayerUser extends Player {

    public PlayerUser(char playerToken) {
        super(playerToken);
    }

    @Override
    public void makeMove(GameBoard board) {
        Scanner scanner = new Scanner(System.in);
        String coordinatesInput;
        String message;
        Integer[] spot = new Integer[2];

        for(;;) {
            System.out.print("Enter the coordinates: ");
            coordinatesInput = scanner.nextLine();
            message = validateMove(coordinatesInput, board, spot);

            if (!message.equals("")) {
                System.out.println(message);
            } else {
                board.getAvailable().remove(spot);
                int i = spot[0];
                int j = spot[1];

                if (board.isAvailable(i, j)) {
                    board.update(i, j, this.getToken());
                }
                return;
            }
        }
    }

    private String validateMove(String coordinatesInput, GameBoard board, Integer[] spot) {
        String[] coordinatesArray = coordinatesInput.trim().split(" ");
        int x = 0;
        int y = 0;

        try {
            x = Integer.parseInt(coordinatesArray[0]);

            if (coordinatesArray.length >= 2) {
                y = Integer.parseInt(coordinatesArray[1]);
            }
        } catch (NumberFormatException e) {
            return "You should enter numbers!";
        }

        spot[0] = 3 - y;
        spot[1] = x - 1;

        if (x < 1 || x > 3 || y < 1 || y > 3) {
            return "Coordinates should be from 1 to 3!";
        } else if (!board.isAvailable(spot[0], spot[1])) {
            return "This cell is occupied! Choose another one!";
        }

        return "";
    }
}
