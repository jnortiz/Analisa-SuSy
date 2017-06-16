package feature.analysis;

import java.io.File;
import java.util.List;

import org.junit.Assert;

import com.google.common.collect.Multimap;
import com.ic.analisaSusy.AnalisaSusy;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;
import com.ic.analisaSusy.commons.CLInterface;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class AnalisaSusyStep {
	
    private Multimap<Tool, Metric> metricsPerTool;
    private String[] arguments;
    private CLInterface aCLI;
    private List<String> filepaths;
    private String anOutput;
    private final String FILENAME = CucumberConstant.PATH + "carregados.txt";

    private void setArgumments() {
    	arguments = new String[2];
    	arguments[0] = "-f";
    	arguments[1] = FILENAME;
    }
        
    /* Scenario: The input file "carregados.txt" exists and is not empty, and the analysis output is not empty */
    @Given("^A file containing a list of filenames$")
    public void receiveFile() throws Throwable {
    	this.setArgumments();
    	File file = new File(arguments[1]);
    	Assert.assertNotNull(file);
    }
    
    @When("^Analisa-SuSy was called by SuSy$")
    public void AnalisaSuSyWasCalled() throws Throwable {
    	aCLI = new CLInterface(arguments);
        Assert.assertTrue(aCLI.getFilepaths().isEmpty());
    }    
    
    @Then("^Generate a string containing the concatenation of all files to be analyzed$")
    public void generateConcatenation() throws Throwable {
    	aCLI.parse();
        filepaths = aCLI.getFilepaths();
        Assert.assertTrue(filepaths.size() >= 1);
    }
    
    @And("^Run the analysis tool passing all filenames as a single input$")
    public void callAnalysisTool() throws Throwable {    	
    	this.metricsPerTool = aCLI.getMetricsPerTool();
        anOutput = AnalisaSusy.runAnalysis(metricsPerTool, filepaths);
        Assert.assertNotNull(anOutput);
    }
    
    @And("^Generate an output containing the analysis result$")
    public void generateReport() throws Throwable {
    	System.out.print(anOutput);
    	Assert.assertTrue(anOutput.length() > 0);
    }
    
    public void setAnOutput(String anOutput) {
	this.anOutput = anOutput;
    }
    	        
}