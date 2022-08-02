package utilityClasses.testSetup.wrapperForSetupClasses;
import utilityClasses.testSetup.settingsLogger.SettingsLogger;


public class WrapperToCallSetupMethods {
    /**
     * @see #counter is static to make sure it is executed only once
     * also xalling diffeerent methods from one place
     */
    static int counter=0;
    public static void initializeAttributes() throws Exception {

        SettingsLogger.printOutSetupInfo(counter);
        SettingsLogger.setupDebuggingLevel(counter);
        counter++;
    }
}
