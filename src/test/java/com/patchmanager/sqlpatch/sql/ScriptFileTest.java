package com.patchmanager.sqlpatch.sql;

import com.patchmanager.sqlpatch.Execution;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * User: vgrinyuk
 * Date: 11/2/11
 * Time: 1:41 PM
 */
public class ScriptFileTest {

    // (((( Constants ))))

    // (((( Private fields ))))

    // (((( Getters & Setters ))))

    // (((( Private methods ))))

    // (((( Public methods ))))

    // (((( Inner objects ))))

    @Test
    public void testGetCommands() throws Exception {
        Execution execution = mock(Execution.class);
        when(execution.getScriptResource()).thenReturn(getClass().getResourceAsStream("/testscript/test.sql"));

        ScriptFile scriptFile = new ScriptFile(execution);
        final List<Command> commands = scriptFile.getCommands();
        Assert.assertEquals(5, commands.size());
    }
}
