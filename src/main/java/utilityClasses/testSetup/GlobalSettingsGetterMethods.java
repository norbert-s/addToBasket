package utilityClasses.testSetup;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

    /**
     * @see GlobalSettingsGetterMethods interface
     * it is responsible for values to be used all accross test cases
     * what kind of values ?
     * @see @waitfortime, testablebranch, environment, headless, debugging level, retrynumberof times, etc
     * above values can come in two different forms
     * 1. command line arguments
     * 2. values from other sources (googlesheet)
     * in case they come as command line arguments then they must be declared as such in 2 places
     * a. pom.xml as a property
     * b. then they are taken by below functions by System.getproperty("nameOfProperty")
     * for instance argument of -Dheadless=true in the command line will be used only after it is referenced in those 2 places mentioned above
     *
     * Also all of these settings definitions have default values
     *
     * @also this interface declares default implementations of the contract in an interface
     * these implementations are then used either as
     * 1. interface implementation ( with keyword "implemented" by classes) OR
     * 2. by calling the static methods of "this" class
     * for instance GlobalSettingsGetterMethods.getBranchId()
     */
public interface GlobalSettingsGetterMethods {
    static String getBranchId(){
        if(System.getProperty("branch_id")==null) return "master";
        else return System.getProperty("branch_id");
    }

    /**
     * @see #getEmailTo() ()
     * email address
     */
    static String getEmailTo() {
        return System.getProperty("emailto");
    }

    /**
     * @see #getwaitForTime()
     * @see #getwaitForTime_static()
     * there will be default and static implementations to be found of these two
     * the reason for that is because a non-static method cannot be called from a static context (it can be the other way around)
     * and there are classes that will use these values which has static methods for easy access
     *
     * "default" access modifier-> new in java 8
     * it means an interface might have a default implementation of a method
     * previously it could not have a default implementation
     *
     */
    default int getwaitForTime() {
        if(System.getProperty("waitfortime")==null) return 30;
        else return Integer.parseInt(System.getProperty("waitfortime"));
    }

    static int getwaitForTime_static() {
        if(System.getProperty("waitfortime")==null) return 30;
        else return Integer.parseInt(System.getProperty("waitfortime"));
    }

    /**
     * @see #getEnvironment()
     * @see #getEnvironment_static()
     * it is the same with environment methods as with getWaitForTime()
     * there will be static and non-static versions
     */
    static String getEnvironment(){
        return System.getProperty("practiceenvironment");
    }
    static String getEnvironment_static(){
        return System.getProperty("practiceenvironment");
    }

    /**
     * @see #getHeadless()
     * if default value is not provided as an environment variable then the default value is going to be headless=true
     * however it can be provided as -Dheadless=false/true as command line argument
     * and if it is provided as such then that value will be taken into account
     */
    static boolean getHeadless() {
        try{
            if (System.getProperty("headless") == null||System.getProperty("headless").contains("true")) return true;
            else if(System.getProperty("headless").contains("false")){
                return false;
            }
            else{
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }

    /**
     * @see #setLogLevelBasedOnEnv()
     * the debugging level will be simply overwritten based on the value provided
     * if no value is provided then ERROR level logging will be effective
     */
    static void setLogLevelBasedOnEnv(){
        try{
            String debugLevel = System.getProperty("debuglevel");
            if(System.getProperty("debuglevel")==null)  Configurator.setRootLevel(Level.ERROR);
            else if(debugLevel.contains("debug")){
                Configurator.setRootLevel(Level.DEBUG);
            }
            else if(debugLevel.contains("warn")){
                Configurator.setRootLevel(Level.WARN);
            }
            else if(debugLevel.contains("error")){
                Configurator.setRootLevel(Level.ERROR);
            }
            else{
                Configurator.setRootLevel(Level.ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }

    /**
     * @see #setRetryNumberOfTimes()
     * if no value is provided as command line argument or enviroment variable
     * then retry will be set to 2
     * otherwise - when a value is set then it will be taken - however the maximum value applied will be 3
     * @see @RetryFailedAnalyzer.class
     */
    static int setRetryNumberOfTimes(){
        try{
            if(System.getProperty("retryfailed")==null){
                return 2;
            }else if(Integer.parseInt(System.getProperty("retryfailed"))>=0&&Integer.parseInt(System.getProperty("retryfailed"))<=3){
                return Integer.parseInt(System.getProperty("retryfailed"));
            }
            else{
                return 2;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }

    /**
     * @see #isXNoCacheNeeded()
     * x-no-cache is going to be taken into account only when the branch is "dns" ( the environment can be prod/preprod/dev)
     */
    static boolean isXNoCacheNeeded(){
        try{
            return getBranchId().contains("dns");
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }
        /**
         * @see #isItFrontEndPipeLine()
         * if it is the frontend pipeline ( it should be true only when the run is triggered from the frontend pipeline
         * and it will be using another set of email/hashtag combination as per URLGetterForDataProviders class
        */
    static boolean isItFrontEndPipeLine(){
        try{
            if(System.getProperty("isitfrontend")==null){
                return false;
            }
            else if(System.getProperty("isitfrontend").contains("true")){
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }
    /**
     * @see  #getDebugLevel()
     * getter of debug level, it is used in the setup logger
    */
    static String getDebugLevel(){
        String debugLevel = System.getProperty("debuglevel");
        if(System.getProperty("debuglevel")==null)  return "error";
        else if(debugLevel.contains("debug")){
            return "debug";
        }
        else if(debugLevel.contains("warn")){
            return "warn";
        }
        else if(debugLevel.contains("error")){
            return "error";
        }
        else{
            return "error";
        }
    }
        /**
         * @see  #isIncognitoNeeded()
         * by default incognito is used
         */
    static boolean isIncognitoNeeded(){
        if(System.getProperty("isincognitoneeded")==null||System.getProperty("isincognitoneeded").equals("true")) return true;
        else {
            return false;
        }
    }

}