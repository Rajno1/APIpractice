package org.reportBuilder;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.util.Map;

import static org.reportBuilder.ExtentManager.setExTest;


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

    public static void addCategory(String[] categories){
        for (String category:categories){
            ExtentManager.getExTest().assignCategory(category);
        }
    }

    public static void logRequestHeaders(Map<String, Object> headers){
        if (headers == null || headers.isEmpty()) return;

        StringBuilder headerLog = new StringBuilder();
        headerLog.append("Request Headers:\n");
        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            headerLog.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        ExtentManager.getExTest().info("<pre>" + headerLog + "</pre>");
    }

    public static void logResponseHeaders(io.restassured.response.Response response) {
        if (response == null) return;
        StringBuilder headerLog = new StringBuilder();
        headerLog.append("Response Headers:\n");
        for (io.restassured.http.Header header : response.getHeaders()) {
            headerLog.append(header.getName()).append(": ").append(header.getValue()).append("\n");
        }
        ExtentManager.getExTest().info("<pre>" + headerLog + "</pre>");
    }
}