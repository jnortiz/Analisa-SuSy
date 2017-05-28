package com.ic.analisaSusy.commons;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marcelo C. Araújo
 */
public class ParserTool {

    public static Multimap<Tool, Metric> parseConfigFile(String aConfigurationFile) {
        if (aConfigurationFile == null) {
            return ParserTool.generateDefaultMetrics();
        }
        return null;
    }

    private static Multimap<Tool, Metric> generateDefaultMetrics() {
        Multimap<Tool,Metric> metricsPerTool = ArrayListMultimap.create();
        for(Metric aMetric: Metric.values())
            metricsPerTool.put(aMetric.getTool(), aMetric);
        return metricsPerTool;
    }

    public static List<String> parseFilepaths(String aFile) {
        List<String> filepaths = new ArrayList<>();
        try (BufferedReader aBufferedReader = new BufferedReader(new FileReader(aFile))) {
            String aFilepath;
            while ((aFilepath = aBufferedReader.readLine()) != null) {
                filepaths.add(aFilepath);
            }
        } catch (IOException ex) {
            System.err.println("Error parsing filepaths");
        } finally {
            return filepaths;
        }
    }

    public static String parseAnalisaOutput(Map<Metric, String> analysisOutput) {
        return null;
    }
   

}
