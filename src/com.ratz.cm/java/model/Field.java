package model;


import java.util.ArrayList;
import java.util.List;

public class Field {

    private final int row;
    private final int col;

    private boolean openField;
    private boolean minedField;
    private boolean markedField;

    private List<Field> neighbors = new ArrayList<Field>();



    Field(int row, int col) {
        this.col = col;
        this.row = row;
    }

    boolean addNeighbor(Field neighbor) {

        boolean unmatchedRow = row != neighbor.row;
        boolean unmatchedCol = col != neighbor.col;
        boolean diagonal = unmatchedCol && unmatchedRow;

        int deltaRow = Math.abs(row - neighbor.row);
        int deltaCol = Math.abs(col - neighbor.col);

        int deltaTotal = deltaCol + deltaRow;

        if (deltaTotal == 1 && !diagonal) {
            neighbors.add(neighbor);
            return true;

        } else if (deltaTotal == 2 && diagonal) {
            neighbors.add(neighbor);
            return true;

        } else {
            return false;
        }

    }

}
