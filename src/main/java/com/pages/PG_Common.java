package com.pages;

public class PG_Common {

    public static String lnk_SignIn() {
        return "//a[normalize-space()='Sign in']";
    }

    public static String lnk_UserName() {
        return "//a[@class='account']/span";
    }
}
