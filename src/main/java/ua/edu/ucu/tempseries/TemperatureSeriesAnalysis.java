package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] tempSeries;
    private double average;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < -273){
                throw new InputMismatchException();
            }
        }
        this.tempSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
    }

    public double average() {
        if (tempSeries.length == 0){
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
        if (tempSeries.length == 0){
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
        if (tempSeries.length == 0){
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
        if (tempSeries.length == 0){
            throw new IllegalArgumentException("Temperature Series is empty");
        }
        double max = 0.0;
        for (int i = 0; i < tempSeries.length; i++) {
            if (tempSeries[i] > max){
                max = tempSeries[i];
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
//        double min = Math.abs(tempSeries[0] - 0);
//        double closest = 0.0;
//        double second_closest = 0.0;
//        for (int i = 0; i < tempSeries.length; i++) {
//            if (Math.abs(tempSeries[i]) <= min){
//                if (Math.abs(tempSeries[i]) < min){
//                    min = Math.abs(tempSeries[i]);
//                    closest = tempSeries[i];
//                }
//                else if ((Math.abs(tempSeries[i]) == min)){
//                    second_closest = tempSeries[i];
//                }
//            }
//        }
////        System.out.println(closest + " " + second_closest);
//        if (Math.abs(closest) == Math.abs(second_closest)){
//            return Math.abs(closest);
//        }
//        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        double min = Math.abs(tempSeries[0] - tempValue);
        double closest = tempSeries[0];
        double second_closest = 0.0;
        for (int i = 0; i < tempSeries.length; i++) {
            if (Math.abs(tempSeries[i] - tempValue) <= min){
                if (Math.abs(tempSeries[i] - tempValue) < min){
                    min = Math.abs(tempSeries[i] - tempValue);
                    closest = tempSeries[i];
                }
                else if ((Math.abs(tempSeries[i] - tempValue) == min)){
                    second_closest = tempSeries[i];
                }
            }
        }
//        System.out.println(closest + " " + second_closest);
        if (Math.abs(closest) == Math.abs(second_closest)){
            return Math.abs(closest);
        }
        if (closest > second_closest){
            return closest;
        }
        else return second_closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        int size = 0;
        for (int i = 0; i < tempSeries.length; i++) {
            if (tempSeries[i] < tempValue){
                size++;
            }
        }
        double[] less_arr = new double[size];
        int j = 0;
        for (int i = 0; i < tempSeries.length; i++) {
            if (tempSeries[i] < tempValue){
                less_arr[j] = tempSeries[i];
                j++;
            }
        }
        return less_arr;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int size = 0;
        for (int i = 0; i < tempSeries.length; i++) {
            if (tempSeries[i] > tempValue){
                size++;
            }
        }
        double[] greater_arr = new double[size];
        int j = 0;
        for (int i = 0; i < tempSeries.length; i++) {
            if (tempSeries[i] > tempValue){
                greater_arr[j] = tempSeries[i];
                j++;
            }
        }
        return greater_arr;
    }
    //    public TempSummaryStatistics summaryStatistics() {
//        return null;
//    }
    public int addTemps(double... temps) {
//        this.tempSeries = new double[tempSeries.length * 2]
//        for (int i = 0; i < temps.length; i++) {
//            tempSeries[tempSeries.length + i] = temps[i];
//        }
//        return tempSeries.length;
        return 0;
    }
}