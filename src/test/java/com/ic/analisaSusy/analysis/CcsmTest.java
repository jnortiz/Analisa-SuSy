package com.ic.analisaSusy.analysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author dmarinho
 *
 */
public class CcsmTest {

    private static final String SEPARATOR_TOKEN = "#";

    private static final String FILE_PATH_OUTPUT = "/bin/output";

    private static final String FILE_PATH_INPUT = "/bin/carregados.txt";

    private static Ccsm ccsm;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        // GIVEN a analise event
        ccsm = new Ccsm(Arrays.asList(FILE_PATH_INPUT),
                Arrays.asList(Metric.FUNC_C, Metric.CASE_C, Metric.BREAK_C, Metric.GOTO_C, Metric.CONTINUE_C, Metric.VAR_FILE_LOC_C, Metric.RETURN_POINT_C,
                        Metric.CMNT_DENS, Metric.CC, Metric.UNQ_FUNC_CALL, Metric.PARAM_PER_FUNC, Metric.NESTING_FUNC_C, Metric.HAL_VOC, Metric.HAL_LEN,
                        Metric.HAL_CALC_LEN, Metric.HAL_D));
    }

    @Test
    public void testParseCommand() {

        // WHEN parse command effect
        final String parseCommand = ccsm.parseCommand();

        // THEN parse command will be executed
        assertNotNull(parseCommand);
        assertEquals("ccsm --output-metrics=STMT_HIS_PARAM -exclude-std-headers -disable-file " + FILE_PATH_INPUT + "  --", parseCommand);
    }

    @Test
    public void testExecuteSpecificParseCommand() {

        // GIVEN a analysis event
        ccsm = new Ccsm(Arrays.asList(FILE_PATH_INPUT), Arrays.asList(Metric.PARAM_PER_FUNC));

        // WHEN parse command effect
        final String parseCommand = ccsm.parseCommand();

        // THEN parse command will be executed
        assertNotNull(parseCommand);
        assertEquals("ccsm --output-metrics=STMT_HIS_PARAM -exclude-std-headers -disable-file " + FILE_PATH_INPUT + "  --", parseCommand);
    }

    @Test
    public void testParseOutput() {

     //   ccsm.parseOutput(FILE_PATH_OUTPUT);
    }

    @Test
    public void testRunTool() {

        ccsm.runTool();
    }

    @Test
    public void testGetOutput() {

        // WHEN parse command effect
        final String output = ccsm.getOutput();

        // THEN parse command will be executed
        assertNotNull(output);
    }

    @Test
    public void testMetricsToString() {

        // GIVEN a metrics
        final List<Metric> metrics = Arrays.asList(Metric.FUNC_C, Metric.CASE_C, Metric.BREAK_C, Metric.GOTO_C, Metric.CONTINUE_C, Metric.VAR_FILE_LOC_C,
                Metric.RETURN_POINT_C, Metric.CMNT_DENS, Metric.CC, Metric.UNQ_FUNC_CALL, Metric.PARAM_PER_FUNC, Metric.NESTING_FUNC_C, Metric.HAL_VOC,
                Metric.HAL_LEN, Metric.HAL_CALC_LEN, Metric.HAL_D);

        // WHEN parse command effect
        final String output = ccsm.metricsToString(metrics, SEPARATOR_TOKEN);

        // THEN parse command will be executed
        assertNotNull(output);
    }

    @Test
    public void testFilesToString() {

        // GIVEN a files path list
        final List<String> files = Arrays.asList("/bin/input/algorithm_1.c", "/bin/input/algorithm_2.c", "/bin/input/algorithm_3.h");

        // WHEN parse command effect
        final String output = ccsm.filesToString(files, SEPARATOR_TOKEN);

        // THEN parse command will be executed
        assertNotNull(output);
    }

}
