import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] tempSeries;
    private double average;
    private int size;
    private int lastAddedIndex;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        int MINIMUM = -273;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < MINIMUM) {
                throw new InputMismatchException();
            }
        }
        this.tempSeries = Arrays.copyOf(temperatureSeries,
                temperatureSeries.length);
        this.size = temperatureSeries.length;
        this.lastAddedIndex = temperatureSeries.length - 1;
    }

    public double average() {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Temperature Series is empty");
        }
        double sum = 0.0;
        for (int i = 0; i < tempSeries.length; i++) {
            sum += tempSeries[i];
        }
        this.average = sum / tempSeries.length;
        return sum / tempSeries.length;
    }

    public double deviation() {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Temperature Series is empty");
        }
        double sum = 0.0;
        for (int i = 0; i < tempSeries.length; i++) {
            sum = sum + Math.pow(tempSeries[i] - average, 2.0);
        }
        double std = Math.sqrt(sum/tempSeries.length);
        return std;
    }

    public double min() {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Temperature Series is empty");
        }
        double min = tempSeries[0];
        for (int i = 0; i < tempSeries.length; i++) {
            if (tempSeries[i] < min){
                min = tempSeries[i];
            }
        }
        return min;
    }

    public double max() {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Temperature Series is empty");
        }
        double max = 0.0;
        for (int i = 0; i < tempSeries.length; i++) {
            if (tempSeries[i] > max) {
                max = tempSeries[i];
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        double min = Math.abs(tempSeries[0] - tempValue);
        double closest = tempSeries[0];
        double secondClosest = 0.0;
        for (int i = 0; i < tempSeries.length; i++) {
            if (Math.abs(tempSeries[i] - tempValue) <= min) {
                if (Math.abs(tempSeries[i] - tempValue) < min) {
                    min = Math.abs(tempSeries[i] - tempValue);
                    closest = tempSeries[i];
                }
                else if (Math.abs(tempSeries[i] - tempValue) == min) {
                    secondClosest = tempSeries[i];
                }
            }
        }
        if (Math.abs(closest) == Math.abs(secondClosest)) {
            return Math.abs(closest);
        }
        return Math.max(closest, secondClosest);
    }

    public double[] findTempsLessThen(double tempValue) {
        int lessSize = 0;
        for (int i = 0; i < tempSeries.length; i++) {
            if (tempSeries[i] < tempValue){
                lessSize++;
            }
        }
        double[] lessArr = new double[lessSize];
        int j = 0;
        for (int i = 0; i < tempSeries.length; i++) {
            if (tempSeries[i] < tempValue) {
                lessArr[j] = tempSeries[i];
                j++;
            }
        }
        return lessArr;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int greaterSize = 0;
        for (int i = 0; i < tempSeries.length; i++) {
            if (tempSeries[i] > tempValue) {
                greaterSize++;
            }
        }
        double[] greaterArr = new double[greaterSize];
        int j = 0;
        for (int i = 0; i < tempSeries.length; i++) {
            if (tempSeries[i] > tempValue) {
                greaterArr[j] = tempSeries[i];
                j++;
            }
        }
        return greaterArr;
    }

    public TempSummaryStatistics summaryStatistics() {
        TempSummaryStatistics stats = new TempSummaryStatistics(this.average(),
                this.deviation(), this.min(), this.max());
        return stats;
    }

    public int addTemps(double... temps) {
        if (lastAddedIndex >= size - 1) {
            double[] tmp = new double[2*size];
            System.arraycopy(tmp, 0, tempSeries, 0, tempSeries.length);
            tempSeries = tmp;
            size = size*2;
        }
        for (int i = 0; i < temps.length; i++) {
            tempSeries[lastAddedIndex + i] = temps[i];
            lastAddedIndex += 1;
        }
        return lastAddedIndex + 1;
    }
}

