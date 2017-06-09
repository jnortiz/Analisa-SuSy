package com.ic.analisaSusy.commons;

import com.google.common.collect.Multimap;
import com.ic.analisaSusy.AnalisaSusy;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public void parse() throws ParseException {
        CommandLineParser aParser = new DefaultParser();
        CommandLine aCommandLine = null;
        String aConfigurationFile = null;
        aCommandLine = aParser.parse(this.options, this.arguments);
        if (aCommandLine.hasOption("f")) {
            try {
                this.filepaths = ParserTool.parseFilepaths(aCommandLine.getOptionValue("f"));
                if (aCommandLine.hasOption("cfg")) {
                    aConfigurationFile = aCommandLine.getOptionValue("cfg");
                }
                this.metricsPerTool = ParserTool.parseConfigFile(aConfigurationFile);
            } catch (IOException anIOException) {
                throw new ParseException("Erro na leitura do arquivo de entrada");
            }
        } else {
            AnalisaSusy.errors.put(ApplicationError.NF_FILE_ARG, "");
            throw new ParseException("O paramêtro obrigatório -f não foi encontrado");
        }
    }

    public List<String> getFilepaths() {
        return this.filepaths;
    }

    public Multimap<Tool, Metric> getMetricsPerTool() {
        return this.metricsPerTool;
    }

}
