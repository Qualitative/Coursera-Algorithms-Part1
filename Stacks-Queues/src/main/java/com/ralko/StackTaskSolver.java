package com.ralko;

import com.ralko.stack.ArrayStack;

public class StackTaskSolver {

    // Prevents instantiation
    private StackTaskSolver() {
    }

    public static boolean solve(int[] sequence) {

        ArrayStack<Integer> stack = new ArrayStack<Integer>();
        int previousItem = -1;

        for (int i = 0; i < sequence.length; i++) {
            int item = sequence[i];
            if (item > previousItem) {
                for (int j = previousItem + 1; j <= item; j++) {
                    stack.push(j);
                }
                previousItem = stack.pop();
            } else {
                int value = stack.pop();
                if (value != item) {
                    return false;
                }
            }
        }

        return true;
    }
}
