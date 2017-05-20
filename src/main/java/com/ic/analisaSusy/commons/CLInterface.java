package com.ic.analisaSusy.commons;

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
    private String configurationFile;

    public CLInterface(String[] arguments) {
        this.arguments = arguments;
        this.options = new Options();
        this.filepaths = new ArrayList<>();
        this.createOptions();
    }

    private void createOptions() {
        Option anOption = new Option("f", "file", true, "Path(s) of file(s) to analyse.");
        anOption.setArgs(Option.UNLIMITED_VALUES);
        this.options.addOption(anOption);
        this.options.addOption("cfg", "cfgFile", true, "Configuration file");
        this.options.addOption("h", "help", false, "Show help");
    }

    public void parse() {
        CommandLineParser aParser = new DefaultParser();
        CommandLine aCommandLine = null;
        try {
            aCommandLine = aParser.parse(this.options, this.arguments);
            if (aCommandLine.hasOption("h")) {
                help();
            } else if (aCommandLine.hasOption("f") && aCommandLine.hasOption("cfg")) {
                this.filepaths = Arrays.asList(aCommandLine.getOptionValues("f"));
                this.configurationFile = aCommandLine.getOptionValue("cfg");
            } else {
                System.err.println("Missing CLI arguments");
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

    public String getConfigurationFile() {
        return this.configurationFile;
    }

}
