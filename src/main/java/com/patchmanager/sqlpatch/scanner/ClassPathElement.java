package com.patchmanager.sqlpatch.scanner;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * User: vgrinyuk
 * Date: 11/1/11
 * Time: 5:57 PM
 */
public abstract class ClassPathElement {

    // (((( Constants ))))

    // (((( Private fields ))))

    protected File file;

    protected static final String separator = System.getProperty("file.separator");

    // (((( Getters & Setters ))))

    // (((( Private methods ))))

    protected ClassPathElement(String path) {
        file = new File(path);
    }

    protected abstract Set<TargetElement> scanForFileMask(String parent, Pattern fileNamePattern);

    // (((( Public methods ))))

    public static ClassPathElement createClassPathElement(String path) {
        if (path.endsWith(".jar")) {
            return new JarClassPathElement(path);
        } else {
            return new DirectoryClassPathElement(path);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassPathElement)) return false;

        ClassPathElement that = (ClassPathElement) o;

        if (file != null ? !file.equals(that.file) : that.file != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return file != null ? file.hashCode() : 0;
    }

    public Collection<TargetElement> scanFor(String path, Pattern fileNamePattern) {
        return scanForFileMask(path, fileNamePattern);
    }

    public abstract InputStream openTargetStream(TargetElement target);
    // (((( Inner objects ))))

}
