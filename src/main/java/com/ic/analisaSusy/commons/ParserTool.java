package com.ic.analisaSusy.commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.ic.analisaSusy.AnalisaSusy;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;

/**
 *
 * @author Marcelo C. Araújo
 */
public class ParserTool {

    public static Multimap<Tool, Metric> parseConfigFile(final String aConfigurationFile) {
        if (aConfigurationFile == null) {
            return ParserTool.generateDefaultMetrics();
        }
        return null;
    }

    private static Multimap<Tool, Metric> generateDefaultMetrics() {
        final Multimap<Tool, Metric> metricsPerTool = ArrayListMultimap.create();
        for (final Metric aMetric : Metric.values()) {
            metricsPerTool.put(aMetric.getTool(), aMetric);
        }
        return metricsPerTool;
    }

    public static List<String> parseFilepaths(final String aFile) throws FileNotFoundException, IOException {
        List<String> filepaths = new ArrayList<>();
        final File file = new File(aFile);
        if (!file.exists()) {
            final String[] splits = aFile.split(File.separator);
            AnalisaSusy.errors.put(ApplicationError.NF_FILE_ARG, splits[splits.length - 1]);
            throw new FileNotFoundException("Arquivo de entrada não encontrado");
        }
        final BufferedReader aBufferedReader = new BufferedReader(new FileReader(aFile));
        String aFilepath="";
        while ((aFilepath = aBufferedReader.readLine()) != null) {
            if(aFilepath.trim().isEmpty())
                continue;
            filepaths.add(aFilepath.trim());
        }
        filepaths = ErrorChecking.fileExistenceCheck(filepaths);
        filepaths = ErrorChecking.fileExtensionCheck(filepaths);
        return filepaths;

    }

    private static List<Metric> generateMetricFilter(){
        List<Metric> aFilterList = new ArrayList<>();
        aFilterList.add(Metric.BREAK_C);
        aFilterList.add(Metric.CASE_C);
        aFilterList.add(Metric.GOTO_C);
        aFilterList.add(Metric.CONTINUE_C);
        return aFilterList;
    }
    
    public static String generateOutput(final LinkedHashMap<String, LinkedHashMap<Metric, String>> structuredOutput) {
        final StringBuilder aStringBuilder = new StringBuilder();
        String aFormatedDescription = "";
        List<Metric> aFilterList = ParserTool.generateMetricFilter();
        for (final String aFunction : structuredOutput.keySet()) {
            aStringBuilder.append("Função " + aFunction + System.getProperty("line.separator"));
            final HashMap<Metric, String> byFunctionOutput = structuredOutput.get(aFunction);
            for (final Metric aMetric : byFunctionOutput.keySet()) {
                if(!aFunction.equalsIgnoreCase("global") && aFilterList.contains(aMetric))
                    continue;
                aFormatedDescription = aMetric.getMetricDescription();
                aFormatedDescription = String.format(aFormatedDescription, byFunctionOutput.get(aMetric).replace(".",","));
                aStringBuilder.append("\t");
                aStringBuilder.append(aFormatedDescription);
                aStringBuilder.append(System.getProperty("line.separator"));
            }
        }
        aStringBuilder.append(System.getProperty("line.separator"));
        aStringBuilder.append("Para mais informações, acesse a <a href=\"https://github.com/jnortiz/Analisa-SuSy/wiki/Métricas\" target=\"_blank\" >página</a> que descreve as métricas acima.");
        aStringBuilder.append(System.getProperty("line.separator"));;
        return aStringBuilder.toString().replace("Função Global", "Métricas globais");
    }

    public static String parseErrors(final Multimap<ApplicationError, String> errors) {
        final StringBuilder aStringBuilder = new StringBuilder();
        List<String> identifiedErrors;
        String anErrorTemplate;
        for (final ApplicationError anApplicationError : errors.keySet()) {
            identifiedErrors = (List<String>) errors.get(anApplicationError);
            for (final String anErrorObject : identifiedErrors) {
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
