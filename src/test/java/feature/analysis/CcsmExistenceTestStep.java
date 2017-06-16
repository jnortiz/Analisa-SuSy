package feature.analysis;

import com.google.common.collect.Multimap;
import com.ic.analisaSusy.AnalisaSusy;
import static com.ic.analisaSusy.AnalisaSusy.runAnalysis;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;
import com.ic.analisaSusy.commons.ApplicationError;
import com.ic.analisaSusy.commons.CLInterface;
import com.ic.analisaSusy.commons.ErrorChecking;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import org.junit.Assert;

public class CcsmExistenceTestStep {
    private String[] arguments;
    private CLInterface aCLI;
    private final String FILENAME = CucumberConstant.PATH + "carregados.txt";

    //Scenario The CCSM component is not available in the path
    @Given("^Analisa-SuSy is called by SuSy with the correct parameters$")
    public void calledWithCorrectParameters() {
        arguments = new String[2];
        arguments[0] = "-f";
        arguments[1] = FILENAME;
        Assert.assertTrue(arguments.length == 2);
    }

    @When("^Analisa-SuSy checks if CCSM is running$")
    public void analisaSuSyChecksCCSM() {
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
    @Then("^Throw a message saying the path to CCSM must be added to the PATH variable$")
    public void analisaSuSyIsCalled() {
        Assert.assertTrue(AnalisaSusy.errors.containsKey(ApplicationError.NF_CCSM));
    }
    
}
