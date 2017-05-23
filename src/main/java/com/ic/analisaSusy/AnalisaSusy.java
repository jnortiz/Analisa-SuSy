package com.ic.analisaSusy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Multimap;
import com.ic.analisaSusy.analysis.Analiso;
import com.ic.analisaSusy.analysis.AnalysisTool;
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
     * @param arguments
     *            [-f], [$(SRCDIR)/overview.c], [-cfg], [$(SRCDIR)/config.xml]
     */
    public static void main(final String[] arguments) {
        // Read command line arguments and generate data structure
        final CLInterface aCLI = new CLInterface(arguments);
        aCLI.parse();
        final List<String> filepaths = aCLI.getFilepaths();
        final String aConfigurationFile = aCLI.getConfigurationFile();

        // Parse configuration file and generate selected Metric for each Tool
        final Multimap<Tool, Metric> metricsPerTool = ParserTool.parseConfigFile(aConfigurationFile);

        // Execute the analysis tool for each Tool using only selected Metrics
        final Map<Metric, String> analysisOutput = new HashMap<>();
        AnalysisTool analysisTool = null;
        for (final Tool aTool : Tool.values()) {
            final List<Metric> selectedMetrics = (List<Metric>) metricsPerTool.get(aTool);
            if (aTool.equals(Tool.ANALIZO)) {
                analysisTool = new Analiso(filepaths, selectedMetrics);
                analysisTool.runTool();
                analysisOutput.putAll(analysisTool.getOutput());
            } else if (aTool.equals(Tool.TOOL2)) {
                analysisTool = new Analiso(filepaths, selectedMetrics);
                analysisTool.runTool();
                analysisOutput.putAll(analysisTool.getOutput());
            }
            // Add an IF block for each Tool that Analisa-Susy works with
        }

        // Parse each relation metric:value as a formated output
        final String anAnalisaOutput = ParserTool.parseAnalisaOutput(analysisOutput);

        // Output final result
        System.out.println(anAnalisaOutput);
    }

}
