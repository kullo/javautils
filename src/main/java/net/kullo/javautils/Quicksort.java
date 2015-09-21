package net.kullo.javautils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Quicksort {
    public interface PivotStrategy {
        <G> void apply(List<G> list, int left, int right);
    }

    static public class TrivialPivotStrategy implements PivotStrategy {
        @Override
        public <G> void apply(List<G> list, int left, int right) {
            // do nothing
        }
    }

    static public class MiddleElementPivotStrategy implements PivotStrategy {
        @Override
        public <G> void apply(List<G> list, int left, int right) {
            int pivotPosition = left + (right-left)/2;
            Collections.swap(list, pivotPosition, right);
        }
    }

    private Quicksort() {}

    public static <G> void sort(List<G> list, Comparator<G> comparator) {
        sort(list, comparator, 0, list.size() - 1);
    }

    public static <G> void sort(List<G> list, Comparator<G> comparator, int left, int right) {
        if (left < right) {
            PivotStrategy pivotStrategy = new MiddleElementPivotStrategy();
            pivotStrategy.apply(list, left, right);
            int separator = separate(list, comparator, left, right);
            sort(list, comparator, left, separator - 1);
            sort(list, comparator, separator + 1, right);
        }
    }

    public static <G> int separate(List<G> list, Comparator<G> comparator, int left, int right) {
        int i = left;
        int j = right;
        G pivot = list.get(right);

        do {
            while (comparator.compare(list.get(i), pivot) <= 0 && i < right) {
                i++;
            }

            while (comparator.compare(list.get(j), pivot) >= 0 && j > left) {
                j--;
            }

            if (i < j) {
                Collections.swap(list, i, j);
            }
        } while (i < j);

        if (comparator.compare(list.get(i), pivot) > 0) {
            Collections.swap(list, i, right);
        }

        return i;
    }
}
