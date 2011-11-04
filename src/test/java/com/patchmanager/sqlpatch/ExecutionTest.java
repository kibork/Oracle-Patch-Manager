package com.patchmanager.sqlpatch;

import com.patchmanager.sqlpatch.scanner.TargetElement;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * User: vgrinyuk
 * Date: 11/1/11
 * Time: 5:18 PM
 */
public class ExecutionTest {

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

    @Test
    public void initialConstruction() {
        Execution ex = new Execution(getAsTarget("test_20111103.sql"));
        Assert.assertEquals("test", ex.getFileName());
        Assert.assertEquals("20111103", ex.getFileSuffix());

        ex = new Execution(getAsTarget("test2_2_20111103.sql"));
        Assert.assertEquals("test2_2", ex.getFileName());
        Assert.assertEquals("20111103", ex.getFileSuffix());
    }

    @Test(expected = RuntimeException.class)
    public void invalidExtension() {
        new Execution(getAsTarget("test_20111103.txt"));
    }

    @Test(expected = RuntimeException.class)
    public void invalidSuffix() {
        new Execution(getAsTarget("test_2011113.sql"));
    }

    @Test(expected = RuntimeException.class)
    public void invalidDelimiter() {
        new Execution(getAsTarget("test-20111131.sql"));
    }

    // (((( Inner objects ))))

}
