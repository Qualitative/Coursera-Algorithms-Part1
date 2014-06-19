package com.ralko.qf;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuickFindTest {
    
    private static final int INITIAL_CAPACITY = 10;
    private QuickFind qf;
    
    @Before
    public void init() {
        qf = new QuickFind(INITIAL_CAPACITY);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInitialCapacityIsLessThenOne() {
        new QuickFind(0);
    }
    
    @Test
    public void shouldInitializeAllItemsWithDefaultValuesWhenCreated() {
        assertEquals("[0 0 0 0 0 0 0 0 0 0]", qf.getState());
    }
}
