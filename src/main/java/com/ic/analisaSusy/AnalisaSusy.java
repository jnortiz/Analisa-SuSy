package com.ic.analisaSusy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Multimap;
import com.ic.analisaSusy.analysis.AnalysisTool;
import com.ic.analisaSusy.analysis.Ccsm;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;
import com.ic.analisaSusy.commons.CLInterface;
import com.ic.analisaSusy.commons.ParserTool;

/**
 *
 * @author Marcelo C. Araújo
 */
public class AnalisaSusy {

    /**
     * @param arguments [-f], [$(SRCDIR)/overview.c], [-cfg],
     * [$(SRCDIR)/config.xml]
     */
    public static void main(final String[] arguments) {
        // Read command line arguments and generate data structure
        final CLInterface aCLI = new CLInterface(arguments);
        aCLI.parse();
        final List<String> filepaths = aCLI.getFilepaths();
        final Multimap<Tool, Metric> metricsPerTool = aCLI.getMetricsPerTool();
       
        // Execute the analysis tool for each Tool using only selected Metrics
        final Map<Metric, String> analysisOutput = new HashMap<>();
        AnalysisTool analysisTool = null;
        String anOutput = null;
        for (final Tool aTool : Tool.values()) {
            final List<Metric> selectedMetrics = (List<Metric>) metricsPerTool.get(aTool);
            if (aTool.equals(Tool.CCSM)) {
                analysisTool = new Ccsm(filepaths, selectedMetrics);
                analysisTool.runTool();
                anOutput = analysisTool.getOutput();
                //analysisOutput.putAll(analysisTool.getOutput());
            }
            // Add an IF block for each Tool that Analisa-Susy works with
        }

        // Parse each relation metric:value as a formated output
        final String anAnalisaOutput = ParserTool.parseAnalisaOutput(analysisOutput);

        // Output final result
        System.out.println(anOutput);
    }

}
