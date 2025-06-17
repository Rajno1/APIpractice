package org.reports;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public final class ExtentLogger {
    private ExtentLogger() {
    }

    public static void pass(String messsge){
        ExtentManager.getExTest().pass(messsge);
    }


    public static void fail(String messsge){
        ExtentManager.getExTest().fail(MarkupHelper.createLabel(messsge, ExtentColor.RED));
    }

    public static void info(String messsge){
        ExtentManager.getExTest().info(messsge);
    }

    public static void logResponse(String messsge){
        ExtentManager.getExTest().pass(MarkupHelper.createCodeBlock(messsge, CodeLanguage.JSON));
    }


}
