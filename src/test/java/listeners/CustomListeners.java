package listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.mail.MessagingException;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import base.Page;
import utilities.MonitoringMail;
import utilities.TestConfig;
import utilities.Utilities;

public class CustomListeners extends Page implements ITestListener,ISuiteListener {
    public String messageBody = null;
    public void onTestStart(ITestResult result) {
        test = report.startTest(result.getName());

    }

    public void onTestSuccess(ITestResult result) {

        test.log(LogStatus.PASS, result.getName().toUpperCase()+ " -->  Pass");
        //test.log(LogStatus.PASS, test.addScreenCapture(Utilities.screenshotName));
        report.endTest(test);
        report.flush();
    }

    public void onTestFailure(ITestResult result) {

        System.setProperty("org.uncommons.reportng.escape-output", "false");
        try {
            Utilities.captureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        test.log(LogStatus.FAIL, result.getName().toUpperCase()+ " -->  Failed with exception: " + result.getThrowable());
        test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));
        Reporter.log("Click to see screenshot");
        Reporter.log("Login successfully executed");
        Reporter.log("<a target=\"blank\" href=" + Utilities.screenshotName+">" + "Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href="+ Utilities.screenshotName+"><img src="+ Utilities.screenshotName+" height=200 width=200></img></a>");
        report.endTest(test);
        report.flush();
    }

    public void onTestSkipped(ITestResult result) {
        test.log(LogStatus.SKIP, result.getName().toUpperCase()+" Skipped the test as the Run mode is NO");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {
        //test = report.startTest(context.getName().toUpperCase());
    }

    public void onFinish(ITestContext context) {
        MonitoringMail mail = new MonitoringMail();

        try {
            messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
                    + ":8080/job/LivePageObjectModel/Extent_20Report/";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(messageBody);

        try {
            mail.sendMail(TestConfig.from, TestConfig.from, TestConfig.password, TestConfig.to, TestConfig.subject, messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void onStart(ISuite iSuite) {

    }

    public void onFinish(ISuite iSuite) {

    }
}