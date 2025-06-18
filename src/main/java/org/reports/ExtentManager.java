package org.reports;

import com.aventstack.extentreports.ExtentTest;


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