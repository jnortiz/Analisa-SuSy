package feature.commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

public class ProcessingAnalysisOutputStep {

    private String[] arguments;
    private CLInterface aCLI;
    private List<String> filepaths;
    private String anOutput;

    @Given("^The expected file is empty$")
    public void correctArgumentsAsInput() {
        arguments = new String[2];
        arguments[0] = "-f";
        arguments[1] = "src/test/resources/Files/empty.txt";
        final File file = new File(arguments[1]);

        try {
            final BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                final StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                Assert.assertTrue(sb.toString().equals(""));
            } finally {
                br.close();
            }
        } catch (final FileNotFoundException e) {
            Assert.assertTrue(false);
        } catch (final IOException e) {
            Assert.assertTrue(false);
        }
    }

    @When("^The CCSM module returns from calling$")
    public void suSyCallsAnalisaSuSy() throws ParseException {
        aCLI = new CLInterface(arguments);
        aCLI.parse();
        filepaths = aCLI.getFilepaths();
        final Multimap<Tool, Metric> metricsPerTool = aCLI.getMetricsPerTool();
        anOutput = AnalisaSusy.runAnalysis(metricsPerTool, filepaths);
        Assert.assertNotNull(anOutput);
    }

    @Then("^The output should be empty as well$")
    public void analisaSuSyWontReturnErrorMessage() {
        Assert.assertEquals("", anOutput);
    }

}