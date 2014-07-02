package com.ralko.sort;

public class SelectionSort<T extends Comparable<T>> implements ArraySort<T> {

    @Override
    public void sort(T[] array) {
        if (array == null) {
            throw new NullPointerException("Array should not be null");
        }

        int n = array.length;

        if (n == 0) {
            return;
        }

        for (int i = 0; i < n; i++) {
            T min = array[i];

            for (int j = i + 1; j < n; j++) {
                if (less(array[j], min)) {
                    exchange(array, i, j);
                    min = array[i];
                }
            }
        }
    }

    private boolean less(T item1, T item2) {
        return item1.compareTo(item2) < 0;
    }

    private void exchange(T[] array, int i, int j) {
        T temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }

}
