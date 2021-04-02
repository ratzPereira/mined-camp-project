package model;


import exceptions.ExplosionException;

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


    void changeMark(){

        if(!openField) {
            markedField = !markedField;
        }
    }


    boolean open() {

        if(!openField && !markedField) {
            openField = true;

            if(minedField) {
                throw new ExplosionException();
            }

            if(safeNeighbor()) {
                neighbors.forEach(v -> v.open());
            }
            return true;
        } else {

            return false;
        }
    }


    boolean safeNeighbor(){

        return neighbors.stream().noneMatch(n ->n.minedField);
    }

    void mineField(){

        minedField = true;
    }

    boolean objectiveAcomplished(){

        boolean revealedField = !minedField && openField;
        boolean protectedField = minedField && markedField;

        return revealedField || protectedField;
    }

    long mineInTheNeighborhood() {

        //we check how many minedfields the neighborhood has
        return neighbors.stream().filter(n -> n.minedField).count();
    }

    void restartGame() {
        openField = false;
        minedField = false;
        markedField = false;
    }


    public String toString() {

        if (markedField) {
            return "X";

        } else if (openField && minedField){
            return "*";

        } else if( openField && mineInTheNeighborhood() > 0) {
            return Long.toString(mineInTheNeighborhood());

        } else if(openField) {
            return " ";

        } else {
            return "?";
        }
    }




    //getters
    public boolean isMarked(){
        return markedField;
    }

    public boolean isOpen(){
        return openField;
    }

    public boolean isClosed(){
        return !isOpen();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isMined() {
        return minedField;
    }
}
