package org.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import static org.reports.ExtentManager.setExTest;


public class ExtentReport {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static void intiReport(){
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        spark.config().setDocumentTitle("RestAssured Report");
        spark.config().setReportName("Automation API Test Execution");
        extent.attachReporter(spark);
    }

    public static void createTest(String name){
        test = extent.createTest(name);
        setExTest(test);
    }

    public static void tearDownReport(){
        extent.flush();
    }

    public static void addAuthor(String[] authors){
        for (String author:authors){
            ExtentManager.getExTest().assignAuthor(author);
        }
    }

    public static void addCategory (String[] categories){
        for (String category:categories){
            ExtentManager.getExTest().assignCategory(category);
        }
    }
}