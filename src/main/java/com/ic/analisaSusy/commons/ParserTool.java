package com.ic.analisaSusy.commons;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.ic.analisaSusy.AnalisaSusy;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.transform.Affine;

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

    public static String parseAnalisaOutput(Map<Metric, String> analysisOutput) {
        return null;
    }
}
