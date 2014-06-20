package com.ralko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ralko.qf.QuickFind;
import com.ralko.qu.WeightedQuickUnion;

public class TaskRunner {
    
    private static final Logger log = LoggerFactory.getLogger(TaskRunner.class);
    
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
        
        log.info("Quick Find result: {}", qf.getState());
        log.info("Weighted Quick Union result: {}", wqu.getState());
    }
    
}
