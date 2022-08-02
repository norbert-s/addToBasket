package listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import tests.PracticeClass;
import utilityClasses.testSetup.GlobalSettingsGetterMethods;


public class RetryFailedTests implements GlobalSettingsGetterMethods, IRetryAnalyzer {
    private int count = 0;
    private static int maxTry;
    private static final Logger logger = (Logger) LogManager.getLogger(PracticeClass.class);

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(GlobalSettingsGetterMethods.setRetryNumberOfTimes()<0){
            maxTry=0;
        }
        if(GlobalSettingsGetterMethods.setRetryNumberOfTimes()>3){
            maxTry=3;
        }
        else{
            maxTry= GlobalSettingsGetterMethods.setRetryNumberOfTimes();
        }
        if (!iTestResult.isSuccess()) {
            //Check if test not succeed
            if (count < maxTry) {
                //Check if maxtry count is reached
                count++;
                logger.warn("retrying test case the "+count+"th time");//Increase the maxTry count by 1
                iTestResult.setStatus(ITestResult.FAILURE);
                //Mark test as failed
                return true;                                 //Tells TestNG to re-run the test
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }
}


