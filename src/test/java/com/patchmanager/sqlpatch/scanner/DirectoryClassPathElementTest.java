package com.patchmanager.sqlpatch.scanner;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * User: vgrinyuk
 * Date: 11/2/11
 * Time: 10:39 AM
 */
public class DirectoryClassPathElementTest {

    // (((( Constants ))))

    // (((( Private fields ))))

    // (((( Getters & Setters ))))

    // (((( Private methods ))))

    // (((( Public methods ))))

    @Test
    public void testSearch() {
        ClassPath classPath = new ClassPath();
        final Collection<TargetElement> targets = classPath.findTargets("testsql/", Pattern.compile(".+\\.sql"));
        Assert.assertEquals(2, targets.size());
        for (TargetElement element : targets) {
            if ((!element.getName().equals("test1_20111101.sql")) && (!element.getName().equals("test2_20111102.sql"))) {
                Assert.fail(String.format("Invalid target found %s", element.getName()));
            }
        }
    }

    @Test
    public void testOpenStream() throws IOException {
        ClassPath classPath = new ClassPath();
        final Collection<TargetElement> targets = classPath.findTargets("testsql/", Pattern.compile("test1_20111101\\.sql"));
        Assert.assertEquals(1, targets.size());
        final TargetElement element = targets.iterator().next();
        InputStream resourceStream = element.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream));
        Assert.assertEquals("Dummy content", reader.readLine());
    }

    // (((( Inner objects ))))

}
