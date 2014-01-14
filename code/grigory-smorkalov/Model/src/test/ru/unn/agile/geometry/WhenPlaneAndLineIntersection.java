package ru.unn.agile.geometry;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class WhenPlaneAndLineIntersection {
    IntersectionDetector intersectionDetector;

    @Before
    public void setUp() {
        intersectionDetector = new IntersectionDetector();
    }

    @Test
    public void nullPlanePassed() {
        Plane plane = null;
        Line line = new Line(new Point(0, 0, 0), new Point(0, 0, 1));
        try {
            intersectionDetector.compute(plane, line);
            fail("Exception expected");
        } catch (RuntimeException e) {
            assertEquals("Null plane passed", e.getMessage());
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e.toString());
        }
    }

    @Test
    public void nullLinePassed() {
        Plane plane = new Plane(new Point(0, 0, 0), new Point(0, 0, 1));
        Line line = null;
        try {
            intersectionDetector.compute(plane, line);
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
        Plane plane = null;
        Line line = null;
        try {
            intersectionDetector.compute(plane, line);
            fail("Exception expected");
        } catch (RuntimeException e) {
            assertEquals("Null plane and line passed", e.getMessage());
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e.toString());
        }
    }

    @Test
    public void lineParallelPlane() {
        Plane plane = new Plane(new Point(0, 0, 0), new Point(0, 0, 1));
        Line line = new Line(new Point(0, 0, 1), new Point(0, 1, 0));

        Point result = intersectionDetector.compute(plane, line);

        assertEquals(result, null);
    }

    @Test
    public void lineOrtPlane() {
        Plane plane = new Plane(new Point(1, 2, 3), new Point(0, 0, 1));
        Line line = new Line(new Point(1, 2, 3), new Point(0, 0, 1));

        Point result = intersectionDetector.compute(plane, line);

        assertEquals(result, new Point(1, 2, 3));
    }

    @Test
    public void planeIncludeLine() {
        Plane plane = new Plane(new Point(1, 2, 3), new Point(0, 0, 1));
        Line line = new Line(new Point(1, 2, 3), new Point(0, 1, 0));

        Point result = intersectionDetector.compute(plane, line);

        assertEquals(result, new Point(1, 2, 3));
    }

    @Test
    public void linePlaneOxyCrossXyz() {
        Plane plane = new Plane(new Point(1, 2, 0), new Point(0, 0, 1));
        Line line = new Line(new Point(1, 1, 1), new Point(Math.cbrt(1.0/3.0), Math.cbrt(1.0/3.0), Math.cbrt(1.0/3.0)));

        Point result = intersectionDetector.compute(plane, line);

        assertEquals(result, new Point(0, 0, 0));
    }
}
