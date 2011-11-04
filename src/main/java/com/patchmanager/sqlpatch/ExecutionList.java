package com.patchmanager.sqlpatch;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * User: vgrinyuk
 * Date: 11/1/11
 * Time: 4:54 PM
 */
public class ExecutionList {

    // (((( Constants ))))

    // (((( Private fields ))))

    private SortedSet<Execution> executions = new TreeSet<Execution>();

    // (((( Getters & Setters ))))

    public SortedSet<Execution> getExecutions() {
        return executions;
    }

    // (((( Private methods ))))

    // (((( Public methods ))))

    public void addExecution(Execution execution) {
        executions.add(execution);
    }

    // (((( Inner objects ))))

}
