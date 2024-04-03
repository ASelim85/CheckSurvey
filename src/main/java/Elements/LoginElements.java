package Elements;

import Pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginElements extends PageBase {

    public By txtUsername = By.xpath("//button[@class='btn' and @name='provider' and @value='OpenId-Google']");
    public By errorMSG = By.xpath("/html/body/div[2]/div");
    public By txtOTP = By.id("basic_otp");
    public By btnVerifyOTP = By.xpath("//button[@type='submit']");
    public By btnSignInAzure = By.xpath("//button[@name='provider' and @value='OpenId-Azure']");
    public By txtEmailPhoneSkype = By.id("i0116");
    public By btnNext = By.id("idSIButton9");
    public By txtPassword = By.id("i0118");
    public By btnSignIn = By.id("idSIButton9");
    // New Survey Modal
    public By newSurveyBtnOnDesktop = By.
            xpath
                    (
                            "//chksurvey-button[@icon='chksurvey-icon-add-outline' and @class='btn-new-desktop']"
                    );
    public By newSurveyBtnOnMobile = By.
            xpath
                    (
                            "//chksurvey-button[@icon='chksurvey-icon-add-outline' and @class='btn-new-mobile']"
                    );
    public By surveyTitleInputField = By.
            xpath
                    (
                            "//div[@role='dialog']//div[@class='chksurvey-input-text']//input"
                    );
    public By cancelBtn = By.
            xpath
                    (
                            "(//div[@class='footer-buttons']//button)[1]"
                    );
    public By createBtn = By.
            xpath
                    (
                            "(//div[@class='footer-buttons']//button)[2]"
                    );
    public By searchBar = By.
            xpath
                    (
                            "//*[contains(@class,'chksurvey-icon-search')]/preceding-sibling::input"
                    );
    public By firstSearchResult1 = By.
            xpath
                    (
                            "//div[.='Title']/following-sibling::div/descendant::div[contains(@class, 'ng-star-inserted') and not(contains(@class , 'unicode-plainText'))]"
                    );
    public By getFirstSearchResult (String data){
        return By.xpath("(//span[@class='text-blue unicode-plainText clickable' and text()='"+" " +data+" "+"'])[2]");
    }
//    public By firstSearchResult = By.xpath("//span[@class='text-blue unicode-plainText clickable' and text()='"+Computers & Home']");
    // Side Menu
    public By allSurveysOption = By.
            xpath
                    (
                            "//span[contains(@class,'chksurvey-icon-all-forms')]/parent::div"
                    );
    public By newFolderBtn = By.
            xpath
                    (
                            "//span[contains(@class,'chksurvey-icon-add-outline')]/parent::div"
                    );
    public By archiveTab = By.
            xpath
                    (
                            "//span[contains(@class,'chksurvey-icon-archive')]/parent::div"
                    );
    public By trashTab = By.
            xpath
                    (
                            "//span[contains(@class,'chksurvey-icon-delete')]/parent::div"
                    );

    public By dashboardDropdown = By.
            xpath
                    (
                            "//div[@class='header-right']//div"
                    );
    public By dashboardOption = By.
            xpath
                    (
                            "(//ul[@role='menu']//li)[1]"
                    );
    public By surveysOption = By.
            xpath
                    (
                            "(//ul[@role='menu']//li)[2]"
                    );
    public By responsesOption = By.
            xpath
                    (
                            "(//ul[@role='menu']//li)[3]"
                    );
    public By addNewElementBtn = By.xpath("//div[@class='welcome-section']/following-sibling::div//p-button");

    public By closeElementsModalIcon = By.id("dfb-side-bar-close-icon");

    public By inputElement = By.xpath("(//div[@class='dfb-controls-panel']//button)[1]");

    public By textAreaElement = By.xpath("(//div[@class='dfb-controls-panel']//button)[2]");

    public By numberElement = By.xpath("(//div[@class='dfb-controls-panel']//button)[3]");

    public By dateAndTimeElement = By.xpath("(//div[@class='dfb-controls-panel']//button)[4]");

    public By paragraphElement = By.xpath("(//div[@class='dfb-controls-panel']//button)[5]");

    public By mapElement = By.xpath("(//div[@class='dfb-controls-panel']//button)[6]");

    public By singleChoiceElement = By.xpath("(//div[@class='dfb-controls-panel']//button)[7]");

    public By dropdownMenuElement = By.xpath("(//div[@class='dfb-controls-panel']//button)[8]");

    public By multipleChoicesElement = By.xpath("(//div[@class='dfb-controls-panel']//button)[9]");

    public By npsElement = By.xpath("(//div[@class='dfb-controls-panel']//button)[10]");

    public By ratingElement = By.xpath("(//div[@class='dfb-controls-panel']//button)[11]");

    public By faceRatingElement = By.xpath("(//div[@class='dfb-controls-panel']//button)[12]");

    public By sliderElement = By.xpath("(//div[@class='dfb-controls-panel']//button)[13]");

    public By inputElementNameField = By.xpath("//div[contains(@class, 'dfb-question-label')]//input[contains(@class, 'p-inputtext')]");

    public By inputElementPlaceholderField = By.cssSelector("div.question input.p-inputtext");

    public By threeVerticalDots = By.xpath("//dfb-button[@icon='dfb-icon-dots']");

    public By distributionOption = By.xpath("(//div[contains(@class, 'actionCard')]/ancestor::dfb-menu-item[contains(concat(' ', normalize-space(@class), ' '), ' ng-star-inserted ') and not(contains(@class, 'dfb-d-flex-mobile'))])[1]");

    public By cancelButton = By.xpath("(//div[contains(@class, 'footer-buttons')]//button)[1]");

    public By publishBtn = By.xpath("(//div[contains(@class, 'footer-buttons')]//button)[2]");

    public By publishOption = By.xpath("(//div[contains(@class, 'actionCard')]/ancestor::dfb-menu-item[contains(concat(' ', normalize-space(@class), ' '), ' ng-star-inserted ') and not(contains(@class, 'dfb-d-flex-mobile'))])[2]");
    public By nextButton = By.
            xpath
                    (
                            "//input[@data-report-event='Signin_Submit']"
                    );


    public LoginElements(WebDriver driver) {
        super(driver);
    }
}
