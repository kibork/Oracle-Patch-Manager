package com.patchmanager.sqlpatch.sql;

/**
 * User: vgrinyuk
 * Date: 11/2/11
 * Time: 12:25 PM
 */
public class Command {

    // (((( Constants ))))

    // (((( Private fields ))))

    private String commandText;

    private ScriptFile scriptFile;

    private long lineNumber;

    // (((( Getters & Setters ))))

    public String getCommandText() {
        return commandText;
    }

    public ScriptFile getScriptFile() {
        return scriptFile;
    }

    public long getLineNumber() {
        return lineNumber;
    }

    // (((( Private methods ))))

    // (((( Public methods ))))

    public Command(String commandText, long lineNumber, ScriptFile scriptFile) {
        this.commandText = commandText;
        this.scriptFile = scriptFile;
        this.lineNumber = lineNumber;
    }

    // (((( Inner objects ))))

}
