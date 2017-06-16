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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;

public class CcsmInputTestStep {
    private String[] arguments;
    private CLInterface aCLI;
    
    private final String EMPTY = CucumberConstant.PATH + "empty.txt";
    private final String ERROR_PATH = CucumberConstant.PATH + "errorPath.txt";
    private final String WITHOUT_C = CucumberConstant.PATH + "withoutC.txt";
    private final String BROKEN = CucumberConstant.PATH + "broken.txt";

    //Scenario The file carregados.txt is empty
    @Given("^The carregados.txt file exists and was opened properly (\\d)$")
    public void fileCarregadosTxtExists(int index) {
        arguments = new String[2];
        arguments[0] = "-f";
        
        switch(index) {
            case 1:
                arguments[1] = EMPTY;
                break;
            case 2:
                arguments[1] = ERROR_PATH;
                break;
            case 3:
                arguments[1] = WITHOUT_C;
                break;
            case 4:
                arguments[1] = BROKEN;
                break;
        }
        
        Assert.assertTrue(arguments.length == 2);
    }

    @When("^It has no paths at all$")
    public void withoutPath() {
        File file = new File(arguments[1]);
                
        try{
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	
	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        Assert.assertEquals("", sb.toString());
	    } finally {
	        br.close();
	    }
        } catch (FileNotFoundException e) {
            Assert.assertTrue(false);
	} catch (IOException e) {
            Assert.assertTrue(false);
	}
    }
    
    @Then("^Throw a message saying there is no C files to be evaluated$")
    public void analisaSuSyIsCalled() {
        try{
            ErrorChecking.verifyCCSM();
            aCLI = new CLInterface(arguments);
            aCLI.parse();
            final List<String> filepaths = aCLI.getFilepaths();
            final Multimap<Tool, Metric> metricsPerTool = aCLI.getMetricsPerTool();

            String anOutput = runAnalysis(metricsPerTool, filepaths);
        }catch(Exception e) {

        }
        Assert.assertTrue(AnalisaSusy.errors.containsKey(ApplicationError.NOT_FOUND_FILES));
    }
    
    @When("^The paths point to non-existent files$")
    public void fileWrongPath() {
        File file = new File(arguments[1]);
                
        try{
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	
	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        Assert.assertNotEquals("", sb.toString());
	    } finally {
	        br.close();
	    }
        } catch (FileNotFoundException e) {
            Assert.assertTrue(false);
	} catch (IOException e) {
            Assert.assertTrue(false);
	}
    }
    
    @Then("^Throw a message saying files doesn't exist$")
    public void messageSayingFilesDoesntExist() {
        try{
            ErrorChecking.verifyCCSM();
            aCLI = new CLInterface(arguments);
            aCLI.parse();
            final List<String> filepaths = aCLI.getFilepaths();
            final Multimap<Tool, Metric> metricsPerTool = aCLI.getMetricsPerTool();

            String anOutput = runAnalysis(metricsPerTool, filepaths);
        }catch(Exception e) {

        }
        Assert.assertTrue(AnalisaSusy.errors.containsKey(ApplicationError.NOT_FOUND_FILES));
    }
    
    @When("^There is no paths to files with C extension$")
    public void withoutCExtension() {
        File file = new File(arguments[1]);
                
        try{
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	
	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        Assert.assertNotEquals("", sb.toString());
                Assert.assertFalse(sb.toString().contains(".c"));
                Assert.assertFalse(sb.toString().contains(".h"));
	    } finally {
	        br.close();
	    }
        } catch (FileNotFoundException e) {
            Assert.assertTrue(false);
	} catch (IOException e) {
            Assert.assertTrue(false);
	}
    }
    
    @Then("^Throw a message saying no C files were found$")
    public void messageSayingNoCFiles() {
        try{
            ErrorChecking.verifyCCSM();
            aCLI = new CLInterface(arguments);
            aCLI.parse();
            final List<String> filepaths = aCLI.getFilepaths();
            final Multimap<Tool, Metric> metricsPerTool = aCLI.getMetricsPerTool();

            String anOutput = runAnalysis(metricsPerTool, filepaths);
        }catch(Exception e) {

        }
        Assert.assertTrue(AnalisaSusy.errors.containsKey(ApplicationError.INVALID_EXT));
    }
    
    @When("^The path points to a C file but it don't pass in compilation phase$")
    public void brokenCFile() {
        try{
            ErrorChecking.verifyCCSM();
            aCLI = new CLInterface(arguments);
            aCLI.parse();
            final List<String> filepaths = aCLI.getFilepaths();
            final Multimap<Tool, Metric> metricsPerTool = aCLI.getMetricsPerTool();

            String anOutput = runAnalysis(metricsPerTool, filepaths);
        }catch(Exception e) {

        }
    }
    
    @Then("^Throw a message saying the file is not a valid C source code$")
    public void invalidCSource() {
        Assert.assertTrue(AnalisaSusy.errors.containsKey(ApplicationError.NO_VALID_FILES));
    }
}
