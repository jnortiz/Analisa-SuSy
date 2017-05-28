package com.ic.analisaSusy.commons;

import com.google.common.collect.Multimap;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author Marcelo C. Araújo
 */
public class CLInterface {

    private String[] arguments;
    private Options options;
    private List<String> filepaths;
    private Multimap<Tool, Metric> metricsPerTool;

    public CLInterface(String[] arguments) {
        this.arguments = arguments;
        this.options = new Options();
        this.filepaths = new ArrayList<>();
        this.createOptions();
    }

    private void createOptions() {
        this.options.addOption("f", "file", true, "Text file with code filepaths to analise.");
        this.options.addOption("cfg", "cfgFile", true, "Configuration file");
        this.options.addOption("h", "help", false, "Show help");
    }

    public void parse() {
        CommandLineParser aParser = new DefaultParser();
        CommandLine aCommandLine = null;
        String aConfigurationFile = null;
        try {
            aCommandLine = aParser.parse(this.options, this.arguments);
            if (aCommandLine.hasOption("h")) {
                help();
            } else if (aCommandLine.hasOption("f")) {
                this.filepaths = ParserTool.parseFilepaths(aCommandLine.getOptionValue("f"));
                if (aCommandLine.hasOption("cfg")) {
                    aConfigurationFile = aCommandLine.getOptionValue("cfg");
                }
                this.metricsPerTool = ParserTool.parseConfigFile(aConfigurationFile);
            } else {
                System.err.println("Missing file (-f) argument");
            }

        } catch (ParseException anException) {
            Logger.getLogger(CLInterface.class.getName()).log(Level.SEVERE, null, anException);
        }
    }

    private void help() {
    }

    public List<String> getFilepaths() {
        return this.filepaths;
    }

    public Multimap<Tool, Metric> getMetricsPerTool() {
        return this.metricsPerTool;
    }

}
