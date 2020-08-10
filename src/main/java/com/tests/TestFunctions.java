package com.tests;

import com.codebase.TestBase;
import com.pages.PG_Authentication;
import com.pages.PG_Common;
import com.relevantcodes.extentreports.LogStatus;

public class TestFunctions extends TestBase {

    public static void functionLogin(String url, String email, String password, String userName) throws AssertionError{
        try {
            writeToReport(LogStatus.INFO, "Start of functionLogin");
            navigateToURL(url);
            click(PG_Common.lnk_SignIn());
            type(PG_Authentication.tf_EmailAddress(), email);
            type(PG_Authentication.tf_Password(), password);
            click(PG_Authentication.btn_SignIn());
            assertStringEquals(getTextFromElement(PG_Common.lnk_UserName()), userName);
            writeToReport(LogStatus.INFO, "End of functionLogin");
        } catch (Exception error) {
            writeToReport(LogStatus.INFO, "End of functionLogin");
            throw error;
        }

    }
}
