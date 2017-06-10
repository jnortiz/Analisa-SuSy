package com.ic.analisaSusy.commons;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ShellInterfaceTest {

    @Mock
    private ProcessBuilder processBuilderMock;

    @Test
    public void testExecuteCommand() throws IOException {

        final ShellInterface shellInterface = new ShellInterface();
        shellInterface.executeCommand("analizo metrics");
    }

    @Test(expected = NullPointerException.class)
    public void testExecuteCommandException() throws IOException {

        final ShellInterface shellInterface = new ShellInterface();

        when(processBuilderMock.start()).thenThrow(Mockito.mock(IOException.class));

        shellInterface.executeCommand("analizo metrics");
    }

    @Test
    public void testGetOutput() throws ParseException {

        final ShellInterface shellInterface = new ShellInterface();
        final String output = shellInterface.getOutput();
        assertNotNull(output);
    }
}
