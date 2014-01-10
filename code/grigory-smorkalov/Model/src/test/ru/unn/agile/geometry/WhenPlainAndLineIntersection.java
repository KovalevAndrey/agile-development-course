package ru.unn.agile.geometry;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class WhenPlainAndLineIntersection {
    IntersectionComputer intersectionComputer;

    @Before
    public void setUp() {
        intersectionComputer = new IntersectionComputer();
    }

    @Test
    public void nullPlainPassed() {
        Plain plain = null;
        Line line = new Line(new Point(0, 0, 0), new Point(0, 0, 1));
        try {
            intersectionComputer.compute(plain, line);
            fail("Exception expected");
        } catch (RuntimeException e) {
            assertEquals("Null plain passed", e.getMessage());
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e.toString());
        }
    }

    @Test
    public void nullLinePassed() {
        Plain plain = new Plain(new Point(0, 0, 0), new Point(0, 0, 1));
        Line line = null;
        try {
            intersectionComputer.compute(plain, line);
            fail("Exception expected");
        } catch (RuntimeException e) {
            assertEquals("Null line passed", e.getMessage());
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e.toString());
        }
    }

    @Test
    public void nullArgsPassed() {
        Plain plain = null;
        Line line = null;
        try {
            intersectionComputer.compute(plain, line);
            fail("Exception expected");
        } catch (RuntimeException e) {
            assertEquals("Null plain and line passed", e.getMessage());
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e.toString());
        }
    }

    @Test
    public void lineParallelPlain() {
        Plain plain = new Plain(new Point(0, 0, 0), new Point(0, 0, 1));
        Line line = new Line(new Point(0, 0, 1), new Point(0, 1, 0));

        Point result = intersectionComputer.compute(plain, line);

        assertEquals(result, null);
    }

    @Test
    public void lineOrtPlain() {
        Plain plain = new Plain(new Point(1, 2, 3), new Point(0, 0, 1));
        Line line = new Line(new Point(1, 2, 3), new Point(0, 0, 1));

        Point result = intersectionComputer.compute(plain, line);

        assertEquals(result, new Point(1, 2, 3));
    }

    @Test
    public void plainIncludeLine() {
        Plain plain = new Plain(new Point(1, 2, 3), new Point(0, 0, 1));
        Line line = new Line(new Point(1, 2, 3), new Point(0, 1, 0));

        Point result = intersectionComputer.compute(plain, line);

        assertEquals(result, new Point(1, 2, 3));
    }

    @Test
    public void linePlainOxyCrossXyz() {
        Plain plain = new Plain(new Point(1, 2, 0), new Point(0, 0, 1));
        Line line = new Line(new Point(1, 1, 1), new Point(Math.cbrt(1.0/3.0), Math.cbrt(1.0/3.0), Math.cbrt(1.0/3.0)));

        Point result = intersectionComputer.compute(plain, line);

        assertEquals(result, new Point(0, 0, 0));
    }
}
