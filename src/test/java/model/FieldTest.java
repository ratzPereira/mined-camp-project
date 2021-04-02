package model;



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
}
