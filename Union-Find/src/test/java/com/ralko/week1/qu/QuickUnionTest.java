package com.ralko.week1.qu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ralko.week1.qf.QuickFind;
import com.ralko.week1.qu.QuickUnion;

public class QuickUnionTest {

    private static final int INITIAL_CAPACITY = 10;
    private QuickUnion qu;

    @Before
    public void init() {
        qu = new QuickUnion(INITIAL_CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInitialCapacityIsNotPositive() {
        new QuickFind(0);
    }

    @Test
    public void shouldInitializeAllItemsWithDefaultValuesWhenCreated() {
        assertEquals("[0 1 2 3 4 5 6 7 8 9]", qu.getState());
    }

    @Test
    public void shouldChangeParentOfSecondArgumentWhenUnionPair() {
        qu.union(0, 1);
        assertEquals("[0 0 2 3 4 5 6 7 8 9]", qu.getState());
        qu.union(9, 8);
        assertEquals("[0 0 2 3 4 5 6 7 9 9]", qu.getState());
        qu.union(8, 1);
        assertEquals("[9 0 2 3 4 5 6 7 9 9]", qu.getState());
    }

    @Test
    public void shouldReturnFalseWhenItemsAreNotConnected() {
        assertFalse(qu.find(0, 1));
        assertFalse(qu.find(9, 8));
        assertFalse(qu.find(8, 1));
    }

    @Test
    public void shouldReturnTrueWhenItemsAreConnected() {
        qu.union(0, 1);
        qu.union(9, 8);
        qu.union(8, 1);

        assertTrue(qu.find(0, 1));
        assertTrue(qu.find(9, 8));
        assertTrue(qu.find(8, 1));
        assertTrue(qu.find(1, 0));
        assertTrue(qu.find(8, 9));
        assertTrue(qu.find(1, 8));
        assertTrue(qu.find(0, 9));
        assertTrue(qu.find(0, 8));
        assertTrue(qu.find(1, 9));
    }
}
