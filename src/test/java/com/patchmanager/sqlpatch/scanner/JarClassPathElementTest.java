package com.patchmanager.sqlpatch.scanner;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * User: vgrinyuk
 * Date: 11/1/11
 * Time: 7:00 PM
 */
public class JarClassPathElementTest {

    // (((( Constants ))))

    // (((( Private fields ))))

    // (((( Getters & Setters ))))

    // (((( Private methods ))))

    // (((( Public methods ))))

    @Test
    public void initial() {
        new ClassPath();
    }

    @Test
    public void testSeach() {
        ClassPath classPath = new ClassPath();
        final Collection<TargetElement> targets = classPath.findTargets("org/junit", Pattern.compile("Assert\\.class"));
        Assert.assertEquals(1, targets.size());
        for (TargetElement element : targets) {
            Assert.assertEquals("Assert.class", element.getName());
        }
    }

    @Test
    public void testOpenStream() throws IOException {
        ClassPath classPath = new ClassPath();
        final Collection<TargetElement> testtargets = classPath.findTargets("", Pattern.compile("testcontent\\.jar"));
        Assert.assertEquals("Test Jar file not found", 1, testtargets.size());
        final TargetElement jartarget = testtargets.iterator().next();

        JarClassPathElement jarClassPathElement = new JarClassPathElement(jartarget.getInternalName());

        final Collection<TargetElement> targets = jarClassPathElement.scanFor("sun/nio/cs", Pattern.compile(".+\\.class"));
        Assert.assertEquals("Test target not found", 1, targets.size());
        final TargetElement element = targets.iterator().next();
        Assert.assertEquals("SingleByte.class", element.getName());
        InputStream resourceStream = element.getInputStream();
        byte[] expected = new byte[] { (byte)0xca, (byte)0xfe, (byte)0xba, (byte)0xbe, 0x00, 0x00, 0x00, 0x31 };
        byte[] buffer = new byte[expected.length];
        int result = resourceStream.read(buffer);
        Assert.assertEquals(expected.length, result);
        Assert.assertArrayEquals(expected, buffer);
        resourceStream.close();
    }

    // (((( Inner objects ))))

}
