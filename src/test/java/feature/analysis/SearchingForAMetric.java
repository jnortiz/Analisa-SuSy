package feature.analysis;

import java.util.List;

import org.junit.Assert;

import com.google.common.collect.Multimap;
import static com.ic.analisaSusy.AnalisaSusy.runAnalysis;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;
import com.ic.analisaSusy.commons.CLInterface;
import com.ic.analisaSusy.commons.ErrorChecking;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchingForAMetric {

    private String[] arguments;
    private String anOutput;

    private final String FILENAME = CucumberConstant.PATH + "carregados.txt";

    @Given("^The correct arguments and carregados.txt$")
    public void correctArgumentsAndFile() {
        arguments = new String[2];
        arguments[0] = "-f";
        arguments[1] = FILENAME;
        Assert.assertEquals(2, arguments.length);
    }

    @When("^Analisa-SuSy analyze all files$")
    public void analisaSuSyAnalyzeAllFiles() {
        try{
            ErrorChecking.verifyCCSM();
            CLInterface aCLI = new CLInterface(arguments);
            aCLI.parse();
            final List<String> filepaths = aCLI.getFilepaths();
            final Multimap<Tool, Metric> metricsPerTool = aCLI.getMetricsPerTool();

            anOutput = runAnalysis(metricsPerTool, filepaths);
        }catch(Exception e) {
            Assert.assertTrue(false);
        }
        Assert.assertTrue(!anOutput.equals(""));
    }

    @Then("^Output contains (.*)$")
    public void outPutContains(String metric) {
        Assert.assertTrue(anOutput.contains(metric));
    }
}