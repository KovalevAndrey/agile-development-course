package ru.unn.agile.colorConverter.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterTest {
    @Test
    public void convertsRGBtoRGB() throws Exception {
        Converter converter = new Converter();

        RGBColor color = new RGBColor(255, 128, 0);

        assertEquals(converter.toRGB(color), color);
    }

    @Test
    public void convertsHSVtoRGB() throws Exception {
        Converter converter = new Converter();

        Color color = new HSVColor(30, 100, 100);

        assertEquals(converter.toRGB(color), color.getRGB());
    }

    @Test
    public void convertsLABtoRGB() throws Exception {
        Converter converter = new Converter();

        Color color = new LABColor(67, 43, 74);

        assertEquals(converter.toRGB(color), color.getRGB());
    }

    @Test
    public void convertsRGBtoHSV() throws Exception {
        Converter converter = new Converter();

        RGBColor sourceRgb = new RGBColor(255, 128, 0);
        HSVColor outputHsv = converter.toHSV(sourceRgb);

        assertTrue(ColorEqualChecker.almostEqualValues(
                outputHsv.getRGB(), sourceRgb));
    }

    @Test
    public void convertsHSVtoHSV() throws Exception {
        Converter converter = new Converter();

        HSVColor sourceHsv = new HSVColor(30, 70, 100);
        HSVColor outputHsv = converter.toHSV(sourceHsv);

        assertEquals(sourceHsv, outputHsv);
    }

    @Test
    public void convertsLABtoHSV() throws Exception {
        Converter converter = new Converter();

        LABColor soucreLab = new LABColor(100, 0, 0);
        HSVColor outputHsv = converter.toHSV(soucreLab);

        assertTrue(ColorEqualChecker.almostEqualValues(
                soucreLab.getRGB(), outputHsv.getRGB()));
    }

    @Test
    public void convertsRGBtoLAB() throws Exception {
        Converter converter = new Converter();

        RGBColor sourceRgb = new RGBColor(255, 128, 128);
        LABColor outputLab = converter.toLAB(sourceRgb);

        assertTrue(ColorEqualChecker.almostEqualValues(
                sourceRgb, outputLab.getRGB()));
    }

    @Test
    public void convertsHSVtoLAB() throws Exception {
        Converter converter = new Converter();

        HSVColor sourceHsv = new HSVColor(30, 100, 100);
        LABColor outputLab = converter.toLAB(sourceHsv);

        assertTrue(ColorEqualChecker.almostEqualValues(
                sourceHsv.getRGB(), outputLab.getRGB()));
    }

    @Test
    public void convertsLABtoLAB() throws Exception {
        Converter converter = new Converter();

        LABColor sourceLab = new LABColor(50, 25, 20);
        LABColor outputLab = converter.toLAB(sourceLab);

        assertTrue(ColorEqualChecker.almostEqualValues(
                sourceLab.getRGB(), outputLab.getRGB()));
    }
}
