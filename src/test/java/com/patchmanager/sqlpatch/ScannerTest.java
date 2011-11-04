package com.patchmanager.sqlpatch;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: vgrinyuk
 * Date: 11/2/11
 * Time: 12:14 PM
 */
public class ScannerTest {

    // (((( Constants ))))

    // (((( Private fields ))))

    // (((( Getters & Setters ))))

    // (((( Private methods ))))

    // (((( Public methods ))))

    // (((( Inner objects ))))

    @Test
    public void testGetExecutions() throws Exception {
        Scanner scanner = new Scanner("testsql");
        ExecutionList list = scanner.getExecutions();
        Assert.assertEquals(2, list.getExecutions().size());
        final Execution[] executions = list.getExecutions().toArray(new Execution[2]);
        Assert.assertEquals("test1", executions[0].getFileName());
        Assert.assertEquals("test2", executions[1].getFileName());
    }
}
