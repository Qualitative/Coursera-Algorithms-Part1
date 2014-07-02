package com.ralko.sort;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArraySortTest {

    private ArraySort<Integer> sort;

    @Before
    public void init() {
        sort = createArraySort();
    }

    protected abstract ArraySort<Integer> createArraySort();

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfArrayIsNull() {
        sort.sort(null);
    }

    @Test
    public void shouldDoNothingIfArrayIsEmpty() {
        // Given
        Integer[] array = new Integer[] {};
        // When
        sort.sort(array);
        // Then
        assertArrayEquals(new Integer[] {}, array);
    }

    @Test
    public void shouldSortArrayIfArrayIsNotEmpty() {
        // Given
        Integer[] array = { 1, 5, 8, 2, 9, 10, 3, 6, 4, 7 };
        // When
        sort.sort(array);
        // Then
        assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, array);
    }
}
