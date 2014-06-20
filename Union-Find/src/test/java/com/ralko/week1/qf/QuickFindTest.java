package com.ralko.week1.qf;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ralko.week1.qf.QuickFind;

public class QuickFindTest {
    
    private static final int INITIAL_CAPACITY = 10;
    private QuickFind qf;
    
    @Before
    public void init() {
        qf = new QuickFind(INITIAL_CAPACITY);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInitialCapacityIsNotPositive() {
        new QuickFind(0);
    }
    
    @Test
    public void shouldInitializeAllItemsWithDefaultValuesWhenCreated() {
        assertEquals("[0 1 2 3 4 5 6 7 8 9]", qf.getState());
    }
    
    @Test
    public void shouldChangeSetForFirstArgumentWhenUnionPair() {
        qf.union(0, 1);
        assertEquals("[1 1 2 3 4 5 6 7 8 9]", qf.getState());
        qf.union(9, 8);
        assertEquals("[1 1 2 3 4 5 6 7 8 8]", qf.getState());
        qf.union(8, 1);
        assertEquals("[1 1 2 3 4 5 6 7 1 1]", qf.getState());
    }
    
    @Test
    public void shouldReturnFalseWhenItemsAreNotConnected() {
        assertFalse(qf.find(0, 1));
        assertFalse(qf.find(9, 8));
        assertFalse(qf.find(8, 1));
    }
    
    @Test
    public void shouldReturnTrueWhenItemsAreConnected() {
        qf.union(0, 1);
        qf.union(9, 8);
        qf.union(8, 1);
        
        assertTrue(qf.find(0, 1));
        assertTrue(qf.find(9, 8));
        assertTrue(qf.find(8, 1));
        assertTrue(qf.find(1, 0));
        assertTrue(qf.find(8, 9));
        assertTrue(qf.find(1, 8));
        assertTrue(qf.find(0, 9));
        assertTrue(qf.find(0, 8));
        assertTrue(qf.find(1, 9));
    }
}
