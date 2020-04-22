package base;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import utilities.ExcelReader;
import utilities.ExtentManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import utilities.Utilities;

public class Page {
    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
    public static WebDriverWait wait;
    public ExtentReports report = ExtentManager.getInstance();
    public static ExtentTest test;
    public static String browser;
    public static TopMenu topMenu;
    public Page() {

        if(driver == null) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis);

                log.debug("Config file loaded !!!!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                OR.load(fis);
                log.debug("OR file loaded !!!!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
                browser = System.getenv("browser");
            }else{
                browser = config.getProperty("browser");
            }

            config.setProperty("browser", browser);

            if(System.getProperty("browser")!= null && System.getenv("browser").length()>1){
                browser = System.getenv("browser");
            }else{
                browser = config.getProperty("browser");
            }
            config.setProperty("browser", browser);

            if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.default_content_setting_values.notifications", 2);
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
                log.debug("Chrome Launched !!!!");
            } else if (config.getProperty("browser").equalsIgnoreCase("ie")) {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }
            driver.get(config.getProperty("testsiteurl"));
            //test.log(LogStatus.INFO, " Open the test site URL: " + driver.getCurrentUrl());
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 5);
            topMenu = new TopMenu(driver);
        }
    }
    public void click(String locator) {

        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
        } else if (locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(OR.getProperty(locator))).click();
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(OR.getProperty(locator))).click();
        }
        log.debug("Clicking on an element: "+ locator);
        test.log(LogStatus.INFO, "Clicking on : " + locator);
    }

    public void type(String locator, String value) {
        System.out.println("Locator: " + locator + " ----  " + "Value: " + value);
        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
        }
        log.debug("Typing in : " + locator + " entered value as " + value);
        test.log(LogStatus.INFO, "Clicking on : " + locator);

    }

    static WebElement dropdown;

    public void select(String locator, String value) {

        if (locator.endsWith("_CSS")) {
            dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
        } else if (locator.endsWith("_XPATH")) {
            dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
        } else if (locator.endsWith("_ID")) {
            dropdown = driver.findElement(By.id(OR.getProperty(locator)));
        }

        Select select = new Select(dropdown);
        select.selectByVisibleText(value);

        test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + value);

    }

    public static void verifyEquals(String expected, String actual) throws IOException {

        try {

            Assert.assertEquals(actual, expected);

        } catch (Throwable t) {

            Utilities.captureScreenshot();
            // ReportNG
            Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
            Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + "><img src=" + Utilities.screenshotName
                    + " height=200 width=200></img></a>");
            Reporter.log("<br>");
            Reporter.log("<br>");
            // Extent Reports
            test.log(LogStatus.FAIL, " Verification failed with exception : " + t.getMessage());
            //test.log(Status.FAIL, test.addScreenCapture(Utilities.screenshotName));
            //test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());

        }


    }

    public static void quit(){
        driver.quit();
    }
}
