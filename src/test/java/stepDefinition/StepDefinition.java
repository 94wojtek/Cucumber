package stepDefinition;

import com.sample.textsearch.PatternSearch;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class StepDefinition {
    private PatternSearch ps;
    private int offset;
    private String[] args;
    private String exception;

    @After
    public void resetFields() {
        ps = null;
        offset = 0;
        args = null;
        exception = null;
    }

    @Given("Input pattern is (.+)$")
    public void inputPatternIsPattern(String pattern) {
        args = new String[2];
        args[0] = pattern;
        System.out.println("Pattern: " + args[0]);
        ps = new PatternSearch(args[0]);
    }

    @And("Input text is (.+)$")
    public void inputTextIsText(String txt) {
        args[1] = txt;
        System.out.println("Txt: " + args[1]);
    }

    @When("Offset is calculated")
    public void offsetIsCalculated() {
        offset = ps.search(args[1]);
    }

    @Then("Offset number is (.+)$")
    public void offsetNumberIsOffset(int offset) {
        System.out.println("Offset: " + offset);
        System.out.println("");
        assertEquals(offset, this.offset);
    }

    @Given("No parameters are provided")
    public void oneParameterIsProvided() {
        args = new String[0];
    }

    @When("App starts")
    public void appStarts() {
        try {
            PatternSearch.main(args);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("App started.");
            exception = e.getMessage();
        }
    }

    @Then("App is terminated")
    public void appIsTerminated() {
        int i = args.length;
        String err = "Index " + i + " out of bounds for length " + i;
        assertEquals(err, exception);
        System.out.println("Expected exception was thrown.\n");
    }

    @Given("One parameter provided is (.+)$")
    public void oneParameterProvidedIs(String param) {
        args = new String[1];
        args[0] = param;
    }
}
