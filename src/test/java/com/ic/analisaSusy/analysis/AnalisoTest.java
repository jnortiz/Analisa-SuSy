package com.ic.analisaSusy.analysis;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author dmarinho
 *
 */
public class AnalisoTest {

    private static final String SEPARATOR_TOKEN = "#";

    private static final String FILE_PATH_OUTPUT = "/bin/output";

    private static final String FILE_PATH = "/Files/carregados.txt";

    private static Analiso analiso;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        final String filePath = AnalisoTest.class.getClass().getResource(FILE_PATH).getFile();

        // GIVEN a analise event
        analiso = new Analiso(Arrays.asList(filePath),
                Arrays.asList(Metric.FUNC_C, Metric.CASE_C, Metric.BREAK_C, Metric.GOTO_C, Metric.CONTINUE_C, Metric.VAR_FILE_LOC_C, Metric.RETURN_POINT_C,
                        Metric.CMNT_DENS, Metric.CC, Metric.UNQ_FUNC_CALL, Metric.PARAM_PER_FUNC, Metric.NESTING_FUNC_C, Metric.HAL_VOC, Metric.HAL_LEN,
                        Metric.HAL_CALC_LEN, Metric.HAL_D));
    }

    @Test
    public void testParseCommand() {

        // WHEN parse command effect
        final String parseCommand = analiso.parseCommand();

        // THEN parse command will be executed
        assertNull(parseCommand);
    }

    @Test
    public void testExecuteSpecificParseCommand() {

        final String filePath = AnalisoTest.class.getClass().getResource(FILE_PATH).getFile();

        // GIVEN a analysis event
        analiso = new Analiso(Arrays.asList(filePath), Arrays.asList(Metric.PARAM_PER_FUNC));

        // WHEN parse command effect
        final String parseCommand = analiso.parseCommand();

        // THEN parse command will be executed
        assertNull(parseCommand);
    }

    @Test
    public void testParseOutput() {

        analiso.parseOutput(FILE_PATH_OUTPUT);
    }

    @Ignore
    @Test
    public void testRunTool() {

        analiso.runTool();
    }

    @Test
    public void testGetOutput() {

        // WHEN parse command effect
        final String output = analiso.getOutput();

        // THEN parse command will be executed
        assertNull(output);
    }

    @Test
    public void testMetricsToString() {

        // GIVEN a metrics
        final List<Metric> metrics = Arrays.asList(Metric.FUNC_C, Metric.CASE_C, Metric.BREAK_C, Metric.GOTO_C, Metric.CONTINUE_C, Metric.VAR_FILE_LOC_C,
                Metric.RETURN_POINT_C, Metric.CMNT_DENS, Metric.CC, Metric.UNQ_FUNC_CALL, Metric.PARAM_PER_FUNC, Metric.NESTING_FUNC_C, Metric.HAL_VOC,
                Metric.HAL_LEN, Metric.HAL_CALC_LEN, Metric.HAL_D);

        // WHEN parse command effect
        final String output = analiso.metricsToString(metrics, SEPARATOR_TOKEN);

        // THEN parse command will be executed
        assertNotNull(output);
    }

    @Test
    public void testFilesToString() {

        final String filePath = AnalisoTest.class.getClass().getResource(FILE_PATH).getFile();

        // GIVEN a files path list
        final List<String> files = Arrays.asList(filePath);

        // WHEN parse command effect
        final String output = analiso.filesToString(files, SEPARATOR_TOKEN);

        // THEN parse command will be executed
        assertNotNull(output);
    }

}
