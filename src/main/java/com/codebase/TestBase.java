package com.codebase;

import com.beforesuite.Helper;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class TestBase extends Helper {

    private static WebDriverWait wait;

    private static WebElement _getElement(String xpath) {
        WebElement element;
        wait = new WebDriverWait(driver, 30);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return element;
    }

    public static void navigateToURL(String url) {
        driver.get(url);
        writeToReport(LogStatus.PASS, "NAVIGATE TO URL : PASSED \n\n Navigated to [" + url + "]");
    }

    public static void click(String xpath) {
        WebElement element = _getElement(xpath);
        element.click();
        writeToReport(LogStatus.PASS, "\n CLICK : PASSED \n\n Clicked on object [" + xpath + "]");
    }

    public static void type(String xpath, String value) {
        WebElement element = _getElement(xpath);
        element.clear();
        element.sendKeys(value);
        writeToReport(LogStatus.PASS, "\n TYPE : PASSED \n\n Typed on object [" + xpath + "] Value : [" + value + "]");
    }

    public static String getTextFromElement(String xpath) {
        String text = "";
        WebElement element = _getElement(xpath);
        text = element.getText();
        writeToReport(LogStatus.PASS, "\n GET TEXT : PASSED \n\n Retrieved text from object [" + xpath + "] Text : [" + text + "]");
        return text;
    }

    public static boolean checkIsSelected(String xpath) {
        boolean isSelected = false;
        WebElement element = _getElement(xpath);
        isSelected = element.isSelected();
        writeToReport(LogStatus.PASS, "\n IS SELECTED : PASSED \n\n Checked is selected from object [" + xpath + "] Value : [" + isSelected + "]");
        return isSelected;
    }

    public static void writeToReport(LogStatus logStatus, String message) {
        switch (logStatus) {
            case INFO:
                _initializeTest(logStatus, message);
                break;
            case PASS:
            case FAIL:
                extentTest.log(logStatus, message);
                break;
            default:
                break;
        }
        log.info(message);
    }

    private static void _initializeTest(LogStatus logStatus, String message) {
        if (message.contains("test") && message.startsWith("Start")) {
            String[] ar = message.split("of");
            extentTest = extentReports.startTest(ar[1].trim());
        }
        extentTest.log(logStatus, message);
    }

    public static void assertStringEquals(String actual, String expected) throws AssertionError {
        if (actual.equals(expected)) {
            writeToReport(LogStatus.PASS, "\n ASSERT - PASSED \n\n Expected Text : [" + expected + "] Actual Text : [" + actual + "].");
        } else {
            takeScreenShot("assertStringEqualsFailure");
            writeToReport(LogStatus.FAIL, "\n ASSERT - FAILED \n\n Expected Text : [" + expected + "] Actual Text : [" + actual + "].");
            throw new AssertionError("\n ASSERT - FAILED \n\n Expected Text : [" + expected + "] Actual Text : [" + actual + "].");
        }
    }

    public static void takeScreenShot(String ssName) {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File scrFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(System.getProperty("user.dir") + "\\ScreenShots\\" + ssName + ".png");
        try {
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
        }
    }
}