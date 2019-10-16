import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final int INITIAL_SIZE = 5;
    private static final double MINIMUM_TEMPERATURE = -273.0;
    private double[] tempSeries;
    private double average;
    private int size;
    private int lastAddedIndex;

    public TemperatureSeriesAnalysis() {
        this.tempSeries = new double[INITIAL_SIZE];
        this.size = INITIAL_SIZE;
        this.lastAddedIndex = -1;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < MINIMUM_TEMPERATURE) {
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
            if (tempSeries[i] < min) {
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
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Temperature Series is empty");
        }
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Temperature Series is empty");
        }
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
            if (tempSeries[i] < tempValue) {
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
    public int addTemp(double temp) {
        if (lastAddedIndex >= size - 1) {
            double[] tmp = new double[size * 2];
            if (size > 0) {
                System.arraycopy(tmp, 0, tempSeries, 0, tempSeries.length);
            }
            tempSeries = tmp;
            size = size * 2;
        }
        tempSeries[lastAddedIndex + 1] = temp;
        lastAddedIndex += 1;
        return  lastAddedIndex + 1;
    }

    public int addTemps(double... temps) {
        for (double temp : temps) {
            if (temp < MINIMUM_TEMPERATURE) {
                throw new InputMismatchException("Too small temperature");
            }
            addTemp(temp);
        }
        return lastAddedIndex + 1;
    }

    public int getLength() {
        return lastAddedIndex + 1;
    }
}

