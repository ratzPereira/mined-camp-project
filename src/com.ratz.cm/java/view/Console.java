package view;

import exceptions.ExplosionException;
import exceptions.QuitException;
import model.Table;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Console {

    private Table table;
    private Scanner in = new Scanner(System.in);

    public Console(Table table) {
        this.table = table;

        runGame();
    }

    private void runGame() {
        try {

            boolean keepPlaying = true;

            while (keepPlaying){

                gameLoop();

                System.out.println("Play? (Y/N) ");
                String answer = in.nextLine();

                if("n".equalsIgnoreCase(answer)){

                    keepPlaying = false;

                } else {

                    table.restartGame();
                }
            }
        } catch (QuitException e) {
            System.out.println("Good bye, come again soon!");

        } finally {
            in.close();
        }
    }

    private void gameLoop() {

        try {

            while (!table.win()){

                System.out.println(table.toString());

                String value = catchUserInputValue("Insert the coordinates (x, y): ");

                Iterator<Integer> cord = Arrays.stream(value.split(",")).map(e -> Integer.parseInt(e.trim())).iterator();

                value = catchUserInputValue("Press 1 to Open or press 2 to Mark/Unmark: ");

                if("1".equals(value)){
                    table.openField(cord.next(), cord.next());

                } else if ("2".equals(value)){
                    table.markField(cord.next(),cord.next());
                }
            }

            System.out.println(table);
            System.out.println("Congratulations, you WON!");

        } catch (ExplosionException e){

            System.out.println(table);
            System.out.println("You lose!");
        }

    }

    private String catchUserInputValue(String text) {

        System.out.println(text);
        String inputValue = in.nextLine();

        if("quit".equalsIgnoreCase(inputValue)){
            throw new QuitException();
        }
        return inputValue;
    }
}
