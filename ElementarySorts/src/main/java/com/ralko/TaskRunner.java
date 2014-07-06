package com.ralko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ralko.sort.InsertionSort;
import com.ralko.sort.SelectionSort;
import com.ralko.sort.ShellSort;

public class TaskRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TaskRunner.class);

    public static void main(String[] args) {
        LOG.info("----- Task1 ----- Selection -----");
        SelectionSort<Integer> selectionSort = new SelectionSort<>();
        selectionSort.sort(new Integer[] { 93, 84, 23, 47, 41, 26, 94, 53, 13, 29 });

        LOG.info("----- Task1 ----- Insertion -----");
        InsertionSort<Integer> insertionSort = new InsertionSort<>();
        insertionSort.sort(new Integer[] { 26, 35, 38, 67, 70, 89, 58, 17, 50, 59 });

        LOG.info("----- Task1 ----- Shellsort -----");
        ShellSort<Integer> shellSort = new ShellSort<>();
        shellSort.sort(new Integer[] { 24, 87, 72, 92, 40, 82, 65, 22, 84, 67 });

        LOG.info("----- Task2 ----- Selection Sort -----");
        SelectionSort<String> selectionSortString = new SelectionSort<>();
        selectionSortString.sort(new String[] { "root", "lifo", "lazy", "ceil", "push", "find", "byte", "edge", "path", "load", "leaf", "miss",
                "next", "hash", "cost", "data" });

        LOG.info("----- Task2 ----- Insertion Sort -----");
        InsertionSort<String> insertionSortString = new InsertionSort<>();
        insertionSortString.sort(new String[] { "root", "lifo", "lazy", "ceil", "push", "find", "byte", "edge", "path", "load", "leaf", "miss",
                "next", "hash", "cost", "data" });

        LOG.info("----- Task2 ----- Shell Sort -----");
        ShellSort<String> shellSortString = new ShellSort<>();
        shellSortString.sort(new String[] { "root", "lifo", "lazy", "ceil", "push", "find", "byte", "edge", "path", "load", "leaf", "miss", "next",
                "hash", "cost", "data" });
    }
}
