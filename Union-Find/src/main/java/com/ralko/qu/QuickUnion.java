package com.ralko.qu;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class QuickUnion {

    private final int[] items;

    public QuickUnion(int initialCapacity) {
        checkArgument(initialCapacity > 0, "Capacity should be positive: %s", initialCapacity);
        items = new int[initialCapacity];

        for (int i = 0; i < items.length; i++) {
            items[i] = i;
        }
    }

    public void union(int p, int q) {
        int proot = root(p);
        int qroot = root(q);
        items[qroot] = proot;
    }


    public boolean find(int p, int q) {
        return root(p) == root(q);
    }

    public String getState() {
        String itemsAsString = Arrays.toString(items);
        itemsAsString = StringUtils.remove(itemsAsString, ',');
        return itemsAsString;
    }

    private int root(int i) {
        while(i != items[i]) i = items[i];
        return i;
    }

}
