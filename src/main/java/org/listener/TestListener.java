package org.listener;

import org.annotations.FrameworkAnnotation;
import org.reportBuilder.ExtentLogger;
import org.reportBuilder.ExtentReport;
import org.testng.ISuite;

import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.utils.JiraUtils;

public class TestListener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) { ExtentReport.intiReport(); /* Initiating Extent report*/ }

    @Override
    public void onFinish(ISuite suite) { ExtentReport.tearDownReport(); }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();

        // Create test in ExtentReport with description if available
        if (description != null && !description.isEmpty()) {
            ExtentReport.createTest(testName + " - " + description);
        } else {
            ExtentReport.createTest(testName);
        }

        FrameworkAnnotation annotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class);

        if (annotation == null) {
            annotation = result.getTestClass().getRealClass().getAnnotation(FrameworkAnnotation.class);
        }

        if (annotation != null) {
            ExtentReport.addAuthor(annotation.author());
            ExtentReport.addCategory(annotation.category());
        }    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getName()+ " is passed ");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(String.valueOf(result.getThrowable()));

        String summary = "Automation Bug: " + result.getName() + " failed";
        String description = "Test method: " + result.getName() + "\n"
                + "Error: " + (result.getThrowable() != null ? result.getThrowable().toString() : "No exception info");

        try {
            JiraUtils.createBug(summary, description);
        } catch (Exception e) {
            System.err.println("❌ Failed to create JIRA bug: " + e.getMessage());
            e.printStackTrace();
        }
         }


}
