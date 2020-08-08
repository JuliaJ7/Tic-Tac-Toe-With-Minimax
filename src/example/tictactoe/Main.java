package example.tictactoe;

import java.util.*;

public class Main {

    public static final String[] OPTIONS = {"user" , "easy", "medium", "hard"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] inputArray;
        String message;

        Game game;

        for(;;) {
            System.out.print("Input command: ");
            input = scanner.nextLine();

            if (input.equals("exit")) {
                return;
            }

            inputArray = input.split(" ");
            message = validateInput(inputArray);

            if(!message.equals("")) {
                System.out.println(message);
            } else {
                game = new Game(inputArray[1], inputArray[2]);
                game.run();
            }
        }
    }

    public static String validateInput(String[] inputArray) {
        if (inputArray.length == 3) {
            if (inputArray[0].equals("start")) {
                if (isInputValid(inputArray[1]) && isInputValid(inputArray[2])) {
                    return "";
                } else {
                    return "Bad parameters!";
                }
            } else {
                return "Bad parameters!";
            }
        } else {
            return "Bad parameters!";
        }
    }

    private static boolean isInputValid(String input) {
        for (String option : OPTIONS) {
            if (input.equals(option)) {
                return true;
            }
        }
        return false;
    }
}
