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
    public void testTempSummaryStatistics(){
        String stats = seriesAn.summaryStatistics().toString();
        String actual = "   ===Statistics===   \n" +
                "Average Temperature: 0.6\n" +
                "Standard Deviation: 3.4409301068170506\n" +
                "Minimum Temperature: -5.0\n" +
                "Maximum Temperature: 5.0\n";
        assertEquals(stats, actual);
    }
}