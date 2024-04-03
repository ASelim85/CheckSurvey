package Pages;

import Elements.LoginElements;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;


public class LoginPage extends LoginElements {
    Faker faker = new Faker();
    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public LoginPage successLogin(String username) {

        click(btnSignInAzure);
        return this;
    }

    public LoginPage setUsername(String username) {
        click(btnSignInAzure);
        return this;
    }

    public LoginPage clickSignInAzure() {
        click(btnSignInAzure);
        return this;
    }

    public LoginPage setEmailPhoneSkype(String data) {
        setText(txtEmailPhoneSkype, data);
        return this;
    }

    public LoginPage clickNext() {
        click(btnNext);
        return this;

    }

    public LoginPage setPassword(String password) {
        setText(txtPassword, password);
        return this;

    }

    public LoginPage clickSignIn() {
        click(btnSignIn);
        return this;

    }

    public LoginPage clickNewSurveyBtnOnDesktop() {
        click(newSurveyBtnOnDesktop);
        return this;

    }

    public LoginPage clickNewSurveyBtnOnMobile() {
        click(newSurveyBtnOnMobile);
        return this;

    }

    public LoginPage setSurveyTitle(String title) {
        setText(surveyTitleInputField, title);
        return this;

    }

    public LoginPage clickCancelBtn() {
        click(cancelBtn);
        return this;

    }

    public LoginPage clickCreateBtn() {
        click(createBtn);
        return this;

    }

    public LoginPage setSearchBar(String query) {
        setText(searchBar, query);
        return this;

    }

//    public String getFirstSearchResult() {
//        return getContent(firstSearchResult);
//    }

    public LoginPage clickAllSurveysOption() {
        click(allSurveysOption);
        return this;

    }

    public LoginPage clickNewFolderBtn() {
        click(newFolderBtn);
        return this;

    }

    public LoginPage clickArchiveTab() {
        click(archiveTab);
        return this;

    }

    public LoginPage clickTrashTab() {
        click(trashTab);
        return this;

    }

    public String GetErrorMSG() {
        waitElement(errorMSG);
        return find(errorMSG).getText();
    }

    public LoginPage setOTP(String otp) {
        setText(txtOTP, otp);
        return this;

    }

    public LoginPage clickVerifyOTP() {
        click(btnVerifyOTP);
        return this;

    }

    public LoginPage addANewSurvey(String surveyName) {
        click(newSurveyBtnOnDesktop);
        setText(surveyTitleInputField, surveyName);
        click(createBtn);
        return this;
    }

    public LoginPage searchForASurvey(String surveyTitle) {
        setText(searchBar, surveyTitle);
        return this;
    }

    public LoginPage navigateToDashboardPage() {
        click(dashboardDropdown);
        click(dashboardOption);
        return this;
    }

    public LoginPage navigateToSurveysPage() {
        click(dashboardDropdown);
        click(surveysOption);
        return this;

    }

    public LoginPage navigateToResponsesPage() {
        click(dashboardDropdown);
        click(responsesOption);
        return this;

    }

    public LoginPage addQuestions(String InputFieldName, String InputFieldPlaceholder) {
        click(addNewElementBtn);
        click(inputElement);
        setText(inputElementNameField, InputFieldName);
        setText(inputElementPlaceholderField, InputFieldPlaceholder);
        return this;
    }

    public LoginPage clickYes() {
        click(nextButton);
        return this;
    }

    public String getSurveyTitle(String data) {
        return getContent(getFirstSearchResult(data));
    }
}
