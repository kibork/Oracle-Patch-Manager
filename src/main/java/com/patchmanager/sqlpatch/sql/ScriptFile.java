package com.patchmanager.sqlpatch.sql;

import com.patchmanager.sqlpatch.Execution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: vgrinyuk
 * Date: 11/2/11
 * Time: 12:24 PM
 */
public class ScriptFile {

    // (((( Constants ))))

    // (((( Private fields ))))

    private Execution execution;

    private boolean parsed = false;

    private List<Command> commands = new ArrayList<Command>();

    // (((( Getters & Setters ))))

    public List<Command> getCommands() {
        if (!parsed) {
            try {
                parse();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            parsed = true;
        }
        return Collections.unmodifiableList(commands);
    }

    // (((( Private methods ))))

    protected void parse() throws IOException {
        commands.clear();
        InputStream resourceStream = execution.getScriptResource();
        BufferedReader scriptReader = new BufferedReader(new InputStreamReader(resourceStream));
        String line;
        StringBuilder commandBuffer = new StringBuilder();
        long lineNumber = 0;
        while ((line = scriptReader.readLine()) != null) {
            ++lineNumber;
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            if (line.equals("/")) {//End of command
                commands.add(new Command(commandBuffer.toString(), lineNumber, this));
                commandBuffer.delete(0, commandBuffer.length());
            } else {
                if (commandBuffer.length() > 0) {
                    commandBuffer.append("\n");
                }
                commandBuffer.append(line);
            }
        }
        if (commandBuffer.length() > 0) {
            commands.add(new Command(commandBuffer.toString(), lineNumber, this));
        }
        scriptReader.close();
    }

    // (((( Public methods ))))

    public ScriptFile(Execution execution) {
        this.execution = execution;
    }

    public String getFullPath() {
        return execution.getFullPath();
    }

    // (((( Inner objects ))))

}
