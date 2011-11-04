package com.patchmanager.sqlpatch;

import com.patchmanager.sqlpatch.jdbc.DatabaseConfig;
import com.patchmanager.sqlpatch.jdbc.JdbcExecutor;
import com.patchmanager.sqlpatch.sql.PatchExecutionList;

/**
 * User: vgrinyuk
 * Date: 11/3/11
 * Time: 3:59 PM
 */
public class PatchLanucher {

    // (((( Constants ))))

    // (((( Private fields ))))

    // (((( Getters & Setters ))))

    // (((( Private methods ))))

    // (((( Public methods ))))

    public static void main(String pathToScan, String jdbc_url, String jdbc_user, String jdbc_pass) throws Exception {
        Scanner scanner = new Scanner(pathToScan);
        ExecutionList list = scanner.getExecutions();
        if (!list.getExecutions().isEmpty()) {
            PatchExecutionList patchExecutionList = new PatchExecutionList(list);
            DatabaseConfig databaseConfig = new DatabaseConfig(jdbc_url, jdbc_user, jdbc_pass);
            JdbcExecutor executor = new JdbcExecutor(patchExecutionList.getCommands(), databaseConfig);
            executor.execute();
        }
    }

    // (((( Inner objects ))))

}
