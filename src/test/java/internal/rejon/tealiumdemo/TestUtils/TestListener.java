package internal.rejon.tealiumdemo.TestUtils;

import internal.rejon.tealiumdemo.SeleniumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import java.io.File;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.invoke.MethodHandles.lookup;
import static org.testng.log4testng.Logger.getLogger;

public class TestListener implements ITestListener {
    static final Logger log = getLogger(lookup().lookupClass());

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        String failureTime = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
        String testName = result.getMethod().getMethodName();
        String fileName = new StringBuilder(testName).append('-').append(failureTime).toString();
        File srcFile = ((TakesScreenshot) SeleniumDriver.driver).getScreenshotAs(OutputType.FILE);
        File dstFile = new File(fileName);
        try{
            FileHandler.copy(srcFile, dstFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug("onTestFailure {}", result.getThrowable());
    }
}
