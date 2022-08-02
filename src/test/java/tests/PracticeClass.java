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

    @Test(groups = {"t1"})
    public void t1() throws InterruptedException {
        d.navigate().to("https://amazon.in/");
        List<WebElement> myList = d.findElements(By.xpath("//a"));
        List<String> myStringlist = new ArrayList<>();
        for(WebElement i: myList){
            String temp= i.getText();
            if(!temp.isBlank()){
                myStringlist.add(temp);
            }
        }
        //because of set there are no duplicates
        Set<String>mySet = new HashSet<>(myStringlist);
        List<String> listAfterRemovingDuplicates = new ArrayList<>(mySet);
        Collections.sort(listAfterRemovingDuplicates);
        List<String> finalList=new ArrayList<>();
        for(String i:listAfterRemovingDuplicates){
            if((i.startsWith("C"))||(i.startsWith("D"))) finalList.add(i);
        }
        for(String i:finalList){
            System.out.println(i);
        }
    }
    @Test(groups = {"t3"})
    public void t3() throws InterruptedException {
        d.navigate().to("https://amazon.in/");
        d.findElements(By.xpath("//a"))
                .stream()
                .map(e->e.getText())
                .filter(i->!i.isBlank())
                .distinct()
                .sorted()
                .filter(e->e.startsWith("C")||e.startsWith("D"))
                .forEach(System.out::println);
    }
    @Test(groups = {"t222"})
    public void t2() throws InterruptedException {
        d.navigate().to("https://amazon.in/");
        List<WebElement> myList = d.findElements(By.xpath("//a"));
        Consumer<WebElement> myConsumer = (s)->{
            if(!s.getText().isBlank()){
                System.out.println(s.getText());
            }
        };
        myList.forEach(myConsumer);
    }
    @Test(groups = {"consumer"})
    public void consumerWithSelect() throws InterruptedException {
        d.navigate().to("https://demoqa.com/select-menu");
        gen.scrollBy();
        WebElement element = d.findElement(By.id("oldSelectMenu"));
//        Select select = new Select(element);
//        select.selectByVisibleText("Magenta");
//        select.selectByVisibleText("Magenta");
        gen.selectFromDropDown(e->e.selectByVisibleText("Magenta"),element);
        gen.selectFromDropDown(e->e.selectByVisibleText("Purple"),element);
        gen.selectFromDropDown(e->e.selectByVisibleText("Black"),element);
        gen.selectFromDropDown(e->e.selectByVisibleText("White"),element);
        gen.selectFromDropDown(e->e.selectByVisibleText("Voilet"),element);
        gen.selectFromDropDown(e->e.deselectByValue(String.valueOf(1)),element);
        Thread.sleep(5000);

    }

    @Test(groups = {"t22"})
    public void options() throws InterruptedException {
        d.navigate().to("https://demoqa.com/select-menu");
        gen.scrollBy(500);
        List<WebElement> options = new Select(d.findElement(By.id("cars"))).getOptions();
        options.forEach(e->{
            e.click();
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            e.click();
        });
        Thread.sleep(5000);
    }

//    public void goforit() throws Exception {
//        UtilityPageObjectClass up = new UtilityPageObjectClass(d);
//        d.navigate().to("https://rahulshettyacademy.com/locatorspractice/");
////        up.waitForPageToLoadCompletely();
////        up.waitForElementVisiblityByElement(By.cssSelector("label[for='chkboxOne']"));
//        String text = d.findElement(By.cssSelector("label[for='chkboxOne']")).getText();
//        System.out.println(text);
//        String text2 = (String) ((JavascriptExecutor) d).executeScript("let value =document.querySelectorAll('label[for=\"chkboxOne\"]')[0].innerText; return value;");
//        System.out.println(text2);
//        FluentWait wait =callWait(d);
////        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class=\"submit signInBtn\"]")));
////        d.findElement(By.cssSelector("button[class=\"submit signInBtn\"]")).click();
//        //d.findElement("")
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("visitUsTwo")));
//        d.findElement(By.id("visitUsTwo")).click();
//        Thread.sleep(10000);
//        ApiReponse.getResponse(d);
//    }
    @Test(groups = {"extracting"})
    public void extractFooterValues() throws InterruptedException {
        d.navigate().to("https://automationteststore.com/");
        String extractedValues =

         (String) ((JavascriptExecutor) d).executeScript("let extracted =document.querySelectorAll(\"div[id='block_frame_html_block_1776']\")[0].innerText; return extracted");
        String [] myArray = extractedValues.split("\\n");
        for(String i:myArray){
            System.out.println(i);
        }

    }

    @Test
    public void goForIt2() throws InterruptedException {
        d.navigate().to("https://rahulshettyacademy.com/locatorspractice/");
        WebElement passwordField = d.findElement(By.cssSelector("form[class=\"form\"] input:nth-of-type(2)"));
        d.findElement(with(By.tagName("input")).above(passwordField)).sendKeys("shit");
        Thread.sleep(10000);
    }
//    @Test(groups = {"t2"})
//    public void challenge() throws Exception {
//        test.goToPage("https://www.mediamarkt.hu/");
//        test.enterTextToConcreteSearchForm("Monitorok");
//        test.waitForPageToLoadCompletely();
//        test.findStartingElementAfterSomeOtherActionsOrOnNewPage(By.xpath("//b[text()=\"gamer monitor\"]"));
//
//
//
//    }

    @Test(groups = {"t22"})
    public void challenge1() throws Exception {
        d.navigate().to("https://www.spar.hu/onlineshop/search");
        //test.waitForPageToLoadCompletely();
//        test.waitForElementToBePresent(cookiePopupSpar);
        //test.clickOnAcceptCookie(cookiePopupSpar);
        //test.waitForAndClick(cookiePopupSpar);
        //test.waitForAndClick(welcomePostalCodeExit);

        d.switchTo().frame(0);
        test.waitForAndClick(cookiePopupSpar);
        d.switchTo().alert().accept();


//        test.enterTextToConcreteSearchForm("joghurt");
//        test.waitForPageToLoadCompletely();
//        test.findStartingElementAfterSomeOtherActionsOrOnNewPage(By.xpath("//b[text()=\"gamer monitor\"]"));
    }



    @Test(groups = {"t222"})
    public void challenge() throws Exception {
        d.navigate().to("https://amazon.com");
        test.waitForPageToLoadCompletely();
        test.clickOnAcceptCookie(cookiePopupSaturn);
        test.enterTextToSearchForm(searchFormSaturn,"monitor");
        test.waitForPageToLoadCompletely();
        List<WebElement> myElements = d.findElements(By.xpath("//div[@data-test=\"product-price\"]/descendant::span[8]"));
        List<Integer> Goods = new ArrayList<>();
        HashMap<Integer,Integer> myMap = new HashMap<>();
        int j=0;
        for(WebElement i:myElements){
            myMap.put(Integer.parseInt(i.getText()),j);
            //System.out.println(i.getText());
            System.out.println("itt vagyunk");
            j++;
        }
        Map sorted = myMap.entrySet().stream().sorted(comparingByKey()).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e1));
        sorted.forEach((k,v)-> System.out.println(k+" "+v+"kabbe"));

        Thread.sleep(5000);
    }



    @Test(groups = {"add_to_basket"})
    public void addItemsToBasketWithHighestPrice() throws Exception {
        d.navigate().to("https://saturn.de");
        test.waitForPageToLoadCompletely();
        test.clickOnAcceptCookie(cookiePopupSaturn);
        test.enterTextToSearchForm(searchFormSaturn,"monitor");
        test.waitForPageToLoadCompletely();
        List<WebElement> myElements = d.findElements(By.xpath("//div[@data-test=\"product-price\"]/descendant::span[8]"));
        HashMap<Double,Integer> myMap = new HashMap<>();

        int j=0;
        for(WebElement i:myElements){
            myMap.put(Double.parseDouble(i.getText()),j);
            j++;
        }
        Map sorted = myMap.entrySet()
                .stream()
                .sorted((i1, i2)
                        -> i2.getKey().compareTo(
                        i1.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        List<Integer> finalIndexList = new ArrayList<>();
        List<Double> finalPriceList = new ArrayList<>();
        int counter = 0;
        logger.debug("ordered list");
        sorted.forEach((k,v)->{
                finalIndexList.add((Integer) v);
                finalPriceList.add((Double) k);
                logger.debug("price "+k);

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

        int totalValueActual = (int)(Integer.parseInt(totalValueString[0]));
        double totalValueExpected = finalPriceList.get(0)+ finalPriceList.get(1);
        int finalExpected = (int)(Math.floor(totalValueExpected));
        logger.debug("expected "+finalExpected);
        logger.debug("actual "+totalValueActual);
        Assert.assertEquals(finalExpected,totalValueActual);
      }

    }

