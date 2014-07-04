package com.ralko.sort;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertionSort<T extends Comparable<T>> implements ArraySort<T> {

    private final static Logger LOG = LoggerFactory.getLogger(InsertionSort.class);

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

        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (less(array[j], array[j - 1])) {
                    exchange(array, j, j - 1);
                } else {
                    break;
                }
            }
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
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        if (LOG.isDebugEnabled()) {
            LOG.debug("Exchanged {} with {}", temp, array[i]);
            LOG.debug(Arrays.toString(array));
        }
    }

}
