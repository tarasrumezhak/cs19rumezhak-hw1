import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviation() {
        assertEquals(seriesAn.deviation(), 3.492849839314596, 0.00001);
    }

    @Test
    public void testMin() {
        assertEquals(seriesAn.min(), -5.0, 0.00001);
    }

    @Test
    public void TestOneElementMin() {
        double expResult = -7.4;
        TemperatureSeriesAnalysis oneElementSeries = new TemperatureSeriesAnalysis(new double[] {-7.4});
        double actualResult = oneElementSeries.min();
        assertEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void TestOneElementMax() {
        double expResult = 15.2;
        TemperatureSeriesAnalysis oneElementSeries = new TemperatureSeriesAnalysis(new double[] {15.2});
        double actualResult = oneElementSeries.max();
        assertEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void max() {
        assertEquals(seriesAn.max(), 5.0, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        assertEquals(seriesAn.findTempClosestToZero(), 1.0, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        assertEquals(seriesAn.findTempClosestToValue(4.0), 5.0, 0.00001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] arr = {-5.0, 1.0, -1.0};
        double[] arr_1 = seriesAn.findTempsLessThen(2.0);
        assertArrayEquals(arr_1, arr, 0.000001);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] arr = {3.0, 5.0};
        double[] arr_1 = seriesAn.findTempsGreaterThen(2.0);
        assertArrayEquals(arr_1, arr, 0.000001);
    }

    @Test
    public void testAddTemp() {
        TemperatureSeriesAnalysis serieAn = new TemperatureSeriesAnalysis(new double[] {2.0});
        serieAn.addTemp(4.0);
        assertEquals(serieAn.max(), 4.0, 0.00001);
    }

    @Test
    public void testAddTemps() {
        assertEquals(seriesAn.addTemps(6.0, -16.3), 7);
    }

    @Test
    public void testAddedTempsMax(){
        seriesAn.addTemps(6.0, -16.3);
        assertEquals(seriesAn.max(), 6.0, 0.00001);
    }

    @Test
    public void testAddedTempsMin(){
        seriesAn.addTemps(6.0, -16.3);
        assertEquals(seriesAn.min(), -16.3, 0.00001);
    }

    @Test
    public void testAddedTempsLengthToEmpty(){
        TemperatureSeriesAnalysis seriesAn2 = new TemperatureSeriesAnalysis();
        seriesAn2.addTemps(1, 2, 3, 4, 5, 6);
        assertEquals(6, seriesAn2.getLength(), 0.001);
    }

    @Test
    public void testAddedTempsLength(){
        TemperatureSeriesAnalysis seriesAn2 = new TemperatureSeriesAnalysis(new double[] {8.0, -3.2});
        seriesAn2.addTemps(1, 2, 3, 7.2, -5.7, 6);
        assertEquals(8, seriesAn2.getLength(), 0.001);
    }

    @Test
    public void testTempSummaryStatistics(){
        String stats = seriesAn.summaryStatistics().toString();
        String actual = "   ===Statistics===   \n" +
                "Average Temperature: 0.6\n" +
                "Standard Deviation: 3.4409301068170506\n" +
                "Minimum Temperature: -5.0\n" +
                "Maximum Temperature: 5.0\n";
        assertEquals(stats, actual);
    }

    @Test
    public void testLength(){
        TemperatureSeriesAnalysis seriesAn2 = new TemperatureSeriesAnalysis(new double[] {2.7, 9.0, -5.6});
        assertEquals(3, seriesAn2.getLength(), 0.001);
    }
}