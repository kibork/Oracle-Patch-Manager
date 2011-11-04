package com.patchmanager.sqlpatch;

import com.patchmanager.sqlpatch.scanner.ClassPath;
import com.patchmanager.sqlpatch.scanner.TargetElement;

import java.util.Collection;

/**
 * User: vgrinyuk
 * Date: 11/1/11
 * Time: 5:17 PM
 */
public class Scanner {

    // (((( Constants ))))

    // (((( Private fields ))))

    private String pathToScan;

    private ClassPath classPath = new ClassPath();

    // (((( Getters & Setters ))))

    // (((( Private methods ))))

    // (((( Public methods ))))

    public Scanner(String pathToScan) {
        this.pathToScan = pathToScan;
    }

    public ExecutionList getExecutions() {
        final Collection<TargetElement> targets = classPath.findTargets(pathToScan, Execution.FILE_PATTERN);

        ExecutionList list = new ExecutionList();
        for (TargetElement target : targets) {
            list.addExecution(new Execution(target));
        }
        return list;
    }

    // (((( Inner objects ))))

}
