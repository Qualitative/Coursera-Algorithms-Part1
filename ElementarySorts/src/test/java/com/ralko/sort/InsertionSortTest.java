package com.ralko.sort;

public class InsertionSortTest extends AbstractArraySortTest {

    @Override
    protected ArraySort<Integer> createArraySort() {
        return new InsertionSort<Integer>();
    }

}
