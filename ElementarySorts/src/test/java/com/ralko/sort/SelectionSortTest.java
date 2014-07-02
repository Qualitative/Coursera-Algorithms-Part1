package com.ralko.sort;

public class SelectionSortTest extends AbstractArraySortTest {

    @Override
    protected ArraySort<Integer> createArraySort() {
        return new SelectionSort<Integer>();
    }
}
