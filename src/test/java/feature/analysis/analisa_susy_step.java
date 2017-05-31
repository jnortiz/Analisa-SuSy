package feature.analysis;

import java.util.List;

import org.junit.Assert;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

import com.ic.analisaSusy.commons.CLInterface;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
features = {"src/"},
glue = {"src.test.resourses.feature.analysis.analisa_susy_step.feature"},
plugin = {"pretty",
"html:target/cucumber-html-report",
"junit:target/cucumber-junit-report/allcukes.xml"},
tags = {"@Runme"}
)

public class analisa_susy_step {
	
    private String[] arguments;
    private CLInterface aCLI;

    private void setArgumments() {
    	this.arguments[0] = "-f";
    	this.arguments[1] = "carregados.txt";
    }
    
	/* Scenario: The input file "carregados.c" exists and is not empty, and the analysis output is not empty */
    @Given("^A file containing a list of filenames$")
    public void receiveFile() {
    	this.setArgumments();
        final CLInterface aCLI = new CLInterface(arguments);
        aCLI.parse();
        final List<String> filepaths = aCLI.getFilepaths();
        Assert.assertTrue(filepaths.size() >= 1);    	
    }
    
    @When("^Analisa-SuSy was called by SuSy$")
    public void AnalisaSuSyWasCalled() {
        aCLI = new CLInterface(arguments);
        Assert.assertTrue(aCLI.getFilepaths().size() == 0);
    }    
    
    @Then("^Generate a string containing the concatenation of all files to be analysed$")
    public void generateConcatenation() {
    	
    }
    
    @And("^Run the analysis tool passing all filenames as a single input$")
    public void callAnalysisTool() {
    }

    @And("^Parse the output of the analysis tool$")
    public void parser() {
    	
    }
    
    @And("^Generate a file containing the analysis result$")
    public void generateReport() {
    	
    }    

    /* Scenario: The input file "carregados.c" exists and is not empty, but the parser fails */
    @When("^The parser outputs an empty file$")
    public void parserErrorEmptyFile() {
    }
    
    @Then("^The Analisa-SuSy output should send an error flag to the pos-compiling caller$")
    public void analisaSuSyErrorException() {
    }
    
}