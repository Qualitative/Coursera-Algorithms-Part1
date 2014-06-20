package com.ralko.week1.qu;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class WeightedQuickUnion {

    private final int[] items;
    private final int[] sz;

    public WeightedQuickUnion(int initialCapacity) {
        checkArgument(initialCapacity > 0, "Capacity should be positive: %s", initialCapacity);
        items = new int[initialCapacity];
        sz = new int[initialCapacity];
        
        for (int i = 0; i < items.length; i++) {
            items[i] = i;
        }
        
        for (int i = 0; i < sz.length; i++) {
            sz[i] = 1;
        }
        
    }

    public void union(int p, int q) {
        int proot = root(p);
        int qroot = root(q);
        
        if(sz[qroot] > sz [proot]) {
            items[proot] = qroot;
            sz[qroot] = sz[qroot] + sz[proot];
        } else {
            items[qroot] = proot;
            sz[proot] = sz[proot] + sz[qroot];
        }
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
