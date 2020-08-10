package com.beforesuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Helper {

    public static WebDriver driver;
    public static String timestamp, reportLocation;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    public static Logger log;
    String fileName = "Execution_Report" + ".html";

    Properties properties = new Properties();

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        timestamp = simpleDateFormat.format(new Date());
    }

    @BeforeTest
    public void init() throws Exception {
        _repoPath(System.getProperty("user.dir") + "\\TestOutput\\" + timestamp);
        extentReports = new ExtentReports(reportLocation + "\\" + fileName, true);
        extentReports.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
        properties.load(new FileInputStream(System.getProperty("user.dir") + "\\log4j.properties"));
        PropertyConfigurator.configure(properties);
        log = Logger.getLogger("TestStep");
        log.info("Test Automation Framework");
    }

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\lib\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        log.info("Initializing WebDriver - ChromeDriver");
    }

    @AfterMethod
    public void tearDown() {
        extentReports.endTest(extentTest);
        extentReports.flush();
        driver.quit();
        log.info("Quitting WebDriver - ChromeDriver");
    }

    @AfterTest
    public void close() {
        extentReports.close();
    }

    private static String _repoPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (file.mkdirs()) {
                reportLocation = file.toString();
                return reportLocation;
            } else {
                reportLocation = System.getProperty("user.dir");
                return reportLocation;
            }
        }
        return reportLocation;
    }
}
