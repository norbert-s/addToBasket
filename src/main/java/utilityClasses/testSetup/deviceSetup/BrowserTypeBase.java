package utilityClasses.testSetup.deviceSetup;

import org.openqa.selenium.WebDriver;

public interface  BrowserTypeBase {
    public WebDriver browserSelection(String browser)throws Exception;
}
