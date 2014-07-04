package com.ralko.sort;


public class ShellSortTest extends AbstractArraySortTest {

    @Override
    protected ArraySort<Integer> createArraySort() {
        return new ShellSort<Integer>();
    }

}
