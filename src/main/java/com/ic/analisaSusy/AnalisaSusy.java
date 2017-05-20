package com.ic.analisaSusy;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.ic.analisaSusy.analysis.Analiso;
import com.ic.analisaSusy.analysis.AnalysisTool;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;
import com.ic.analisaSusy.commons.CLInterface;
import com.ic.analisaSusy.commons.ParserTool;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marcelo C. Araújo
 */
public class AnalisaSusy {

    public static void main(String[] arguments) {
        //Read command line arguments and generate data structure 
        CLInterface aCLI = new CLInterface(arguments);
        aCLI.parse();
        List<String> filepaths = aCLI.getFilepaths();
        String aConfigurationFile = aCLI.getConfigurationFile();

        //Parse configuration file and generate selected Metric for each Tool        
        Multimap<Tool, Metric> metricsPerTool = ParserTool.parseConfigFile(aConfigurationFile);

        //Execute the analysis tool for each Tool using only selected Metrics 
        Map<Metric, String> analysisOutput = new HashMap<>();
        AnalysisTool analysisTool = null;
        for (Tool aTool : Tool.values()) {
            List<Metric> selectedMetrics = (List<Metric>) metricsPerTool.get(aTool);
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

        //Parse each relation metric:value as a formated output
        String anAnalisaOutput = ParserTool.parseAnalisaOutput(analysisOutput);

        //Output final result
        System.out.println(anAnalisaOutput);
    }

}
