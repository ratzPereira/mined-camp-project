import model.Table;
import view.Console;

public class Game {

    public static void main(String[] args) {

        Table table = new Table(5,9,9);

        new Console(table);

        table.openField(3,3);
        table.markField(4,4);
        table.markField(4,5);

        System.out.println(table);
    }
}
