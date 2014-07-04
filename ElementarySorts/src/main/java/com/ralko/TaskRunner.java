package com.ralko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ralko.sort.InsertionSort;
import com.ralko.sort.SelectionSort;
import com.ralko.sort.ShellSort;

public class TaskRunner {
    
    private static final Logger LOG = LoggerFactory.getLogger(TaskRunner.class);
    
    public static void main(String[] args) {
        LOG.info("----- Task1 -----");
        SelectionSort<Integer> selectionSort = new SelectionSort<>();
        selectionSort.sort(new Integer[] { 12, 95, 48, 65, 29, 43, 14, 75, 99, 10 });

        LOG.info("----- Task2 ----- Selection Sort -----");
        SelectionSort<String> selectionSortString = new SelectionSort<>();
        selectionSortString.sort(new String[] { "find", "push", "loop", "left", "hash", "next", "list", "data", "root",
                "path", "lifo", "heap", "flip", "sink", "byte", "sort" });
        
        LOG.info("----- Task2 ----- Insertion Sort -----");
        InsertionSort<String> insertionString = new InsertionSort<>();
        insertionString.sort(new String[] { "find", "push", "loop", "left", "hash", "next", "list", "data", "root",
                "path", "lifo", "heap", "flip", "sink", "byte", "sort" });
        
        LOG.info("----- Task2 ----- Shell Sort -----");
        ShellSort<String> shellSort = new ShellSort<>();
        shellSort.sort(new String[] { "find", "push", "loop", "left", "hash", "next", "list", "data", "root",
                "path", "lifo", "heap", "flip", "sink", "byte", "sort" });
    }
}
