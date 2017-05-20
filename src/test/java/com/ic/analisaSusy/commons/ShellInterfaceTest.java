package com.ic.analisaSusy.commons;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

public class ShellInterfaceTest {

    @Mock
    private ProcessBuilder processBuilderMock;

    @Test
    public void testExecuteCommand() throws IOException {

        final ShellInterface shellInterface = new ShellInterface();
        shellInterface.executeCommand("analizo metrics");
    }

    @Ignore
    @Test(expected = IOException.class)
    public void testExecuteCommandException() throws IOException {

        final ShellInterface shellInterface = new ShellInterface();

        doThrow(new IOException()).when(processBuilderMock).start();

        shellInterface.executeCommand("analizo metrics");
    }

    @Test
    public void testGetOutput() throws ParseException {

        final ShellInterface shellInterface = new ShellInterface();
        final String output = shellInterface.getOutput();
        assertNull(output);
    }
}
