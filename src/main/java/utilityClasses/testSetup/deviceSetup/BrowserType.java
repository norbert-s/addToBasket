package utilityClasses.testSetup.deviceSetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public interface BrowserType extends BrowserTypeBase {
    public default WebDriver browserSelection(String browser) throws Exception {
        if(browser.equalsIgnoreCase("chrome")){
           DeviceSetupBase chromeDeviceSetup=new ChromeDeviceSetup();
           return chromeDeviceSetup.driverBuilder(browser);
        }
        else if (browser.equalsIgnoreCase("firefox")){
            return new FirefoxDriver();
        }
        else return null;
    }
}
