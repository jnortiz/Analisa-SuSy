package com.ic.analisaSusy.commons;

import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Multimap;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;

/**
 * @author dmarinho
 *
 */
public class ParserToolTest {

    @Test
    public void testParseConfigFile() {

        final String aConfigurationFile = "/bash/conf.xml";
        final Multimap<Tool, Metric> parseConfigFile = ParserTool.parseConfigFile(aConfigurationFile);
        assertNull(parseConfigFile);
    }

    @Test
    public void testParseAnalisaOutputWithMetricNumLines() {

        final String output = ParserTool.parseAnalisaOutput(teste(Metric.CASE_C, "100"));
        assertNull(output);
    }

    @Test
    public void testParseAnalisaOutputWithMetricA() {

        final String output = ParserTool.parseAnalisaOutput(teste(Metric.BREAK_C, "370"));
        assertNull(output);
    }

    private Map<Metric, String> teste(final Metric metric, final String value) {

        final Map<Metric, String> analysisOutput = new HashMap<>();
        analysisOutput.put(metric, value);

        return analysisOutput;
    }
}
