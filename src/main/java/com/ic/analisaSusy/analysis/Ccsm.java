package com.ic.analisaSusy.analysis;

import java.util.List;

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
        String aCommand = "ccsm --output-metrics=%s -exclude-std-headers -disable-file %s --";
        aCommand = String.format(aCommand, this.metricsToString(metrics, ","),this.filesToString(filepaths, " "));
        return aCommand;
    }

    @Override
    protected void parseOutput(String anOutput) {
        this.output = anOutput;
    }
}
