package com.ralko.sort;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectionSort<T extends Comparable<T>> implements ArraySort<T> {

    private final static Logger LOG = LoggerFactory.getLogger(SelectionSort.class);

    @Override
    public void sort(T[] array) {
        if (array == null) {
            throw new NullPointerException("Array should not be null");
        }

        int n = array.length;

        if (LOG.isDebugEnabled()) {
            LOG.debug("Before sorting: {}", Arrays.toString(array));
            LOG.debug("--------------------------------------");
        }

        for (int i = 0; i < n; i++) {
            int min = i;

            for (int j = i + 1; j < n; j++) {
                if (less(array[j], array[min])) {
                    min = j;
                }
            }
            exchange(array, i, min);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("--------------------------------------");
            LOG.debug("After sorting: {}", Arrays.toString(array));
        }
    }

    private boolean less(T item1, T item2) {
        return item1.compareTo(item2) < 0;
    }

    private void exchange(T[] array, int i, int j) {
        T temp = array[j];
        array[j] = array[i];
        array[i] = temp;
        if (LOG.isDebugEnabled()) {
            LOG.debug("Exchanged {} with {}", temp, array[j]);
            LOG.debug(Arrays.toString(array));
        }
    }

}
