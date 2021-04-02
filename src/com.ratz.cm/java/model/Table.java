package model;

import exceptions.ExplosionException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Table {

    private int mines;
    private int rows;
    private int cols;

    private final List<Field> fields = new ArrayList<>();

    public Table(int mines, int rows, int cols) {
        this.mines = mines;
        this.rows = rows;
        this.cols = cols;

        generateFields();
        generateNeighbors();
        generateMines();
    }

    public void openField(int row, int col) {

        try {
            fields.stream().filter(f -> f.getRow() == row && f.getCol() == col).findFirst().ifPresent(f ->f.open());

        } catch (ExplosionException e) {
            fields.forEach(f -> f.setOpenField(true));
            throw e;
        }

    }

    public void markField(int row, int col) {
        fields.stream().filter(f -> f.getRow() == row && f.getCol() == col).findFirst().ifPresent(f -> f.changeMark());
    }

    private void generateFields() {

        for (int row = 0; row < rows ; row++) {
            for (int col = 0; col < cols; col++) {
                fields.add(new Field(row,col));
            }
        }
    }


    private void generateNeighbors() {

        for(Field f1: fields){
            for(Field f2: fields)
                f1.addNeighbor(f2);
        }
    }


    private void generateMines() {

        long minesInGame = 0;
        Predicate<Field> mined = Field::isMined;


        do {
            int random =(int)(Math.random() * fields.size());

            minesInGame = fields.stream().filter(mined).count();
            fields.get(random).mineField();

        } while (minesInGame < mines);
    }

    public boolean win() {

        return fields.stream().allMatch(f -> f.objectiveAcomplished());
    }

    public void restartGame() {

        fields.stream().forEach(f -> f.restartGame());
        generateMines();
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        int i = 0;

        sb.append(" ");
        for (int j = 0; j < cols; j++) {
            sb.append( " ");
            sb.append(j);
            sb.append( " ");
        }

        sb.append("\n");

        for (int r = 0; r < rows; r++) {
            sb.append(r);
            for (int c = 0; c < cols; c++) {

                sb.append(" ");
                sb.append(fields.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
