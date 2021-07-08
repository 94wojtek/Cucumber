package stepDefinition;

import com.sample.textsearch.PatternSearch;
import io.cucumber.java.After;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class StepDefinition {
    private PatternSearch ps;
    private String txt;
    private int offset;
    private String[] args;

    @After
    public void resetFields() {
        txt = null;
        offset = 0;
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

    @Given("Input pattern value is empty")
    public void inputPatternValueIsEmpty() {
        String pattern = "";
        System.out.println("Pattern: " + pattern);
        ps = new PatternSearch(pattern);
    }

    @And("Input text value is empty")
    public void inputTextValueIsNull() {
        this.txt = "";
        System.out.println("Txt: " + txt);
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public String listOfStringListsType(String cell) {
        return cell;
    }

    @Given("Input pattern is")
    public void inputPatternIs(List<List<String>> patts) {
        int index = 0;
        for(String element : patts.get(index)) {
            ps = new PatternSearch(element);
            System.out.println("Pattern: " + element);
            index++;
        }
    }

    @And("Input text value is")
    public void inputTextValueIs(List<List<String>> txts) {
        int index = 0;
        for(String element : txts.get(index)) {
            txt = element;
            System.out.println("Txt: " + element);
            index++;
        }
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

    @Given("Parameters list is empty.")
    public void parametersListIsEmpty() {
        args = new String[2];
        args[0] = null;
        args[1] = null;
    }

    @When("Program is run.")
    public void programIsRun() {
        PatternSearch.main(args);
    }

    @Then("Exception is thrown.")
    public void exceptionIsThrown() {
    }
}
