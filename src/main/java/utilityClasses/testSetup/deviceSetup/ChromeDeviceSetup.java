package utilityClasses.testSetup.deviceSetup;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


import utilityClasses.testSetup.GlobalSettingsGetterMethods;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChromeDeviceSetup implements DeviceSetupBase {
    public static ChromeDriverService service;
    private static ChromeOptions chromeOptions;

    @Override
    public  ChromeOptions settipUpDevices(String browser) throws Exception {
        chromeOptions =new ChromeOptions();


            if (browser.toLowerCase().contains("chrome".toLowerCase(Locale.ROOT))) {


                //chromeOptions.addArguments("--window-size=1920,1080");
//                    if(GlobalSettingsGetterMethods.isXNoCacheNeeded()){
//                        chromeOptions.addArguments("--disable-web-security");
//                    }
                //chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.setHeadless(GlobalSettingsGetterMethods.getHeadless());
            }
            if (browser.contains("ipad")) {
                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", "iPad Pro");

                HashMap<String, Object> prefs = new HashMap<>();
                chromeOptions.addArguments("--incognito");
                if (GlobalSettingsGetterMethods.isXNoCacheNeeded()) {
                    chromeOptions.addArguments("--disable-web-security");
                }
                chromeOptions.setExperimentalOption("prefs", prefs);
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                chromeOptions.setHeadless(GlobalSettingsGetterMethods.getHeadless());

            }

        return chromeOptions;
    }

    @Override
    public ChromeDriverService driverServiceFactory() throws Exception {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:\\Users\\norbert.susztek\\OneDrive - Accenture\\binaries\\chromedriver\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        return service;
    }

    @Override
    public WebDriver driverBuilder(String browser) throws Exception {

        chromeOptions = settipUpDevices(browser);
        service = driverServiceFactory();
        service.start();
        return new RemoteWebDriver(service.getUrl(), chromeOptions);
    }
}

