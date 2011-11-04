package com.patchmanager.sqlpatch.scanner;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * User: vgrinyuk
 * Date: 11/1/11
 * Time: 6:13 PM
 */
public class JarClassPathElement extends ClassPathElement {

    // (((( Constants ))))

    // (((( Private fields ))))

    private ZipFile zipFile;

    // (((( Getters & Setters ))))

    // (((( Private methods ))))

    @Override
    protected Set<TargetElement> scanForFileMask(String parent, Pattern fileNamePattern) {
        try {
            Set<TargetElement> targets = new HashSet<TargetElement>();
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.getName().startsWith(parent)) {
                    continue;
                }
                File fileName = new File(entry.getName());
                Matcher m = fileNamePattern.matcher(fileName.getName());
                if (m.matches()) {
                    targets.add(new TargetElement(fileName.getParent(), fileName.getName(), entry.isDirectory(), entry.getName(), this));
                }
            }
            return targets;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    // (((( Public methods ))))

    public JarClassPathElement(String path) {
        super(path);
        try {
            zipFile = new ZipFile(file);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public InputStream openTargetStream(TargetElement target) {
        try {
            final ZipEntry entry = zipFile.getEntry(target.getInternalName());
            if (entry == null) {
                throw new RuntimeException(String.format("Can't open target with internal name %s", target.getInternalName()));
            }
            return new BufferedInputStream(zipFile.getInputStream(entry));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toString() {
        return "JarClassPathElement:" + file.getAbsolutePath();
    }

    // (((( Inner objects ))))

}
