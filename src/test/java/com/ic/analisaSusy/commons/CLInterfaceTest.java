package com.ic.analisaSusy.commons;

import static org.mockito.Mockito.when;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.ParseException;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * @author dmarinho
 *
 */
public class CLInterfaceTest {

    private static final String PARAMETER_FILE_CONFIG = "-cfg";

    private static final String PARAMETER_FILE_NONEXISTENT = "a";

    private static final String PARAMETER_FILE = "-f";

    private static final String PATH_FILE = "/Files/carregados.txt";

    private static final String PATH_FILE_CONFIG = "/home/CIT/dmarinho/config.xml";

    private static final String PATH_FILE_NONEXISTENT = "/arquivoInexistente.txt";

    @Mock
    private CommandLineParser commandLineParserMock;

    @Test(expected = ParseException.class)
    public void testParseOptionFileNotFoundException() throws ParseException {

        final String[] arguments = { PARAMETER_FILE, PATH_FILE_NONEXISTENT };
        final CLInterface clInterface = new CLInterface(arguments);
        clInterface.parse();
    }

    @Test
    public void testParseOptionFile() throws ParseException {

        final String filePath = this.getClass().getResource(PATH_FILE).getFile();
        final String[] arguments = { PARAMETER_FILE, filePath };
        final CLInterface clInterface = new CLInterface(arguments);
        clInterface.parse();
    }

    @Test
    public void testParseOptionCfgFile() throws ParseException {

        final String filePath = this.getClass().getResource(PATH_FILE).getFile();
        final String[] arguments = { PARAMETER_FILE, filePath, PARAMETER_FILE_CONFIG, PATH_FILE_CONFIG };
        final CLInterface clInterface = new CLInterface(arguments);
        clInterface.parse();
    }

    @Test
    public void testFilepaths() throws ParseException {

        final String filePath = this.getClass().getResource(PATH_FILE).getFile();
        final String[] arguments = { PARAMETER_FILE, filePath, PARAMETER_FILE_CONFIG, PATH_FILE_CONFIG };
        final CLInterface clInterface = new CLInterface(arguments);
        clInterface.getFilepaths();
    }

    @Test(expected = ParseException.class)
    public void testParseOptionUnknown() throws ParseException {

        final String[] arguments = { PARAMETER_FILE_NONEXISTENT };
        final CLInterface clInterface = new CLInterface(arguments);
        clInterface.parse();
    }

    @Test(expected = NullPointerException.class)
    public void testParseOptionNullPointerException() throws ParseException {

        when(commandLineParserMock.parse(Mockito.anyObject(), Mockito.anyVararg())).thenThrow(Mockito.mock(NullPointerException.class));

        final CLInterface clInterface = new CLInterface(null);
        clInterface.parse();
    }

}
