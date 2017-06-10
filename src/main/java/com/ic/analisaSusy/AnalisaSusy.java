package com.ic.analisaSusy;

import com.google.common.collect.ArrayListMultimap;
import java.util.List;
import com.google.common.collect.Multimap;
import com.ic.analisaSusy.analysis.AnalysisTool;
import com.ic.analisaSusy.analysis.Ccsm;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;
import com.ic.analisaSusy.commons.ApplicationError;
import com.ic.analisaSusy.commons.CLInterface;
import com.ic.analisaSusy.commons.ErrorChecking;
import com.ic.analisaSusy.commons.ParserTool;

/**
 *
 * @author Marcelo C. Araújo
 */
public class AnalisaSusy {

    public static Multimap<ApplicationError, String> errors = ArrayListMultimap.create();

    /**
     * @param arguments [-f], [$(SRCDIR)/overview.c], [-cfg],
     * [$(SRCDIR)/config.xml]
     */
    public static void main(final String[] arguments) {
        String anOutput = "";
        String anErrorOutput = "";
        try {
            // Read command line arguments and generate data structure
            ErrorChecking.verifyCCSM();
            final CLInterface aCLI = new CLInterface(arguments);
            aCLI.parse();
            final List<String> filepaths = aCLI.getFilepaths();
            final Multimap<Tool, Metric> metricsPerTool = aCLI.getMetricsPerTool();

            anOutput = runAnalysis(metricsPerTool, filepaths);
            // Parse each relation metric:value as a formated output
            //final String anAnalisaOutput = ParserTool.parseAnalisaOutput(analysisOutput);

        } catch (Exception ex) {
            anErrorOutput = "Erros foram encontrados:" + System.getProperty("line.separator");
        } finally {
            if (anErrorOutput.isEmpty() && !errors.isEmpty()) {
                anErrorOutput = "Aviso(s):" + System.getProperty("line.separator");
            }
            if (!errors.isEmpty()) {
                anErrorOutput += ParserTool.parseErrors(AnalisaSusy.errors);
            }
            System.out.println(anErrorOutput);
            System.out.println(anOutput);
        }
    }

    public static String runAnalysis(Multimap<Tool, Metric> metricsPerTool, List<String> filepaths) {
        // Execute the analysis tool for each Tool using only selected Metrics
        //final Map<Metric, String> analysisOutput = new HashMap<>();
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
        return anOutput;
    }
}
