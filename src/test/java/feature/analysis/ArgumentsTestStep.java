package feature.analysis;

import com.google.common.collect.Multimap;
import com.ic.analisaSusy.AnalisaSusy;
import static com.ic.analisaSusy.AnalisaSusy.runAnalysis;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;
import com.ic.analisaSusy.commons.ApplicationError;
import org.apache.commons.cli.ParseException;
import org.junit.Assert;

import com.ic.analisaSusy.commons.CLInterface;
import com.ic.analisaSusy.commons.ErrorChecking;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;

public class ArgumentsTestStep {

    private String[] arguments;
    private CLInterface aCLI;
    private final String FILENAME = CucumberConstant.PATH + "carregados.txt";

    //All arguments are correct
    @Given("^The -f and a filename as input for Analisa-SuSy$")
    public void fAndFileNameAsInput() {
        arguments = new String[2];
        arguments[0] = "-f";
        arguments[1] = FILENAME;
        Assert.assertTrue(arguments.length == 2);
    }

    @When("^SuSy calls Analisa-SuSy$")
    public void suSyCallsAnalisaSuSy() {
        try{
            ErrorChecking.verifyCCSM();
            aCLI = new CLInterface(arguments);
            aCLI.parse();
            final List<String> filepaths = aCLI.getFilepaths();
            final Multimap<Tool, Metric> metricsPerTool = aCLI.getMetricsPerTool();

            String anOutput = runAnalysis(metricsPerTool, filepaths);
        }catch(Exception e) {
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }

    @Then("^Open the file given as input$")
    public void openFileGivenAsInput() throws ParseException {
        Assert.assertFalse(AnalisaSusy.errors.containsKey(ApplicationError.NF_INPUT));
    }

    //Without any argument
    @Given("^0 arguments as input for Analisa-SuSy$")
    public void zeroArgumentsAsInput() {
        arguments = new String[0];
        Assert.assertTrue(arguments.length == 0);
    }

    @When("^SuSy calls Analisa-SuSy using 0 arguments$")
    public void suSyCallsAnalisaSuSyZeroArguments() {
        try{
            ErrorChecking.verifyCCSM();
            aCLI = new CLInterface(arguments);
            aCLI.parse();
            final List<String> filepaths = aCLI.getFilepaths();
            final Multimap<Tool, Metric> metricsPerTool = aCLI.getMetricsPerTool();

            String anOutput = runAnalysis(metricsPerTool, filepaths);
        }catch(Exception e) {
            Assert.assertTrue(true);
            return;
        }
        Assert.assertTrue(false);
    }

    @Then("^Throw a message saying the arguments are missing$")
    public void analisaSuSyWillReturnErrorMessage() {
        Assert.assertTrue(AnalisaSusy.errors.containsKey(ApplicationError.NF_FILE_ARG));
    }

    //Without file path
    @Given("^The list of parameters: -f$")
    public void fOptionAsArgument() {
        arguments = new String[1];
        arguments[0] = "-f";
        Assert.assertTrue(arguments.length == 1);
    }

    @When("^SuSy calls Analisa-SuSy using just the -f argument$")
    public void suSyCallsAnalisaSuSyJustFArgument() {
        try{
            ErrorChecking.verifyCCSM();
            aCLI = new CLInterface(arguments);
            aCLI.parse();
            final List<String> filepaths = aCLI.getFilepaths();
            final Multimap<Tool, Metric> metricsPerTool = aCLI.getMetricsPerTool();

            String anOutput = runAnalysis(metricsPerTool, filepaths);
        }catch(Exception e) {
            Assert.assertTrue(true);
            return;
        }
        Assert.assertTrue(false);
    }

    @Then("^Analisa-SuSy will return an error message asking for a filename$")
    public void analisaSuSyWillReturnMessageForFilename() {
        Assert.assertTrue(AnalisaSusy.errors.containsKey(ApplicationError.NF_FILE_ARG));
    }

    //Without -f option
    @Given("^The list of parameters: path$")
    public void pathAsArgument() {
        arguments = new String[1];
        arguments[0] = FILENAME;
        Assert.assertTrue(arguments.length == 1);
    }

    @When("^SuSy calls Analisa-SuSy passing only a filename as argument$")
    public void suSyCallsAnalisaSuSyJustPathArgument() {
        try{
            ErrorChecking.verifyCCSM();
            aCLI = new CLInterface(arguments);
            aCLI.parse();
            final List<String> filepaths = aCLI.getFilepaths();
            final Multimap<Tool, Metric> metricsPerTool = aCLI.getMetricsPerTool();

            String anOutput = runAnalysis(metricsPerTool, filepaths);
        }catch(Exception e) {
            Assert.assertTrue(true);
            return;
        }
        Assert.assertTrue(false);
    }

    @Then("^Throw a message saying the -f option is missing$")
    public void messageFOptionIsmissing() {
        Assert.assertTrue(AnalisaSusy.errors.containsKey(ApplicationError.NF_FILE_ARG));
    }
}