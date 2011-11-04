package com.patchmanager.sqlpatch;

import com.patchmanager.sqlpatch.scanner.TargetElement;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * User: vgrinyuk
 * Date: 11/1/11
 * Time: 5:33 PM
 */
public class ExecutionListTest {

    // (((( Constants ))))

    // (((( Private fields ))))

    // (((( Getters & Setters ))))

    // (((( Private methods ))))

    private TargetElement getAsTarget(String name) {
        TargetElement element = mock(TargetElement.class);
        when(element.getName()).thenReturn(name);
        return element;
    }

    // (((( Public methods ))))

    // (((( Inner objects ))))

    @Test
    public void testGetExecutions() throws Exception {
        ExecutionList list = new ExecutionList();
        list.addExecution(new Execution(getAsTarget("test_20111102.sql")));
        list.addExecution(new Execution(getAsTarget("test_20111101.sql")));
        list.addExecution(new Execution(getAsTarget("test_20111103.sql")));

        List<Execution> executions = new ArrayList<Execution>(list.getExecutions());
        Assert.assertEquals("20111101", executions.get(0).getFileSuffix());
        Assert.assertEquals("20111102", executions.get(1).getFileSuffix());
        Assert.assertEquals("20111103", executions.get(2).getFileSuffix());
    }
}
