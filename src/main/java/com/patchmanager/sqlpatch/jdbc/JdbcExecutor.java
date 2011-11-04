package com.patchmanager.sqlpatch.jdbc;

import com.patchmanager.sqlpatch.sql.Command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

/**
 * User: vgrinyuk
 * Date: 11/2/11
 * Time: 2:26 PM
 */
public class JdbcExecutor {

    // (((( Constants ))))

    // (((( Private fields ))))

    private Connection databaseConnection;

    private List<Command> commands;

    private DatabaseConfig databaseConfig;

    // (((( Getters & Setters ))))

    // (((( Private methods ))))

    protected void initializeConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            databaseConnection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUser(), databaseConfig.getPassword());
            databaseConnection.setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // (((( Public methods ))))

    public JdbcExecutor(List<Command> commands, DatabaseConfig databaseConfig) {
        this.commands = commands;
        this.databaseConfig = databaseConfig;
    }

    public void execute() throws Exception {
        if (databaseConnection == null) {
            initializeConnection();
        }
        for (Command command : commands) {
            try {
                final Statement statement = databaseConnection.createStatement();
                statement.execute(command.getCommandText());
            } catch (Exception ex) {
                databaseConnection.rollback();
                throw new RuntimeException(String.format("Error while executing a command from script file %s at line %s.\nCommand content:\n%s",
                        command.getScriptFile().getFullPath(), command.getLineNumber(), command.getCommandText()));
            }
        }
        databaseConnection.commit();
    }

    // (((( Inner objects ))))

}
