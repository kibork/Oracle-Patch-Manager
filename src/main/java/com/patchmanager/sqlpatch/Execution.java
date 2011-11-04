package com.patchmanager.sqlpatch;

import com.patchmanager.sqlpatch.scanner.TargetElement;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: vgrinyuk
 * Date: 11/1/11
 * Time: 4:35 PM
 */
public class Execution implements Comparable<Execution> {

    // (((( Constants ))))

    private static final String FILE_EXTENSION = ".sql";

    public static final Pattern FILE_PATTERN = Pattern.compile("(.+)_(\\d{8})\\.sql");

    // (((( Private fields ))))

    private String fileSuffix;

    private String fileName;

    private InputStream scriptResource;

    private TargetElement target;

    // (((( Getters & Setters ))))

    public String getFileSuffix() {
        return fileSuffix;
    }

    public String getFileName() {
        return fileName;
    }

    public InputStream getScriptResource() {
        if (scriptResource == null) {
            scriptResource = target.getInputStream();
        }
        return scriptResource;
    }

    // (((( Private methods ))))

    // (((( Public methods ))))

    public Execution(TargetElement target) {
        this.target = target;
        Matcher nameMatcher = FILE_PATTERN.matcher(target.getName());
        if (!nameMatcher.matches()) {
            throw new RuntimeException(String.format("Invalid script name %s, a valid script name must by 'name_date.sql' where date is in format of yyyymmdd", target.getName()));
        }
        fileName = nameMatcher.group(1);
        fileSuffix = nameMatcher.group(2);
    }

    public int compareTo(Execution o) {
        return getFileSuffix().compareTo(o.getFileSuffix());
    }

    public String getFullPath() {
        return target.getInternalName();
    }

    // (((( Inner objects ))))

}
