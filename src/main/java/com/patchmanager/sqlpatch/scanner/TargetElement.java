package com.patchmanager.sqlpatch.scanner;

import java.io.InputStream;

/**
 * User: vgrinyuk
 * Date: 11/1/11
 * Time: 6:37 PM
 */
public class TargetElement {

    // (((( Constants ))))

    // (((( Private fields ))))

    private ClassPathElement classPathElement;

    private String parent;

    private String name;

    private boolean directory;

    private String internalName;

    // (((( Getters & Setters ))))

    public String getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public boolean isDirectory() {
        return directory;
    }

    public String getInternalName() {
        return internalName;
    }

    // (((( Private methods ))))

    // (((( Public methods ))))

    public TargetElement(String parent, String name, boolean directory, String internalName, ClassPathElement classPathElement) {
        this.parent = parent;
        if (this.parent == null) {
            this.parent = "";
        }
        this.name = name;
        this.directory = directory;
        this.classPathElement = classPathElement;
        this.internalName = internalName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TargetElement)) return false;

        TargetElement that = (TargetElement) o;

        if (directory != that.directory) return false;
        if (classPathElement != null ? !classPathElement.equals(that.classPathElement) : that.classPathElement != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classPathElement != null ? classPathElement.hashCode() : 0;
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (directory ? 1 : 0);
        return result;
    }

    public InputStream getInputStream() {
        if (directory) {
            throw new RuntimeException(String.format("Can't open a directory as input stream. Target parent %s, name %s", parent, name));
        }
        return classPathElement.openTargetStream(this);
    }

    // (((( Inner objects ))))

}
