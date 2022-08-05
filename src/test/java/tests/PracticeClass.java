package tests;


import listeners.ParentForTestListener;
import listeners.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import pageObjectClasses.utilityMethods.ConcreteTestMethodsOfChallenge;
import pageObjectClasses.utilityMethods.GenericMethods;
import pageObjectClasses.utilityMethods.LocatorsOfChallengeProject;
import pageObjectClasses.utilityMethods.UtilityMethods;
import utilityClasses.testSetup.deviceSetup.BrowserType;
import utilityClasses.testSetup.deviceSetup.ChromeDeviceSetup;
import utilityClasses.testSetup.wrapperForSetupClasses.WrapperToCallSetupMethods;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;


import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

@Listeners({TestListener.class})
public class PracticeClass implements ParentForTestListener,BrowserType, LocatorsOfChallengeProject {
    private static final Logger logger = (Logger) LogManager.getLogger(PracticeClass.class);

    public WebDriver d;
    GenericMethods gen;
    ConcreteTestMethodsOfChallenge test;
    @BeforeSuite(alwaysRun = true)
    public void settingUpDefaults() throws Exception {
        WrapperToCallSetupMethods.initializeAttributes();
    }

    @Parameters({"browser-name"})
    @BeforeSuite(alwaysRun = true)
    public void setup(@Optional("optional value")String browser) throws Exception {
        System.out.println(browser);
        d =browserSelection(browser);
        gen=new GenericMethods(d);
        test=new ConcreteTestMethodsOfChallenge(d);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        d.quit();
    }
    @AfterSuite()
    public void tearDownFinal() {
       ChromeDeviceSetup.service.stop();
    }
    

    @Test(groups = {"add_to_basket"})
    public void addItemsToBasketWithHighestPrice() throws Exception {
        d.navigate().to("https://saturn.de");
        test.waitForPageToLoadCompletely();
        test.clickOnAcceptCookie(cookiePopupSaturn);
        test.enterTextToSearchForm(searchFormSaturn,"monitor");
        test.waitForPageToLoadCompletely();

        List<WebElement> priceElements = d.findElements(By.xpath("//div[@data-test=\"product-price\"]/descendant::span[8]"));
        HashMap<Double,Integer>  mapWithWebelements = UtilityMethods.addValuesAndIndexesToMap(priceElements);
        HashMap<Double,Integer>  sorted = UtilityMethods.sortingMap(mapWithWebelements);

        List<Integer> finalIndexList = new ArrayList<>();
        List<Double> finalPriceList = new ArrayList<>();
        int counter = 0;
        logger.debug("ordered list");
        sorted.forEach((k,v)->{
                finalIndexList.add(v);
                finalPriceList.add(k);
                logger.debug("price:"+k+", index:"+v);
        });
        //selecting the first highest price index and going back by xpath to that element in the dom

        int limit = 1;
        for(Integer i: finalIndexList) {
            if(counter<=limit){
                test.waitForPageToLoadCompletely();
                test.scrollBy(i*100);
                d.findElements( By.xpath("//div[@data-test='product-price']/descendant::span[8]/ancestor::div[@data-test='mms-search-srp-productlist-item']")).get(i).click();
                test.waitForAndClick(addToBasket);
                test.waitForAndClick(closeAddToBasketModal);
                if(counter<limit){
                    d.navigate().back();
                }
                if(counter==limit){
                    d.findElement(By.cssSelector("button[data-test=\"mms-primary-modal-footer-buttons\"]")).click();
                }
                counter++;
            }
        }
        String [] totalValueString = d.findElement(checkOutTotal).getText().split(",");
        String decimalSplit []= totalValueString[1].split(" ");
        double decimal = Double.parseDouble("0."+decimalSplit[0]);
        double totalValueActual = (int)(Integer.parseInt(totalValueString[0]))+decimal;
        double totalValueExpected = finalPriceList.get(0)+ finalPriceList.get(1);
        double finalExpected = totalValueExpected;
        logger.debug("Total price expected to be:"+finalExpected);
        logger.debug("actual total price:"+totalValueActual);
        //taking screenshot of thepage of the total added to basket if successful
        Assert.assertEquals(finalExpected,totalValueActual);
      }
    }

