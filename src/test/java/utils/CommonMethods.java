package utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import steps.PageInitializer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class CommonMethods extends PageInitializer {
    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication() {
        ConfigReader.readProperties();
        String browserType = ConfigReader.getPropertyValue("browserType");
            switch (browserType) {
                case "Chrome":
                    driver=new ChromeDriver();
                    break;
                case "Firefox":
                    driver=new FirefoxDriver();
                    break;
                case "Edge":
                    driver=new EdgeDriver();
                    break;
        }
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Constants.WAIT_TIME));
        initializePageObjects();
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("This is the beginning of my test case");
        Log.info("My test case ID executing right now");
        Log.warning("My test case might have some trivial issue");
    }

    public static void tearDown() {
        Log.info("This test case about to get completed");
        Log.endTestCase("THis test case is finished");
        driver.close();
    }

    public static void doClick(WebElement element) {
        element.click();
    }

    public static void sendText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public static Select clickOnDropdown(WebElement element) {
        Select select = new Select(element);
        return select;
    }

    public static void selectByVisibleText(WebElement element, String text) {
        clickOnDropdown(element).selectByVisibleText(text);
    }

    public static void selectByIndex(WebElement element, int index) {
        clickOnDropdown(element).selectByIndex(index);
    }

    public static void selectByValue(WebElement element, String value) {
        clickOnDropdown(element).selectByValue(value);
    }

    public static void selectByOption(WebElement element, String text) {
        List<WebElement> options = clickOnDropdown(element).getOptions();
        for (WebElement option : options) {
            String ddlOptionText = option.getText();
            if (ddlOptionText.equalsIgnoreCase(text)) {
                option.click();
            }
        }
    }

    public static byte[] takeScreenshot(String imageName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile,
                    new File(Constants.SCREENSHOT_FILEPATH + imageName + " " +
                            getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return picBytes;
    }

    public static String getTimeStamp(String pattern) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
