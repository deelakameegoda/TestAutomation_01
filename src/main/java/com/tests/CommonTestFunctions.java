package com.tests;

import com.codebase.TestBase;

public class CommonTestFunctions extends TestBase {

    public static void handleCheckBox(String xpath, boolean isCheck){
        if (checkIsSelected(xpath)){
            if (!isCheck){
               click(xpath);
            }
        }else{
            if (isCheck){
                click(xpath);
            }
        }
    }
}
