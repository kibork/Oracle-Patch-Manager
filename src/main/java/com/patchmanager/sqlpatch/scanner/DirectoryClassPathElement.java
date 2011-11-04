package com.patchmanager.sqlpatch.scanner;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: vgrinyuk
 * Date: 11/1/11
 * Time: 6:15 PM
 */
public class DirectoryClassPathElement extends ClassPathElement  {

    // (((( Constants ))))

    // (((( Private fields ))))

    // (((( Getters & Setters ))))

    // (((( Private methods ))))

    protected Collection<TargetElement> getChildren(String parent) {
        File parentDir = new File(file.getAbsolutePath() + separator + parent);
        Set<TargetElement> elements = new HashSet<TargetElement>();
        if (!parentDir.exists() || !parentDir.isDirectory()) {
            return elements;
        }
        final File[] files = parentDir.listFiles();
        for (File file : files) {
            elements.add(new TargetElement(parent, file.getName(), file.isDirectory(), file.getAbsolutePath(), this));
        }
        return elements;
    }

    protected Set<TargetElement> scanForFileMask(String parent, Pattern fileNamePattern) {
        Collection<TargetElement> children = getChildren(parent);

        Set<TargetElement> matchedElements = new HashSet<TargetElement>();
        for (TargetElement child : children) {
            if (!child.isDirectory()) {
                Matcher m = fileNamePattern.matcher(child.getName());
                if (m.matches()) {
                    matchedElements.add(child);
                }
            } else {
                matchedElements.addAll(scanForFileMask(parent + separator + child.getName(), fileNamePattern));
            }
        }
        return matchedElements;
    }


    // (((( Public methods ))))

    public DirectoryClassPathElement(String path) {
        super(path);
    }

    @Override
    public InputStream openTargetStream(TargetElement target) {
        File targetFile = new File(target.getInternalName());
        if (!targetFile.exists() || !targetFile.isFile() || !targetFile.canRead()) {
            throw new RuntimeException(String.format("Can't open stream for target %s", targetFile.getAbsolutePath()));
        }
        try {
            return new BufferedInputStream(new FileInputStream(targetFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "DirectoryClassPathElement:" + file.getAbsolutePath();
    }

    // (((( Inner objects ))))

}
