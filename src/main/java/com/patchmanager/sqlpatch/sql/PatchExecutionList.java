package com.patchmanager.sqlpatch.sql;

import com.patchmanager.sqlpatch.Execution;
import com.patchmanager.sqlpatch.ExecutionList;

import java.util.ArrayList;
import java.util.List;

/**
 * User: vgrinyuk
 * Date: 11/2/11
 * Time: 1:34 PM
 */
public class PatchExecutionList {

    // (((( Constants ))))

    // (((( Private fields ))))

    private List<ScriptFile> scriptFiles = new ArrayList<ScriptFile>();

    private boolean commandsCalculated = false;

    private List<Command> commands = new ArrayList<Command>();

    // (((( Getters & Setters ))))

    public List<ScriptFile> getScriptFiles() {
        return scriptFiles;
    }

    public List<Command> getCommands() {
        if (!commandsCalculated) {
            calculateCommandList();
            commandsCalculated = true;
        }
        return commands;
    }

    // (((( Private methods ))))


    private void calculateCommandList() {
        for (ScriptFile scriptFile : scriptFiles) {
            commands.addAll(scriptFile.getCommands());
        }
    }

    // (((( Public methods ))))

    public PatchExecutionList(ExecutionList executionList) {
        for (Execution execution : executionList.getExecutions()) {
            scriptFiles.add(new ScriptFile(execution));
        }
    }



    // (((( Inner objects ))))

}
