package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;
import utils.ConfigReader;

public class Login extends CommonMethods {
    @When("user enters a valid email and password")
    public void user_enters_a_valid_email_and_password() {
        sendText(login.usernameTextBox,ConfigReader.getPropertyValue("username"));
        sendText(login.passwordTextBox,ConfigReader.getPropertyValue("password"));

    }
    @When("clicks on Login button")
    public void clicks_on_login_button() {
        doClick(login.loginBtn);
    }
    @Then("the user is logged in")
    public void the_user_is_logged_in() {
        String actualMsg=login.welcomeText.getText();
        String expectedMsg="Welcome Admin";

        Assert.assertEquals(actualMsg,expectedMsg);
    }
}
