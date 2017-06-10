package feature.commons;

import java.util.List;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;

import com.google.common.collect.Multimap;
import com.ic.analisaSusy.AnalisaSusy;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;
import com.ic.analisaSusy.commons.CLInterface;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchingForAMetric {

    private String[] arguments;
    private CLInterface aCLI;
    private List<String> filepaths;
    private String anOutput;

    @Given("^The analysis result$")
    public void correctArgumentsAsInput() throws ParseException {
        arguments = new String[2];
        arguments[0] = "-f";
        arguments[1] = "src/test/resources/Files/empty.txt";

        aCLI = new CLInterface(arguments);
        aCLI.parse();
        filepaths = aCLI.getFilepaths();
        final Multimap<Tool, Metric> metricsPerTool = aCLI.getMetricsPerTool();
        anOutput = AnalisaSusy.runAnalysis(metricsPerTool, filepaths);
        Assert.assertNotNull(anOutput);
    }

    @When("^The metric matches$")
    public void suSyCallsAnalisaSuSy() {
        Assert.assertTrue(anOutput.contains("")); // TODO verificar algo que a
                                                  // saida vai conter e colocar
                                                  // aqui
    }

    @Then("^It is correct$")
    public void analisaSuSyWontReturnErrorMessage() {
        Assert.assertTrue(true);
    }

}