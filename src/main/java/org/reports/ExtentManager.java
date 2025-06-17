package org.reports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public final class ExtentManager {

    private ExtentManager() {
    }



    private static ThreadLocal<ExtentTest> exTest = new ThreadLocal<>();

    static ExtentTest getExTest() {
        return exTest.get();
    }

    static void setExTest(ExtentTest test) {
        exTest.set(test);
    }

}
