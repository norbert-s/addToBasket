package listeners;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.*;
import org.testng.*;

import tests.PracticeClass;
import utilityClasses.date.DateTimeStampGetter;


import java.io.File;
/**
 * @see ITestListener
 * @see "when implementing ITestListener it comes with different methods to be implemented
 * those methods may or may not be overwritten
 * those methods are default interface methods
 * but they can be overwritten
 * in case of failure a screenshot is taken here
 * otherwise the result is logged
 */
public class TestListener implements ITestListener {
    private static final org.apache.logging.log4j.core.Logger logger = (Logger) LogManager.getLogger(TestListener.class);
    public WebDriver d;

    String location = "logFiles/screenshots/";
    @Override
    public void onStart(ITestContext context) {
        //logger.debug("onStart method started "+context.getName());
    }
    @Override
    public void onFinish(ITestContext context) {

    }
    @Override
    public void onTestStart(ITestResult result) {
//        ITestContext context = result.getTestContext();
//        String suite = context.getSuite().getName();
//        logger.debug(suite+" "+result.getName()+"------New Test Started------");
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        ITestContext context = result.getTestContext();
        String suite = context.getSuite().getName();
        this.d = ((PracticeClass)result.getInstance()).d;
        try {
            File screenshots = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
            logger.info(suite+" "+result.getName()+"------Test finished with Success------");
            FileUtils.copyFile(screenshots,new File(location +"passed"+ DateTimeStampGetter.getDateTime()+suite+"_"+  result.getName()+ ".png"));
        } catch (Throwable e) {
            logger.info(suite+" "+result.getName()+"taking a screenshot did not succeed");
        }
    }
    @Override
    public void onTestFailure(ITestResult result) throws TimeoutException {
        ITestContext context = result.getTestContext();
        String suite = context.getSuite().getName();
        this.d = ((PracticeClass)result.getInstance()).d;
        try {
            File screenshots = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
            logger.error(suite+" "+result.getName()+"------Test finished with FAILURE------");
            FileUtils.copyFile(screenshots,new File(location +"failed"+ DateTimeStampGetter.getDateTime()+suite+"_"+  result.getName()+ ".png"));

        } catch (Throwable e) {
            logger.error(suite+" "+result.getName()+"taking a screenshot did not succeed because page did not load up");

        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        try{
            ITestContext context = result.getTestContext();
            String suite = context.getSuite().getName();
            logger.debug(suite+" "+result.getName()+" test has been SKIPPED " );
        }catch(Exception e){

        }
    }

}
