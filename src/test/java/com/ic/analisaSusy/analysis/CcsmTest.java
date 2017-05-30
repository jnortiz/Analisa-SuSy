package com.ic.analisaSusy.analysis;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author dmarinho
 *
 */
public class CcsmTest {

    private static Ccsm ccsm;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        // GIVEN a analise event
        ccsm = new Ccsm(Arrays.asList("/bin/input/list_of_algorithms"),
                Arrays.asList(Metric.FUNC_C, Metric.CASE_C, Metric.BREAK_C, Metric.GOTO_C, Metric.CONTINUE_C, Metric.VAR_FILE_LOC_C, Metric.RETURN_POINT_C,
                        Metric.CMNT_DENS, Metric.CC, Metric.UNQ_FUNC_CALL, Metric.PARAM_PER_FUNC, Metric.NESTING_FUNC_C, Metric.HAL_VOC, Metric.HAL_LEN,
                        Metric.HAL_CALC_LEN, Metric.HAL_D));
    }

    @Test
    public void testParseCommand() {

        // WHEN parse command efect
        final String parseCommand = ccsm.parseCommand();

        // THEN parse command will be executed
        assertNotNull(parseCommand);
    }

    @Test
    public void testParseOutput() {

        // WHEN parse command efect
        ccsm.parseOutput("/bin/output");

        // THEN parse command will be executed
    }

    @Ignore
    @Test
    public void testRunTool() {

        // WHEN parse command efect
        ccsm.runTool();

        // THEN parse command will be executed
    }

    @Test
    public void testGetOutput() {

        // WHEN parse command efect
        final String output = ccsm.getOutput();

        // THEN parse command will be executed
        assertNotNull(output);
    }

    @Test
    public void testMetricsToString() {

        final List<Metric> metrics = Arrays.asList(Metric.FUNC_C, Metric.CASE_C, Metric.BREAK_C, Metric.GOTO_C, Metric.CONTINUE_C, Metric.VAR_FILE_LOC_C,
                Metric.RETURN_POINT_C, Metric.CMNT_DENS, Metric.CC, Metric.UNQ_FUNC_CALL, Metric.PARAM_PER_FUNC, Metric.NESTING_FUNC_C, Metric.HAL_VOC,
                Metric.HAL_LEN, Metric.HAL_CALC_LEN, Metric.HAL_D);

        // WHEN parse command efect
        final String output = ccsm.metricsToString(metrics, "#");

        // THEN parse command will be executed
        assertNotNull(output);
    }

    @Test
    public void testFilesToString() {

        final List<String> files = Arrays.asList("/bin/input/algorithm_1.c", "/bin/input/algorithm_2.c", "/bin/input/algorithm_3.h");

        // WHEN parse command efect
        final String output = ccsm.filesToString(files, "#");

        // THEN parse command will be executed
        assertNotNull(output);
    }

}
