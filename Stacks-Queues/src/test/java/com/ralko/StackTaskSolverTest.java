package com.ralko;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StackTaskSolverTest {

    @Test
    public void shouldReturnTrueIfSequenceIsCorrect() {
        boolean value1 = StackTaskSolver.solve(new int[] { 6, 5, 4, 3, 2, 1, 0, 7, 8, 9 });
        boolean value2 = StackTaskSolver.solve(new int[] { 3, 4, 5, 8, 9, 7, 6, 2, 1, 0 });
        boolean value3 = StackTaskSolver.solve(new int[] { 0, 3, 7, 6, 5, 4, 2, 9, 8, 1 });
        assertTrue(value1);
        assertTrue(value2);
        assertTrue(value3);
    }

    @Test
    public void shouldReturnFlaseIfSequenceIsWrong() {
        boolean value1 = StackTaskSolver.solve(new int[] { 0, 1, 3, 2, 4, 6, 8, 5, 7, 9 });
        boolean value2 = StackTaskSolver.solve(new int[] { 1, 0, 3, 5, 2, 7, 6, 8, 9, 4 });
        boolean value3 = StackTaskSolver.solve(new int[] { 3, 5, 2, 4, 6, 1, 0, 8, 7, 9 });
        assertFalse(value1);
        assertFalse(value2);
        assertFalse(value3);
    }

}
