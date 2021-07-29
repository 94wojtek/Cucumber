package stepDefinition;

import com.sample.textsearch.PatternSearch;
import io.cucumber.java.After;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class StepDefinition {
    private PatternSearch ps;
    private String txt;
    private int offset;
    private String[] args;
    private String exception;

    @After
    public void resetFields() {
        txt = null;
        offset = 0;
        args = null;
        exception = null;
    }

    @Given("Input pattern is (.+)$")
    public void inputPatternIsPattern(String pattern) {
        System.out.println("Pattern: " + pattern);
        ps = new PatternSearch(pattern);
    }

    @And("Input text is (.+)$")
    public void inputTextIsText(String txt) {
        System.out.println("Txt: " + txt);
        this.txt = txt;
    }

    @When("Offset is calculated")
    public void offsetIsCalculated() {
        offset = ps.search(txt);
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
        String err = "Index 0 out of bounds for length 0";
        assertEquals(err, exception);
        System.out.println("Expected exception was thrown.");
    }
}
