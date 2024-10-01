package ua.edu.ucu.apps.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] series;
    private int len;
    private int capacity;

    public TemperatureSeriesAnalysis() {
        series = new double[0];
        len = 0;
        capacity = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        len = temperatureSeries.length;
        capacity = len * 2;
        series = new double[capacity];
        System.arraycopy(temperatureSeries, 0, series, 0, len);
    }

    public double average() {
        if (len == 0) {
            throw new IllegalArgumentException(
                    "Can`t find the average of an empty series.");
        }
        double sum = 0;
        for (int i = 0; i < len; i++) {
            sum += series[i];
        }
        return sum / len;
    }

    public double deviation() {
        if (len == 0) {
            throw new IllegalArgumentException(
                    "Can`t find the deviation of an empty series.");
        }
        double avg = average();
        double variance = 0;
        for (int i = 0; i < len; i++) {
            variance += (series[i] - avg) * (series[i] - avg);
        }
        return Math.sqrt(variance / len);
    }

    public double min() {
        if (len == 0) {
            throw new IllegalArgumentException(
                    "Can`t find the minimum of an empty series.");
        }

        double minVal = series[0];
        for (int i = 0; i < len; i++) {
            if (series[i] < minVal) {
                minVal = series[i];
            }
        }
        return minVal;
    }

    public double max() {
        if (len == 0) {
            throw new IllegalArgumentException(
                    "Can`t find the maximum of an empty series.");
        }

        double maxVal = series[0];
        for (int i = 0; i < len; i++) {
            if (series[i] > maxVal) {
                maxVal = series[i];
            }
        }
        return maxVal;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (len == 0) {
            throw new IllegalArgumentException(
                    "Can`t find closest to value of an empty series.");
        }
        double minDiff = Math.abs(series[0] - tempValue);
        double closest = series[0];

        for (int i = 0; i < len; i++) {
            double diff = Math.abs(series[i] - tempValue);
            if (diff < minDiff || (diff == minDiff && series[i] > closest)) {
                minDiff = diff;
                closest = series[i];
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        int sizeOfResArray = 0;
        for (int i = 0; i < len; i++) {
            if (series[i] < tempValue) {
                sizeOfResArray++;
            }
        }
        double[] res = new double[sizeOfResArray];
        int idx = 0;
        for (int i = 0; i < len; i++) {
            if (series[i] < tempValue) {
                res[idx] = series[i];
                idx++;
            }
        }
        return res;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int sizeOfResArray = 0;
        for (int i = 0; i < len; i++) {
            if (series[i] >= tempValue) {
                sizeOfResArray++;
            }
        }
        double[] res = new double[sizeOfResArray];
        int idx = 0;
        for (int i = 0; i < len; i++) {
            if (series[i] >= tempValue) {
                res[idx] = series[i];
                idx++;
            }
        }
        return res;
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        int sizeOfResArray = 0;
        for (int i = 0; i < len; i++) {
            if (series[i] >= lowerBound && series[i] <= upperBound) {
                sizeOfResArray++;
            }
        }
        double[] res = new double[sizeOfResArray];
        int idx = 0;
        for (int i = 0; i < len; i++) {
            if (series[i] >= lowerBound && series[i] <= upperBound) {
                res[idx] = series[i];
                idx++;
            }
        }
        return res;
    }

    public void reset() {
        series = new double[0];
        len = 0;
        capacity = 0;
    }

    public double[] sortTemps() {
        double[] sortedSeries = Arrays.copyOf(series, len);
        Arrays.sort(sortedSeries);

        return sortedSeries;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (len == 0) {
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
        int addSize = temps.length;
        int newLen = len + addSize;

        if (newLen > capacity) {
            int newCapacity = newLen * 2;
            double[] newSeries = new double[newCapacity];
            System.arraycopy(series, 0, newSeries, 0, len);
            System.arraycopy(temps, 0, newSeries, len, addSize);
            capacity = newCapacity;
        } else {
            System.arraycopy(temps, 0, series, len, addSize);
        }
        len = newLen;
        return len;
    }

    public double[] getSeries() {
        return Arrays.copyOf(series, len);
    }
}
