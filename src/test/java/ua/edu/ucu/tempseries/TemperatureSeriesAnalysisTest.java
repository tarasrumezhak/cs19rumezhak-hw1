package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;

public class TemperatureSeriesAnalysisTest {
    private double[] temperatureSeries;
    private TemperatureSeriesAnalysis seriesAn;
    @Before
    public void setUp() throws Exception {
        temperatureSeries = new double[] {3.0, -5.0, 1.0, -1.0, 5.0};
        seriesAn = new TemperatureSeriesAnalysis(temperatureSeries);

    }
    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Ignore
    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }
    @Test
    public void deviation() {
        assertEquals(seriesAn.deviation(), 3.492849839314596, 0.00001);
    }

    @Test
    public void min() {
        assertEquals(seriesAn.min(), -5.0, 0.00001);
    }

    @Test
    public void max() {
        assertEquals(seriesAn.max(), 5.0, 0.00001);
    }

    @Test
    public void findTempClosestToZero() {
        assertEquals(seriesAn.findTempClosestToZero(), 1.0, 0.00001);
    }

    @Test
    public void findTempClosestToValue() {
        assertEquals(seriesAn.findTempClosestToValue(4.0), 5.0, 0.00001);
    }

    @Test
    public void findTempsLessThen() {
//        double[] arr = {-5.0, 1.0, -1.0};
//        double[] arr_1 = seriesAn.findTempsLessThen(2.0);
//        assertArrayEquals(seriesAn.findTempsLessThen(2.0), new double[] {-5.0, 1.0, -1.0});
    }

    @Test
    public void findTempsGreaterThen() {
    }

    @Test
    public void addTemps() {
    }

}
