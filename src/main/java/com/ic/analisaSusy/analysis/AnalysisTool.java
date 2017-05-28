package com.ic.analisaSusy.analysis;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ic.analisaSusy.commons.ShellInterface;

/**
 *
 * @author Marcelo C. Araújo
 */
public abstract class AnalysisTool {

    protected List<String> filepaths;
    protected List<Metric> metrics;
    //protected Map<Metric, String> output;
    protected String output;

    public AnalysisTool(final List<String> filepaths, final List<Metric> metrics) {
        this.filepaths = filepaths;
        this.metrics = metrics;
        //this.output = new HashMap<>();
        String output = "";
    }

    public void runTool() {
        final String aCommand = this.parseCommand();
        final ShellInterface aShell = new ShellInterface();
        try {
            aShell.executeCommand(aCommand);
        } catch (final IOException ex) {
            Logger.getLogger(AnalysisTool.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.parseOutput(aShell.getOutput());
    }

    public String getOutput() {
        return this.output;
    }

    protected String metricsToString(List<Metric> metrics, String aSeparatorToken) {
        String aStringResult = "";
        for (Metric aMetric : metrics) {
            aStringResult += aMetric.getToolCode() + aSeparatorToken;
        }
        return aStringResult.substring(0, aStringResult.length()-1);
    }

    protected String filesToString(List<String> files, String aSeparatorToken) {
        String aStringResult = "";
        for (String aFilepath : files) {
            aStringResult += aFilepath + aSeparatorToken;
        }
        return aStringResult;
    }

    protected abstract void parseOutput(String anOutput);

    protected abstract String parseCommand();

}
