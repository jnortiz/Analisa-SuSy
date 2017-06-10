package com.ic.analisaSusy.analysis;

import com.ic.analisaSusy.commons.ParserTool;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo C. Araújo
 */
public class Ccsm extends AnalysisTool {

    public Ccsm(List<String> filepaths, List<Metric> metrics) {
        super(filepaths, metrics);
    }

    @Override
    protected String parseCommand() {
        String aCommand = "ccsm --output-metrics=%s -exclude-std-headers -disable-file -output-short-names %s --";
        aCommand = String.format(aCommand, this.metricsToString(metrics, ","), this.filesToString(filepaths, " "));
        return aCommand;
    }

    @Override
    public LinkedHashMap<String, LinkedHashMap<Metric, String>> parseOutput() {
        LinkedHashMap<String, LinkedHashMap<Metric, String>> structuredOutput = new LinkedHashMap<>();
        try {
            BufferedReader aBufferedReader = new BufferedReader(new StringReader(this.output));
            String aLine = null;
            String aFunction = "";
            LinkedHashMap<Metric, String> perFunction = new LinkedHashMap<>();
            Metric aMetric;
            while ((aLine = aBufferedReader.readLine()) != null) {
                String[] tokens = aLine.split(":");
                if (tokens[0].equalsIgnoreCase("global")) {
                    aFunction = tokens[0];
                } else if (tokens[0].trim().equalsIgnoreCase("function")) {
                    structuredOutput.put(aFunction, perFunction);
                    perFunction = new LinkedHashMap<>();
                    aFunction = tokens[1];
                } else {
                    aMetric = Metric.getEnum(tokens[0].trim());
                    perFunction.put(aMetric, tokens[1]);
                }
            }
            structuredOutput.put(aFunction, perFunction);
        } catch (IOException ex) {
            Logger.getLogger(ParserTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return structuredOutput;
    }

}
