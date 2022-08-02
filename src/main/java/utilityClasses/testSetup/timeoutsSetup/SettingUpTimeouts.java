package utilityClasses.testSetup.timeoutsSetup;
import utilityClasses.testSetup.GlobalSettingsGetterMethods;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
/**
 * @see #timeOutSetup(WebDriver d)
 * default timeouts should be set here
 */
public class SettingUpTimeouts {
    public static void timeOutSetup(WebDriver d){
        try{
            d.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalSettingsGetterMethods.getwaitForTime_static()));
            d.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(GlobalSettingsGetterMethods.getwaitForTime_static()));
            d.manage().timeouts().scriptTimeout(Duration.ofSeconds(GlobalSettingsGetterMethods.getwaitForTime_static()));
        }catch (Exception e){
            e.printStackTrace();
            throw (e);
        }
    }
}
