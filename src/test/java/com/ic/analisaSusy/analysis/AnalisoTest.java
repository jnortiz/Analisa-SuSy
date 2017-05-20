package com.ic.analisaSusy.analysis;

import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author dmarinho
 *
 */
public class AnalisoTest {

    @Test
    public void testParseCommand() {

        // GIVEN a analise event
        final Analiso analiso = new Analiso(Arrays.asList("/bin/input"), Arrays.asList(Metric.A, Metric.NUM_LINES));

        // WHEN parse command efect
        final String parseCommand = analiso.parseCommand();

        // THEN parse command will be executed
        assertNull(parseCommand);
    }

    @Test
    public void testParseOutput() {

        // GIVEN a analise event
        final Analiso analiso = new Analiso(Arrays.asList("/bin/input"), Arrays.asList(Metric.A, Metric.NUM_LINES));

        // WHEN parse command efect
        analiso.parseOutput("/bin/output");

        // THEN parse command will be executed
    }

    @Ignore
    @Test
    public void testRunTool() {

        // GIVEN a analise event
        final Analiso analiso = new Analiso(Arrays.asList("/bin/input"), Arrays.asList(Metric.A, Metric.NUM_LINES));

        // WHEN parse command efect
        analiso.runTool();

        // THEN parse command will be executed
    }

    @Test
    public void testGetOutput() {

        // GIVEN a analise event
        final Analiso analiso = new Analiso(Arrays.asList("/bin/input"), Arrays.asList(Metric.A, Metric.NUM_LINES));

        // WHEN parse command efect
        final Map<Metric, String> output = analiso.getOutput();

        // THEN parse command will be executed
        assertNull(output.get(0));
    }

}
