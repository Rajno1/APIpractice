package org.listener;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.reports.ExtentLogger;
import org.reports.ExtentReport;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener, ISuiteListener {

    public void onStart(ISuite suite) { ExtentReport.intiReport(); /* Initiating Extent report*/ }

    public void onFinish(ISuite suite) { ExtentReport.tearDownReport(); }

    public void onTestStart(ITestResult result) { ExtentReport.createTest(result.getName()); }

    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getName()+ " is passed ");
    }

    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(String.valueOf(result.getThrowable()));
         }

}
