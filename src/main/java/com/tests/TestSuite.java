package com.tests;

import com.codebase.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.DataParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestSuite extends TestBase {

    @DataProvider(name = "data01")
    public Object[][] dataForTest01() {
        return DataParser.readExcel("test01");
    }

    @Test(dataProvider = "data01")
    public void test01(String url, String email, String password, String userName) {
        try {
            writeToReport(LogStatus.INFO, "Start of test01");
            TestFunctions.functionLogin(url, email, password, userName);
            writeToReport(LogStatus.INFO, "End of test01");
        } catch (Exception error) {
            writeToReport(LogStatus.INFO, "End of test01");
            throw error;
        }
    }
}