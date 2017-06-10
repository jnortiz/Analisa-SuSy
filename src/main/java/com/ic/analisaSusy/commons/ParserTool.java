package com.ic.analisaSusy.commons;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.ic.analisaSusy.AnalisaSusy;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;
import com.sun.javafx.font.Metrics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Multimap<Tool, Metric> metricsPerTool = ArrayListMultimap.create();
        for (Metric aMetric : Metric.values()) {
            metricsPerTool.put(aMetric.getTool(), aMetric);
        }
        return metricsPerTool;
    }

    public static List<String> parseFilepaths(String aFile) throws FileNotFoundException, IOException {
        List<String> filepaths = new ArrayList<>();
        File file = new File(aFile);
        if (!file.exists()) {
            String[] splits = aFile.split(File.separator);
            AnalisaSusy.errors.put(ApplicationError.NF_FILE_ARG, splits[splits.length - 1]);
            throw new FileNotFoundException("Arquivo de entrada não encontrado");
        }
        BufferedReader aBufferedReader = new BufferedReader(new FileReader(aFile));
        String aFilepath;
        while ((aFilepath = aBufferedReader.readLine()) != null) {
            filepaths.add(aFilepath);
        }
        filepaths = ErrorChecking.fileExistenceCheck(filepaths);
        filepaths = ErrorChecking.fileExtensionCheck(filepaths);
        return filepaths;

    }

    public static String generateOutput(LinkedHashMap<String, LinkedHashMap<Metric, String>> structuredOutput) {
        StringBuilder aStringBuilder = new StringBuilder();
        for (String aFunction : structuredOutput.keySet()) {
            aStringBuilder.append("Função:" + aFunction + System.getProperty("line.separator"));
            HashMap<Metric, String> byFunctionOutput = structuredOutput.get(aFunction);
            for (Metric aMetric : byFunctionOutput.keySet()) {
                aStringBuilder.append("\t" + aMetric.getToolCode() + " = " + byFunctionOutput.get(aMetric) + System.getProperty("line.separator"));
            }
        }
        return aStringBuilder.toString().replace("Função:Global", "Global");
    }

    public static String parseErrors(Multimap<ApplicationError, String> errors) {
        StringBuilder aStringBuilder = new StringBuilder();
        List<String> identifiedErrors;
        String anErrorTemplate;
        for (ApplicationError anApplicationError : errors.keySet()) {
            identifiedErrors = (List<String>) errors.get(anApplicationError);
            for (String anErrorObject : identifiedErrors) {
                anErrorTemplate = anApplicationError.getErrorMessage();
                anErrorTemplate = String.format(anErrorTemplate, anErrorObject);
                aStringBuilder.append("\t");
                aStringBuilder.append(anErrorTemplate);
                aStringBuilder.append(System.getProperty("line.separator"));
            }
        }
        return aStringBuilder.toString();
    }
}
