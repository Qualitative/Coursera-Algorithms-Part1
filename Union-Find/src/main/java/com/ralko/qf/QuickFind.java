package com.ralko.qf;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class QuickFind {
    
    private int[] items;

    public QuickFind(int initialCapacity) {
        checkArgument(initialCapacity > 0, "Capacity should be positive: %s", initialCapacity);
        items = new int[initialCapacity];
    }

    public String getState() {
        String arrayAsString = Arrays.toString(items);
        arrayAsString = StringUtils.remove(arrayAsString, ',');
        return arrayAsString;
    }

}
