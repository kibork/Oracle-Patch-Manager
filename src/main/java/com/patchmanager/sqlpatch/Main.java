package com.patchmanager.sqlpatch;

/**
 * User: vgrinyuk
 * Date: 11/1/11
 * Time: 4:34 PM
 */
public class Main {

    // (((( Constants ))))

    // (((( Private fields ))))

    // (((( Getters & Setters ))))

    // (((( Private methods ))))

    // (((( Public methods ))))

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            System.out.println("Incorrect parameter list");
            System.out.println("Usage:");
            System.out.println("java -jar sqlpatch.jar {path to scan} {jdbc url} {schema} {password}");
            System.out.println("Where:");
            System.out.println("    path to scan - the directory root to search sql files in");
            System.out.println("    jdbc url - a full jdbc url to the target database");
            System.out.println("    schema - Oracle schema name to connect to on the specified database");
            System.out.println("    password - Oracle schema password");
            System.out.println("Example:");
            System.out.println("java -jar sqlpatch.jar testonironman jdbc:oracle:thin:@myserver:1551:D1 user1 password");
        } else {
            PatchLanucher.main(args[0], args[1], args[2], args[3]);
        }
    }

    // (((( Inner objects ))))

}
