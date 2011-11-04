package com.patchmanager.sqlpatch.scanner;

import java.util.*;
import java.util.regex.Pattern;

/**
 * User: vgrinyuk
 * Date: 11/1/11
 * Time: 5:53 PM
 */
public class ClassPath {

    // (((( Constants ))))

    // (((( Private fields ))))

    private Collection<ClassPathElement> classPathLocations;

    // (((( Getters & Setters ))))

    public Collection<ClassPathElement> getClassPathLocations() {
        return classPathLocations;
    }

    // (((( Private methods ))))

    // (((( Public methods ))))

    public ClassPath() {
        String pathSep = System.getProperty ("path.separator");
        String classpath = System.getProperty ("java.class.path");


        Set<ClassPathElement> locations = new HashSet<ClassPathElement>();
        StringTokenizer st = new StringTokenizer (classpath, pathSep);
        while (st.hasMoreTokens ())
        {
            String path = st.nextToken ();
            locations.add(ClassPathElement.createClassPathElement(path));
        }
        classPathLocations = Collections.unmodifiableCollection(locations);
    }

    public Collection<TargetElement> findTargets(String path, Pattern pattern) {
        Collection<TargetElement> targets = new HashSet<TargetElement>();
        for (ClassPathElement element : classPathLocations) {
            targets.addAll(element.scanFor(path, pattern));
        }
        return targets;
    }

    // (((( Inner objects ))))

}
