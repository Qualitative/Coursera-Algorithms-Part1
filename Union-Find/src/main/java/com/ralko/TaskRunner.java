package com.ralko;

import com.ralko.qf.QuickFind;
import com.ralko.qu.WeightedQuickUnion;

public class TaskRunner {
    public static void main(String[] args) {
        
        QuickFind qf = new QuickFind(10);
        WeightedQuickUnion wqu = new WeightedQuickUnion(10);
        
        for(int i = 0; i < args.length; i++) {
            String[] pair = args[i].split("-");
            int p = Integer.parseInt(pair[0]);
            int q = Integer.parseInt(pair[1]);
            qf.union(p, q);
            wqu.union(p, q);
        }
        
        
        // FIXME: use logger
        System.out.println("Quick Find:");
        System.out.println(qf.getState());
        System.out.println("Weighted Quick Union:");
        System.out.println(wqu.getState());
    }
}
