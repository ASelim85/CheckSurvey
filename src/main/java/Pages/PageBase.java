package Pages;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class PageBase {
    public static WebDriver driver;
    protected SoftAssert softAssert = new SoftAssert();

    public PageBase(WebDriver driver) {
        PageBase.driver = driver;
    }

    public void waitElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitElementToBeDisplayed(By locator, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(locator, "src", value));
    }

    public void waitWebElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    @SneakyThrows
    public WebElement find(By locator) {
        waitElement(locator);
        scrollElement(locator);
        highlightElement(locator);
        return driver.findElement(locator);
    }

    @SneakyThrows
    public void setText(By locator, String data) {
        waitElement(locator);
        find(locator).isEnabled();
        find(locator).clear();
        find(locator).sendKeys(data);
    }

    public void setKeys(By locator, Keys key) {
        waitElement(locator);
        find(locator).sendKeys(key);
    }

    @SneakyThrows
    public void click(By locator) {
        waitElement(locator);
        find(locator).click();
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(
                    ".\\TestData\\ScreenShots\\" + getClass().getSimpleName() + "\\" + new Throwable().getStackTrace()[0].getMethodName() + "_" + getCurrentDate() + ".png"));
        } catch (Exception ignored) {
        }
    }

    public void clickUpload(By locator) {
        waitElement(locator);
        find(locator).click();
    }

    public void selectData(By locator, int index) {
        Select select;
        select = new Select(find(locator));
        select.selectByIndex(index);
    }

    public void ClickElementsByClass(By locator, int index) {
        waitElement(locator);
        WebElement element;
        element = driver.findElements(locator)
                .get(index);
        element.click();
    }

    public boolean isDisplayed(By locator) {
        return find(locator).isDisplayed();
    }

    public String getContent(By locator) {
        waitElement(locator);
        return find(locator).getText();
    }

    public String getContentByValue(By locator) {
        waitElement(locator);
        return find(locator).getAttribute("value");
    }

    public String getContentBySrc(By locator) {
        waitElement(locator);
        return find(locator).getAttribute("src");
    }


    public void scrollDownToElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)", "");
    }

    public void scrollUpToElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -1000)", "");
    }

    public void hover(By locator) {
        WebElement ele = driver.findElement(locator);
        Actions action = new Actions(driver);
        action.moveToElement(ele)
                .perform();
    }

    public static String getFutureDate() {
        Date dt = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(Calendar.DATE, 364);
        dt = calendar.getTime();
        return new SimpleDateFormat("dd/MM/yyyy").format(dt);
    }

    public String setXpathByDataID(String source) {
        return "//*[@data-axis-test-id='" + source + "']";
    }

    public String setXpathByClass(String cls) {
        return "//*[@class='" + cls + "']";
    }

    public String setXpathById(String id) {
        return "//*[@id='" + id + "']";
    }

    public String setXpathByHref(String href) {
        return STR."//*[@href='\{href}']";
    }

    public static WebElement setElement(String source) {
        return driver.findElement(By.xpath("//*[@data-axis-test-id='" + source + "']"));
    }

    public String setXpathByText(String source) {
        return "//*[text()='" + source + "']";
    }

    public String setXpathByContains(String source) {
        return "//*[contains(text(),'" + source + "')]";
    }

    public String setXpathByClassText(String source, String text) {
        return "//*[@class='" + source + "' and text()='" + text + "']";
    }

    public String setXpathByValue(By locator) {
        waitElement(locator);
        return find(locator).getAttribute("value");
    }

    public void clickByWebElement(String dataID) {
        WebElement element = driver.findElement(By.xpath("//*[@data-axis-test-id='" + dataID + "']"));
        waitWebElement(element);
        element.click();
    }

    public void clickByWebElement1(WebElement element, String dataID) {
        waitWebElement(element);
        element = driver.findElement(By.xpath("//*[@data-axis-test-id='" + dataID + "']"));
        waitWebElement(element);
        element.click();
    }


    public boolean isDisabled(By locator) {
        waitElement(locator);
        WebElement element = (WebElement) driver.findElement(locator);
        if (element.isEnabled()) {
            return true;
        } else {
            return false;
        }
    }

    @SneakyThrows
    public void softAssertionEqual(By locator, String expected) throws IOException {
        waitElement(locator);
        softAssert.assertEquals(getContent(locator), expected);
        if (getContent(locator).equals(expected)) {
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
            String currentMethod = new Object() {
            }.getClass().getSimpleName();
//            String methodName = currentMethod.getName();
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(
                    ".\\TestData\\ScreenShots\\SoftAssertions\\" + getClass().getName() + "\\" + currentMethod + "_" + PageBase.getCurrentDate() + ".png"));
        }
    }


    @SneakyThrows
    public void softAssertionEqual1(By locator, String expected) throws IOException {
        waitElement(locator);
        softAssert.assertEquals(getContent(locator), expected);
        if (getContent(locator).equals(expected)) {
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
            Method currentMethod = new Object() {
            }.getClass().getEnclosingMethod();
            String methodName = currentMethod.getName();
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(
                    ".\\TestData\\ScreenShots\\SoftAssertions\\" + getClass().getName() + "\\" + methodName + "_" + PageBase.getCurrentDate() + ".png"));
        }
    }

    public IAssert<?> softAssertAll() {
        softAssert.assertAll();
        return null;
    }

    public void softAssertionImageSrc(By locator, String expected, String value) {
        waitElementToBeDisplayed(locator, value);
        softAssert.assertTrue(getContentBySrc(locator).contains(expected));
        if (getContent(locator).contains(expected)) {
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
        }
    }

    public void softAssertionImageEqual(By locator, String expected, String value) {
        waitElementToBeDisplayed(locator, value);
        softAssert.assertEquals(getContentBySrc(locator), expected);
        if (getContentBySrc(locator).contains(expected)) {
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
        }
    }

    public void softAssertionTrue(By locator) {
        waitElement(locator);
        softAssert.assertTrue(isDisabled(locator));
        if (getContent(locator).equals("True")) {
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
        }
    }

    public void softAssertionContain(By locator, String expected) {
        waitElement(locator);
        softAssert.assertTrue(getContent(locator).contains(expected));
        if (getContent(locator).contains(expected)) {
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
        }
    }

    public void softAssertionNotNull(By locator) {
        waitElement(locator);
        softAssert.assertNotNull(getContent(locator));
        highlightAssertedElements(locator);
    }


    public void uploadFile(By locator, String URL) throws AWTException, InterruptedException {
        clickUpload(locator);
        Robot robot = new Robot();
        StringSelection str = new StringSelection(URL);
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(str, null);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1500);
    }

    public void scrollElement(By locator) {
        WebElement element = driver.findElement(locator);
        int windowHeight = driver.manage()
                .window()
                .getSize()
                .getHeight();
        int elementLocation = element.getLocation()
                .getY();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, " + (elementLocation - windowHeight / 2) + ")");
    }

    public void highlightElement(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.border='2px solid fuchsia'", element);
    }

    public void highlightAssertedElements(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.border='2px solid blue'", element);
        jsExecutor.executeScript("arguments[0].style.backgroundColor = 'green';", element);
    }

    public void highlightUnAssertedElements(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.border='2px solid blue'", element);
        jsExecutor.executeScript("arguments[0].style.backgroundColor = 'red';", element);
    }

    public void uploadByXpath(String source, String path) {
        WebElement uploadField = driver.findElement(By.xpath("//*[@data-axis-test-id='" + source + "']"));
        String dir = System.getProperty("user.dir");
        String filePath = dir + path;
        uploadField.sendKeys(filePath);
    }

    public void xOperation(By locator, String text) {
        WebElement element = driver.findElement(locator);
        waitElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .click()
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .perform();
        actions.keyDown(Keys.CONTROL)
                .sendKeys("x")
                .keyUp(Keys.CONTROL)
                .perform();
        setText(locator, text);
    }

    public String setXpathForCells(int row, int col) {
        return "//table/tbody/tr[" + row + "]/td[" + col + "]";
    }

    public static String generateArabicFirstName() {
        final String[] firstName = {"فاطمة", "زينب", "مريم", "خديجة", "نور", "لمى", "سارة", "عبدالله"
                , "محمد"
                , "أحمد"
                , "علي"
                , "مصطفى"
                , "يوسف"
                , "خالد"
                , "عمر"
                , "محمود"
                , "سعيد"
                , "حسين"
                , "ناصر"
                , "عبدالرحمن"
                , "طارق"
                , "عبدالعزيز"
                , "عبدالكريم"
                , "فارس"
                , "رامي"
                , "مجدي"
                , "سليمان"
                , "جمال"
                , "عبداللطيف"
                , "محسن"
                , "صالح"
                , "رضا"
                , "زكريا"
                , "نورالدين"
                , "جلال"
                , "عبدالملك"
                , "قاسم"};
        Random random = new Random();
        return firstName[random.nextInt(firstName.length)];
    }

    public static String generateArabicLastName() {
        final String[] MIDDLE_NAMES = {"عزيز", "محمد", "أحمد", "علي", "حسين", "محمود", "عبدالله"
                , "محمد"
                , "أحمد"
                , "علي"
                , "مصطفى"
                , "يوسف"
                , "خالد"
                , "عمر"
                , "محمود"
                , "سعيد"
                , "حسين"
                , "ناصر"
                , "عبدالرحمن"
                , "طارق"
                , "عبدالعزيز"
                , "عبدالكريم"
                , "فارس"
                , "رامي"
                , "مجدي"
                , "سليمان"
                , "جمال"
                , "عبداللطيف"
                , "محسن"
                , "صالح"
                , "رضا"
                , "زكريا"
                , "نورالدين"
                , "جلال"
                , "عبدالملك"
                , "قاسم"
                , "عمر"};

        final String[] LAST_NAMES = {"عبد العال"
                , "عبد الرحيم"
                , "عبد الرحمن"
                , "عبد الجبار"
                , "عبد الصبور"
                , "عبد المهيمن"
                , "عبد العزيز"
                , "عبد الملك"
                , "عبد السلام",
                "عبد المؤمن",
                "عبد الخالق",
                "عبدالله",
                "عبد الرشيد",
                "الملواني"
                , "يسري",
                "جمعة",
                "خميس"

        };
        Random random = new Random();
        String middleName1 = MIDDLE_NAMES[random.nextInt(MIDDLE_NAMES.length)];
        String middleName2 = MIDDLE_NAMES[random.nextInt(MIDDLE_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return middleName1 + " " + middleName2 + " " + lastName;
    }

    public static String generateArabicBusinessName() {
        final String[] businessName = {"زيزو", "محطة مصر", "اكسيس", "الاغا", "ديزني لاند", "كارت", "ماك", "اولاد رجب"
                , "شاورمة الريم"
                , "بافلوبرجر"
                , "نون"
                , "جوميا"
                , "امازون"
                , "حالا"
                , "وصل"
                , "صيدلية عماد زكي"
                , "ك&س"
                , "فيزيتا"
                , "بوما"
                , "اديداس"
                , "مول البستان"
                , "العزبي"
                , "اور كيدز"
                , "هدايا"
                , "منيوز"
                , "كارفور"
                , "طبالي"
                , "الشروق"
                , "جولد"
                , "ادفانسيس"
                , "لابوار"
                , "العبد"
                , "ديفاكتو"};
        Random random = new Random();
        return businessName[random.nextInt(businessName.length)];
    }

    protected static Faker faker = new Faker();

    public static int generateRNumber() {
        return Integer.parseInt(String.valueOf(faker.number().numberBetween(10, 99)));
    }

    public static String generateArabicJobName() {
        final String[] jobName = {
                "مهندس برمجيات",
                "مدير مشروع",
                "محاسب",
                "مهندس معماري",
                "مصمم جرافيك",
                "طبيب",
                "محامي",
                "معلم",
                "مدير موارد بشرية",
                "مهندس كهرباء",
                "مدير تسويق",
                "مهندس ميكانيكا",
                "مدير مالي",
                "مهندس صوت",
                "مدير عمليات",
                "مهندس بيانات",
                "مدير تكنولوجيا المعلومات",
                "مهندس شبكات",
                "مدير مبيعات",
                "مهندس طيران",
                "مدير جودة",
                "مهندس تصميم",
                "مدير تطوير أعمال",
                "محلل أعمال",
                "مهندس صناعي",
                "مدير تدريب",
                "مهندس نظم",
                "مدير علاقات عامة",
                "مهندس أتمتة",
                "مدير مشتريات",
                "محلل بيانات",
                "مدير مستودع",
                "مهندس برمجيات مضمنة",
                "مدير مراقبة الجودة",
                "مهندس إلكترونيات",
                "مدير موقع",
                "مهندس نفط",
                "مدير مشتريات",
                "مهندس كهربائي",
                "مدير تطوير منتج",
                "محلل نظم",
                "مدير تشغيل",
                "مهندس معالجة صورة",
                "مدير مشروع تنفيذي",
                "مهندس برمجيات محاكاة",
                "مدير تسويق رقمي",
                "مهندس مواد",
                "مدير تقنية المعلومات",
                "مهندس تحكم",
                "مدير مبيعات إقليمي",
                "مهندس تقنية البيانات",
                "مدير مالي",
                "مهندس معالجة إشارة",
                "مدير موارد بشرية",
                "مهندس تصنيع",
                "مدير مشروع مهندسين",
                "مهندس استشعار عن بعد",
                "مدير تسويق المنتج",
                "مصمم ويب",
                "مدير عمليات المشتريات",
                "مهندس برمجيات مضمنة",
                "مدير جودة",
                "مهندس تقنية برمجيات",
                "مدير تطوير الأعمال",
                "مهندس بحري",
                "مدير مختبر",
                "مهندس تحليل بيانات",
                "مدير مستودع",
                "مهندس معالجة إشارة رقمية",
                "مدير مشتريات",
                "مهندس كهربائي",
                "مدير تطوير المنتجات",
                "محلل نظم",
                "مدير تشغيل",
                "مهندس تصوير",
                "مدير مشروع تنفيذي",
                "مهندس برمجياتمحتوى",
                "مدير تسويق رقمي",
                "مهندس مواد",
                "مدير تقنية المعلومات",
                "مهندس تحكم",
                "مدير مبيعات إقليمي",
                "مهندس تقنية البيانات",
                "مدير مالي",
                "مهندس معالجة إشارة",
                "مدير موارد بشرية",
                "مهندس تصنيع",
                "مدير مشروع مهندسين",
                "مهندس استشعار عن بعد",
                "مدير تسويق المنتج",
                "مصمم ويب",
                "مدير عمليات المشتريات",
                "مهندس برمجيات مضمنة",
                "مدير جودة",
                "مهندس تقنية برمجيات",
                "مدير تطوير الأعمال",
                "مهندس بحري",
                "مدير مختبر",
                "مهندس تحليل بيانات",
                "مدير مستودع",
                "مهندس معالجة إشارة رقمية",
                "مدير مشتريات",
                "مهندس كهربائي",
                "مدير تطوير المنتجات",
                "محلل نظم",
                "مدير تشغيل",
                "مهندس تصوير",
                "مدير مشروع تنفيذي",
                "مهندس برمجيات محاكاة",
                "مدير تسويق رقمي",
                "مهندس مواد",
                "مدير تقنية المعلومات",
                "مهندس تحكم",
                "مدير مبيعات إقليمي",
                "مهندس تقنية البيانات",
                "مدير مالي",
                "مهندس معالجة إشارة",
                "مدير موارد بشرية",
                "مهندس تصنيع",
                "مدير مشروع مهندسين",
                "مهندس استشعار عن بعد",
                "مدير تسويق المنتج",
                "مصمم ويب",
                "مدير عمليات المشتريات",
                "مهندس برمجيات مضمنة",
                "مدير جودة",
                "مهندس تقنية برمجيات",
                "مدير تطوير الأعمال",
                "مهندس بحري",
                "مدير مختبر",
                "مهندس تحليل بيانات",
                "مدير مستودع",
                "مهندس معالجة إشارة رقمية",
                "مدير مشتريات",
                "مهندس كهربائي",
                "مدير تطوير المنتجات",
                "محلل نظم",
                "مدير تشغيل",
                "مهندس تصوير",
                "مدير مشروع تنفيذي",
                "مهندس برمجيات محاكاة",
                "مدير تسويق رقمي",
                "مهندس مواد",
                "مدير تقنية المعلومات",
                "مهندس تحكم",
                "مدير مبيعات إقليمي",
                "مهندس تقنية البيانات",
                "مدير مالي",
                "مهندس زراعي"};
        Random random = new Random();
        return jobName[random.nextInt(jobName.length)];
    }

    public static String generateArabicEntityName() {
        final String[] jobName = {
                "شركة الريادة العربية",
                "مجموعة الابتكار التقني",
                "شركة الإبداع المتقدمة",
                "مجموعة الخدمات المتكاملة",
                "شركة التجارة العالمية",
                "مجموعة الحلول الذكية",
                "شركة الصناعات المتقدمة",
                "مجموعة الابتكار والتطوير",
                "شركة الخدمات اللوجستية",
                "مجموعة الاستثمار العربية",
                "شركة الإبداع والتكنولوجيا",
                "مجموعة الحلول الاستراتيجية",
                "شركة الخدمات المالية المتقدمة",
                "مجموعة الاستشارات الإدارية",
                "شركة الطاقة المتجددة",
                "مجموعة التجارة الإلكترونية",
                "شركة الصناعات الغذائية",
                "مجموعة الاستثمار العقاري",
                "شركة الخدمات اللوجستية المتكاملة",
                "مجموعة الإعلام والتسويق",
                "مجموعة الإعلام والتسويق"};
        Random random = new Random();
        return jobName[random.nextInt(jobName.length)];
    }

    public static String generateUnitName() {
        final String[] jobName = {
                "مكتب الأمين",
                "إدارة التخطيط والتنسيق",
                "وحدة البلديات الفرعية",
                "وكالة الأمانة لشؤون بلديات المنطقة",
                "وكالة التعمير والمشاريع",
                "وكالة الخدمات",
                "الإدارة العامة لتنمية الاستثمارات",
                "الإدارة العامة لتقنية المعلومات والخدمات الإلكترونية",
                "إدارة التطوير الإداري",
                "الإدارة القانونية",
                "وحدة المتابعة",
                "الإدارة العامة للشؤون المالية والإدارية، ويرتبط بها:",
                "إدارة شئون الموظفين",
                "مركز الاتصالات والمحفوظات",
                "إدارة الميزانية",
                "إدارة المشتريات",
                "إدارة المستودعات",
                "إدارة مراقبة المخزون",
                "إدارة الخدمات العامة",
                "إدارة الشئون المالية",
                "الإدارة العامة للحدائق وعمارة البيئة ",
                "الإدارة العامة للنظافة",
                "إدارة العمليات المساندة",
                "إدارة مراقبة وتطوير النظافة",
                "إدارة الدراسات والتطوير",
                "الإدارة العامة لصحة البيئة",
                "الإدارة العامة للأسواق",
                "الإدارة العامة الراحة والسلامة",
                "الإدارة العامة للخدمات الاجتماعية",
                "وكالة التعمير والمشاريع",
                "الإدارة العامة للتخطيط العمراني",
                "الإدارة المركزية لرقابة المباني والمنشآت",
                "إدارة التخطيط",
                "إدارة المساحة وتنفيذ المخططات",
                "الإدارة العامة للدارسات والتصاميم",
                "إدارة تصميم الطرق وتصريف السيول",
                "الإدارة العامة للإشراف والتنفيذ",
                "الإدارة العامة للتشغيل والصيانة",
                "الإدارة العامة للأراضي والممتلكات",
                "إدارة الأراضي",
                "إدارة الممتلكات",
                "الإدارة العامة لتقنية المعلومات والخدمات الإلكترونية",
                "إدارة خدمات المعلومات",
                "إدارة هندسة وصيانة وتشغيل الحاسب الآلي",
                "إدارة تطوير التطبيقات الآلية",
                "وكالة الأمانة لشئون بلديات المنطقة",
                "إدارة الشئون الفنية",
                "إدارة الشئون المالية والإدارية",
                "إدارة الأراضي",
                "إدارة التخطيط",
                "الإدارة العامة لتنمية الاستثمارات"};
        Random random = new Random();
        return jobName[random.nextInt(jobName.length)];
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat("dd-MM-yyyy hh.mm.ss a").format(new Date());
    }

    public static String getDateFormat(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy | hh:mm a");
        Date date = new Date();
        System.out.println("Current Date : " + dateFormat.format(date));
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, -15);
        Date currentDatePlusOne = c.getTime();
        return String.valueOf(dateFormat.format(currentDatePlusOne)
                .toLowerCase());
    }
}
