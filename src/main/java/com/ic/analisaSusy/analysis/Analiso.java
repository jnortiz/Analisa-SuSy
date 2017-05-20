package com.ic.analisaSusy.analysis;

import java.util.List;

/**
 *
 * @author Marcelo C. Araújo
 */
public class Analiso extends AnalysisTool {

    public Analiso(List<String> filepaths, List<Metric> metrics) {
        super(filepaths, metrics);
    }

    @Override
    protected String parseCommand() {
        String aCommand = null;
        return aCommand;
    }

    @Override
    protected void parseOutput(String anOutput) {
    }
}
