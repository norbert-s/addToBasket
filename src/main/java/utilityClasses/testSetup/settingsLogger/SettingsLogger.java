package utilityClasses.testSetup.settingsLogger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import utilityClasses.testSetup.GlobalSettingsGetterMethods;
/**
 * @see #logger is set
 * @see #printOutSetupInfo(int)
 * all the information we want to print out at this stage can be printed from here
 * we use "GlobalSettingsGetterMethods." with static methods to print the values
 */
public class SettingsLogger {
    private static final org.apache.logging.log4j.core.Logger logger = (Logger) LogManager.getLogger(SettingsLogger.class);
    public static void printOutSetupInfo(int counter){
        try{
            if(counter==0){
                logger.warn("webdriver instance has been created");
                logger.warn("environment -> "+ GlobalSettingsGetterMethods.getEnvironment());
                logger.warn("headless -> "+ GlobalSettingsGetterMethods.getHeadless());
                logger.warn("debug level -> "+GlobalSettingsGetterMethods.getDebugLevel());
                logger.warn("waitforTime -> " + GlobalSettingsGetterMethods.getwaitForTime_static());
                logger.warn("xnocache -> " + GlobalSettingsGetterMethods.isXNoCacheNeeded());
                logger.warn("retry -> " + GlobalSettingsGetterMethods.setRetryNumberOfTimes());
                logger.warn("testable branch -> " + GlobalSettingsGetterMethods.getBranchId());
                logger.warn("is it front end pipeline -> " + GlobalSettingsGetterMethods.isItFrontEndPipeLine());
                logger.warn("is incognito needed -> "+GlobalSettingsGetterMethods.isIncognitoNeeded());
            }
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }
    /**
     * @see #setupDebuggingLevel(int)
     * it is setup only once
     * every time when the main class of test is instantiated it is set as well
     * "GlobalSettingsGetterMethods." is used
     */
    public static void setupDebuggingLevel(int counter){
        if(counter==0) GlobalSettingsGetterMethods.setLogLevelBasedOnEnv();
    }
}
