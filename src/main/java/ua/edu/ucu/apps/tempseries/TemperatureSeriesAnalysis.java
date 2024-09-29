package ua.edu.ucu.apps.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] series = {};

    public TemperatureSeriesAnalysis() {
        series = new double[0];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        series = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
    }

    public double average() {
        int size = series.length;
        if (size == 0) {
            throw new IllegalArgumentException(
                    "Can`t find the average of an empty series.");
        }
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += series[i];
        }
        return sum / size;
    }

    public double deviation() {
        int size = series.length;
        if (size == 0) {
            throw new IllegalArgumentException(
                    "Can`t find the deviation of an empty series.");
        }
        double avg = average();
        double variance = 0;
        for (int i = 0; i < size; i++) {
            variance += (series[i] - avg) * (series[i] - avg);
        }
        return Math.sqrt(variance / size);
    }

    public double min() {
        int size = series.length;
        if (size == 0) {
            throw new IllegalArgumentException(
                    "Can`t find the minimum of an empty series.");
        }

        double minVal = series[0];
        for (int i = 0; i < size; i++) {
            if (series[i] < minVal) {
                minVal = series[i];
            }
        }
        return minVal;
    }

    public double max() {
        int size = series.length;
        if (size == 0) {
            throw new IllegalArgumentException(
                    "Can`t find the maximum of an empty series.");
        }

        double maxVal = series[0];
        for (int i = 0; i < size; i++) {
            if (series[i] > maxVal) {
                maxVal = series[i];
            }
        }
        return maxVal;
    }

    public double findTempClosestToZero() {
        int size = series.length;
        if (size == 0) {
            throw new IllegalArgumentException(
                    "Can`t find closest to zero of an empty series.");
        }
        double minDiff = Math.abs(series[0]);
        double closest = series[0];

        for (double num : series) {
            double diff = Math.abs(num);
            if (diff < minDiff || (diff == minDiff && num > closest)) {
                minDiff = diff;
                closest = num;
            }
        }
        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        int size = series.length;
        if (size == 0) {
            throw new IllegalArgumentException(
                    "Can`t find closest to value of an empty series.");
        }
        double minDiff = Math.abs(series[0] - tempValue);
        double closest = series[0];

        for (double num : series) {
            double diff = Math.abs(num - tempValue);
            if (diff < minDiff || (diff == minDiff && num > closest)) {
                minDiff = diff;
                closest = num;
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        int size = series.length;
        int sizeOfResArray = 0;
        for (double d : series) {
            if (d < tempValue) {
                sizeOfResArray++;
            }
        }
        double[] res = new double[sizeOfResArray];
        int idx = 0;
        for (int i = 0; i < size; i++) {
            if (series[i] < tempValue) {
                res[idx] = series[i];
                idx++;
            }
        }
        return res;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int size = series.length;
        int sizeOfResArray = 0;
        for (double d : series) {
            if (d >= tempValue) {
                sizeOfResArray++;
            }
        }
        double[] res = new double[sizeOfResArray];
        int idx = 0;
        for (int i = 0; i < size; i++) {
            if (series[i] >= tempValue) {
                res[idx] = series[i];
                idx++;
            }
        }
        return res;
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        int size = series.length;
        int sizeOfResArray = 0;
        for (double d : series) {
            if (d >= lowerBound && d <= upperBound) {
                sizeOfResArray++;
            }
        }
        double[] res = new double[sizeOfResArray];
        int idx = 0;
        for (int i = 0; i < size; i++) {
            if (series[i] >= lowerBound && series[i] <= upperBound) {
                res[idx] = series[i];
                idx++;
            }
        }
        return res;
    }

    public void reset() {
        series = new double[0];
    }

    public double[] sortTemps() {
        int size = series.length;
        double[] sortedSeries = Arrays.copyOf(series, size);
        Arrays.sort(sortedSeries);

        return sortedSeries;
    }

    public TempSummaryStatistics summaryStatistics() {
        int size = series.length;
        if (size == 0) {
            throw new IllegalArgumentException(
                    "Can`t create summary statistics of an empty series.");
        }
        TempSummaryStatistics res = new TempSummaryStatistics();
        res.setAvgTemp(average());
        res.setDevTemp(deviation());
        res.setMinTemp(min());
        res.setMaxTemp(max());
        return res;
    }

    public int addTemps(double... temps) {
        final int MAGIC_NUM = -273;
        for (double num : temps) {
            if (num < MAGIC_NUM) {
                throw new InputMismatchException("To low!");
            }
        }
        int oldSize = series.length;
        int addSize = temps.length;
        int newSize = oldSize * 2;
        series = Arrays.copyOf(series, newSize);
        System.arraycopy(temps, 0, series, oldSize, addSize);
        return newSize;
    }

    public double[] getSeries() {
        return Arrays.copyOf(series, series.length);
    }
}
