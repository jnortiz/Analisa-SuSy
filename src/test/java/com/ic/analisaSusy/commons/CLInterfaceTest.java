package com.ic.analisaSusy.commons;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.ParseException;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.google.common.collect.Multimap;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;

/**
 * @author dmarinho
 *
 */
public class CLInterfaceTest {

    @Mock
    private CommandLineParser commandLineParserMock;

    @Test
    public void testParseOptionHelp() {

        final String[] arguments = { "-h" };
        final CLInterface clInterface = new CLInterface(arguments);
        clInterface.parse();
    }

    @Test
    public void testParseOptionCfgFile() {

        final String[] arguments = { "-f", "/home/CIT/dmarinho/ANALIZO/t/features/exemplo1.c", "-cfg", "/home/CIT/dmarinho/config.xml" };
        final CLInterface clInterface = new CLInterface(arguments);
        clInterface.parse();
    }

    @Test
    public void testParseOptionUnknown() {

        final String[] arguments = { "a" };
        final CLInterface clInterface = new CLInterface(arguments);
        clInterface.parse();
    }

    @Test(expected = NullPointerException.class)
    public void testParseOptionException() throws ParseException {

        when(commandLineParserMock.parse(Mockito.anyObject(), Mockito.anyVararg())).thenThrow(Mockito.mock(NullPointerException.class));

        final CLInterface clInterface = new CLInterface(null);
        clInterface.parse();
    }

    @Test
    public void testConfigurationFile() {

        final String[] arguments = { "a", "b", "c" };
        final CLInterface clInterface = new CLInterface(arguments);
        final Multimap<Tool, Metric> configurationFile = clInterface.getMetricsPerTool();
        assertNull(configurationFile);
    }

}
