package stepDefinition;

import com.sample.textsearch.PatternSearch;
import io.cucumber.java.After;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.*;

public class StepDefinition {
    private PatternSearch ps;
    private String txt;
    private int offset;
    String[] args;
    private Function<ArrayIndexOutOfBoundsException, Integer> e;

    @After
    public void resetFields() {
        txt = null;
        offset = 0;
        args = null;
    }

    //Replacing "[blank]" string in datatable with empty string
    @DataTableType(replaceWithEmptyString = "[blank]")
    public String listOfStringListsType(String cell) {
        return cell;
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
        //ps = new PatternSearch(args[0]);
        //txt = args[1];
    }

    @When("App starts")
    public void app_starts() {
         e = (arr) -> ps.search(txt);
         ps.search(args[1]);
    }

    @Then("App is terminated")
    public void app_is_terminated() {
        //ArrayIndexOutOfBoundsException e =
       // e = assertThrows(ArrayIndexOutOfBoundsException.class, () -> PatternSearch.main(args));

        String err = "Index 0 out of bounds for length 0";
       // assertEquals(err, e.getMessage());
    }
}
