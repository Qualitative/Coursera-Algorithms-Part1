package com.ralko.week1.qf;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

public class QuickFind {

    private final int[] items;

    public QuickFind(int initialCapacity) {
        checkArgument(initialCapacity > 0, "Capacity should be positive: %s", initialCapacity);
        items = new int[initialCapacity];

        for (int i = 0; i < items.length; i++) {
            items[i] = i;
        }
    }

    public void union(int p, int q) {
        int pid = items[p];
        int qid = items[q];

        if (pid == qid) return;

        for(int i = 0; i < items.length; i++) {
            if (items[i] == pid) items[i] = qid;
        }

    }

    public boolean find(int p, int q) {
        return items[p] == items[q];
    }

    public String getState() {
        String itemsAsString = Arrays.toString(items);
        itemsAsString = StringUtils.remove(itemsAsString, ',');
        return itemsAsString;
    }

}
