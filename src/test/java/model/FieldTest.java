package model;



import exceptions.ExplosionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class FieldTest {

    private Field field;

    @BeforeEach
    void initialCamp() {
        field = new Field(3,3);
    }

    @Test
    void testIfNeighborDistanceOneLeft(){

        Field neighbor = new Field(3,2);
        boolean isNeighbor = field.addNeighbor(neighbor);

        assertEquals(true,isNeighbor);
    }

    @Test
    void testIfNeighborDistanceOneRight(){

        Field neighbor = new Field(3,4);
        boolean isNeighbor = field.addNeighbor(neighbor);

        assertEquals(true,isNeighbor);
    }
    @Test
    void testIfNeighborDistanceOneUp(){

        Field neighbor = new Field(2,3);
        boolean isNeighbor = field.addNeighbor(neighbor);

        assertEquals(true,isNeighbor);
    }

    @Test
    void testIfNeighborDistanceOneDown(){

        Field neighbor = new Field(4,3);
        boolean isNeighbor = field.addNeighbor(neighbor);

        assertEquals(true,isNeighbor);
    }

    @Test
    void testIfNeighborDistanceDiagonal(){

        Field neighbor = new Field(2,2);
        boolean isNeighbor = field.addNeighbor(neighbor);

        assertEquals(true,isNeighbor);
    }

    @Test
    void testIfNotNeighbor(){

        Field neighbor = new Field(5,2);
        boolean isNeighbor = field.addNeighbor(neighbor);

        assertEquals(false,isNeighbor);
    }

    @Test
    void changeDefaultMarkTest(){

        assertFalse(field.isMarked());
    }

    @Test
    void changeMarkTest(){

        field.changeMark();
        assertTrue(field.isMarked());
    }

    @Test
    void changeMarkTestWithTwoCalls(){

        field.changeMark();
        field.changeMark();
        assertFalse(field.isMarked());
    }

    @Test
    void openNotMinedNotMarkedFieldTest() {

        assertTrue(field.open());
    }

    @Test
    void openNotMinedButMarkedFieldTest() {

        field.changeMark();
        assertFalse(field.open());
    }

    @Test
    void openMinedAndMarkedFieldTest() {

        field.changeMark();
        field.mineField();
        assertFalse(field.open());
    }

    @Test
    //we expect to throw exception if is an explosion. we can use assertThrows, pass the exception and the method that will throw
    void openMinedAndNotMarkedFieldTest() {

        field.mineField();

        assertThrows(ExplosionException.class, () -> {
            field.open();
        });
    }

    @Test
    void openWithNeighbor(){

        Field neighbor1 = new Field(2,2);
        Field neighbor2 = new Field(2,3);
        Field neighbor3 = new Field(3,2);

        field.addNeighbor(neighbor1);
        field.addNeighbor(neighbor2);
        field.addNeighbor(neighbor3);

        field.open();

        assertTrue(neighbor1.isOpen() && neighbor2.isOpen() && neighbor3.isOpen());
    }

    @Test
    //neighbor has mined now. we wanna test if neighbor11 doest open because the neighbor12 has mine
    void openWithNeighbor2(){

        Field neighbor11 = new Field(1,1);
        Field neighbor12 = new Field(1,2);
        neighbor12.mineField();

        Field neighbor22 = new Field(2,2);
        neighbor22.addNeighbor(neighbor11);
        neighbor22.addNeighbor(neighbor12);

        field.addNeighbor(neighbor22);
        field.open();

        assertTrue(neighbor22.isOpen() && neighbor11.isClosed());
    }
}
