package internal.rejon.tealiumdemo.TestUtils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.testng.log4testng.Logger.getLogger;

public class TestListener implements ITestListener {
    static final Logger log = getLogger(lookup().lookupClass());

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        log.debug("onTestFailure {}", result.getThrowable());
    }
}
