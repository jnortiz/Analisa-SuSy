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

public class InputTestStep {
    private String[] arguments;
    private CLInterface aCLI;
    private final String FILENAME = CucumberConstant.PATH + "carregados.txt";

    //Scenario The carregados.txt file does not exist
    @Given("^A name of a file that does not exist$")
    public void optionFAndFileNameAsInput() {
        arguments = new String[2];
        arguments[0] = "-f";
        arguments[1] = "NaoExiste.txt";
        Assert.assertTrue(arguments.length == 2);
    }

    @When("^Analisa-SuSy is called$")
    public void analisaSuSyIsCalled() {
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
    
    @Then("^Throw a message saying the file does not exist$")
    public void openFileGivenAsInput() {
        Assert.assertTrue(AnalisaSusy.errors.containsKey(ApplicationError.NF_FILE_ARG));
    }
    
    //Scenario The files containing source codes may not have the C extensions
    @Given("^The carregados.txt file does exist$")
    public void theFileExists() {
        arguments = new String[2];
        arguments[0] = "-f";
        arguments[1] = FILENAME;
        Assert.assertTrue(arguments.length == 2);
    }
  
    @When("^Analisa-SuSy is called successfully$")
    public void analisaSuSyIsCalledSuccessfully() {
        try{
            ErrorChecking.verifyCCSM();
            aCLI = new CLInterface(arguments);
            aCLI.parse();
            final List<String> filepaths = aCLI.getFilepaths();
            final Multimap<Tool, Metric> metricsPerTool = aCLI.getMetricsPerTool();

            String anOutput = runAnalysis(metricsPerTool, filepaths);
        }catch(Exception e) {
            Assert.assertTrue(false);
            return;
        }
        Assert.assertTrue(true);
    }
    
    @Then("^Filter those file names which extension is .c and .h$")
    public void filterThoseFileNamesExtensionCOrH() {
        Assert.assertTrue(AnalisaSusy.errors.containsKey(ApplicationError.INVALID_EXT));
    }
}