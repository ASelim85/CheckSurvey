package Tests;

import Elements.LoginElements;
import Pages.LoginPage;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;


public class loginTestCases extends TestBase {
    Faker faker = new Faker();
    @Test(priority = 0, enabled = true)
    public void LoginWithValidCredentials() {
        String surveyName = faker.animal().name();

        loginPage.clickSignInAzure()
                .setEmailPhoneSkype("mabdelsoud@youxel.com")
                .clickNext()
                .setPassword("M0h@med2359#")
                .clickSignIn()
                .clickYes()
                .navigateToSurveysPage()
                .addANewSurvey(surveyName)
                .addQuestions("What is your name?", "set your answer here")
                .navigateToSurveysPage()
                .searchForASurvey(surveyName);
        Assert.assertEquals(loginPage.getSurveyTitle(surveyName), surveyName);
    }


    @Test(priority = 1, enabled = true)
    public void LoginWithInValidCredentials() {
        loginPage.setUsername("Username");
        loginPage.setPassword("P@ssw0rd1");
        loginPage.clickSignIn();
        loginPage.GetErrorMSG();
        Assert.assertEquals(loginPage.GetErrorMSG(), "Invalid Login");
    }
}