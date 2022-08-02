package utilityClasses.testSetup.deviceSetup;

import org.openqa.selenium.WebDriver;

public interface DeviceSetupBase {

    public  <T> T settipUpDevices(String browser)throws Exception;
    public <T> T driverServiceFactory()throws Exception;
    public <T> T driverBuilder(String browser)throws Exception;
}
