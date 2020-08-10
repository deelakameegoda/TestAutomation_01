package com.pages;

public class PG_Authentication {

    public static String tf_EmailAddress() {
        return "//input[@id='email']";
    }

    public static String tf_Password() {
        return "//input[@id='passwd']";
    }

    public static String btn_SignIn() {
        return "//span[normalize-space()='Sign in']";
    }
}
