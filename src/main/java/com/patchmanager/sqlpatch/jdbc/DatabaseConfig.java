package com.patchmanager.sqlpatch.jdbc;

/**
 * User: vgrinyuk
 * Date: 11/2/11
 * Time: 2:30 PM
 */
public class DatabaseConfig {

    // (((( Constants ))))

    // (((( Private fields ))))

    private String url;

    private String user;

    private String password;

    // (((( Getters & Setters ))))

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    // (((( Private methods ))))

    // (((( Public methods ))))

    public DatabaseConfig(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static DatabaseConfig getSystemConfig() {
        return new DatabaseConfig(System.getProperty("jdbc_url"), System.getProperty("jdbc_user"), System.getProperty("jdbc_password"));
    }

    // (((( Inner objects ))))

}
