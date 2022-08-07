package pageObjectClasses.utilityMethods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pageObjectClasses.implementableMethods.SelectFromDropDown;
import utilityClasses.testSetup.GlobalSettingsGetterMethods;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class GenericMethods implements GlobalSettingsGetterMethods, SelectFromDropDown, LocatorsOfChallengeProject {
    private static final Logger logger = (Logger) LogManager.getLogger(GenericMethods.class);
    WebDriver d;

    public GenericMethods(WebDriver d) {
        this.d = d;
        PageFactory.initElements(d, this);
    }

    /**
     * @see #waitForElementToBeClickable(WebElement)
     * custom method using fluent wait to wait for element to be clickable
     */
    public void waitForElementToBeClickable(WebElement element){
        try{
            FluentWait wait = new FluentWait(d)
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(NoSuchElementException.class)
                    .withTimeout(Duration.ofSeconds(getwaitForTime()));
            wait.until((Function) ExpectedConditions.elementToBeClickable(element));
        }catch(Exception e){
            logger.error("wait for element to be clickable did not succeed "+element + " ");
            throw (e);
        }
    }
    /**
     * @see #waitForInvisibility(WebElement)
     * custom method using fluent wait to wait for element to be invisible
     * this method is important when we wait for for instance deletion button to disappear
     * because after it has disappeared we will only wait 100 milliseconds to find out that it is not there
     */
    public void waitForInvisibility(WebElement element){
        try{
            FluentWait wait = new FluentWait(d)
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(NoSuchElementException.class)
                    .withTimeout(Duration.ofSeconds(getwaitForTime()));
            wait.until((Function) ExpectedConditions.invisibilityOf(element));
        }catch(Exception e){
            logger.error("waiting for invisiblity of element did not succeed "+element + " ");
            throw (e);
        }
    }

    public void waitForElementToBeClickable(By element){
        try{
            FluentWait wait = new FluentWait(d)
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(NoSuchElementException.class)
                    .withTimeout(Duration.ofSeconds(getwaitForTime()));
            wait.until((Function) ExpectedConditions.elementToBeClickable(element));
        }catch(Exception e){
            logger.error("waiting for element to be clickable  did not succeed "+element + " ");
            throw (e);
        }
    }

    public void waitForElementToBePresent(By element){
        try{
            FluentWait wait = new FluentWait(d)
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(NoSuchElementException.class)
                    .withTimeout(Duration.ofSeconds(getwaitForTime()));
            wait.until((Function) ExpectedConditions.presenceOfElementLocated(element));
        }catch(Exception e){
            logger.error("waiting for element to be present  did not succeed "+element + " ");
            throw (e);
        }
    }

    public void waitForElementVisiblityWebelement(WebElement element){
        try{
            FluentWait wait = new FluentWait(d)
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(NoSuchElementException.class)
                    .withTimeout(Duration.ofSeconds(getwaitForTime()));
            wait.until((Function) ExpectedConditions.visibilityOf(element));
        }catch(Exception e){
            logger.error("waiting for element to be visible  did not succeed "+element);
            throw(e);
        }
    }

    public void waitForElementVisiblityByElement(By element){
        try{
            FluentWait wait = new FluentWait(d)
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(NoSuchElementException.class)
                    .withTimeout(Duration.ofSeconds(getwaitForTime()));
            wait.until((Function) ExpectedConditions.visibilityOfElementLocated(element));
        }catch(Exception e){
            logger.error("waiting for element to be visible  did not succeed "+element + " ");
            throw(e);
        }
    }

    /**
     * @see #waitForAndClick(By)
     * custom method waitd for element
     * then asserts its displayed
     * and then clicks on the element
     */
    public void waitForAndClick(By element) throws Exception {
        try{
            FluentWait wait = new FluentWait(d)
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(NoSuchElementException.class)
                    .withTimeout(Duration.ofSeconds(getwaitForTime()));
            wait.until((Function) ExpectedConditions.elementToBeClickable(d.findElement(element)));
            Assert.assertTrue(d.findElement(element).isDisplayed());
            d.findElement(element).click();
        }catch (NoSuchElementException e) {
            logger.error("waiting for element and clickon it  did not succeed "+element + " ");
            throw(e);
        }
    }

    /**
     * @see #waitForAndClick(WebElement)
     * sometimes elements cannot be found by "By elementfactory"
     * custom method waitd for element
     * then asserts its displayed
     * and then clicks on the element
     */
    public void waitForAndClick(WebElement element) throws Exception {
        try{
            FluentWait wait = new FluentWait(d)
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(NoSuchElementException.class)
                    .withTimeout(Duration.ofSeconds(getwaitForTime()));
            wait.until((Function) ExpectedConditions.elementToBeClickable(element));
            element.click();
        }catch (NoSuchElementException e) {
            logger.error("waiting for element and clickon it  did not succeed "+element + " ");
            throw(e);
        }
    }
    /**
     * @see #enterTextToSearchForm(By, String)
     * this method should be used when the input field does not contain dropdowns or autocompletion
     * for instance when entering email address to an input field
     */
    public void enterTextToSearchForm(By element,String text) throws Exception {
        try{
//            waitForElementToBePresent(element);
//            waitForAndClick(element);
            clearText(element);
//            waitForElementToBePresent(element);
            d.findElement(element).sendKeys(text);
            d.findElement(element).sendKeys(Keys.RETURN);
        }catch(Exception e){
            logger.error("entering text into search form  did not succeed "+element+" "+text + " ");
            throw(e);
        }
    }

    public void clearText(By element){
        try{
            waitForElementToBeClickable(element);
            d.findElement(element).clear();
        }catch(Exception e){
            logger.error("clearing text from form  did not succeed "+element + " ");
            throw(e);
        }
    }

    /**
     * @see #scrollBy()
     * this method scrolls down on the page by 150 pixels
     * or an amount wanted
     */
    public void scrollBy(int numberOfPixels){
        try{
            JavascriptExecutor js = (JavascriptExecutor) d;
            String scrollByScript = "window.scrollBy(0,"+numberOfPixels+")";
            js.executeScript(scrollByScript, "");
        }catch(Exception e){
            logger.error("scrolling down  did not succeed ");
            throw(e);
        }
    }
    public void scrollBy(){
        int numberOfPixels = 150;
        try{
            JavascriptExecutor js = (JavascriptExecutor) d;
            String scrollByScript = "window.scrollBy(0,"+numberOfPixels+")";
            js.executeScript(scrollByScript, "");
        }catch(Exception e){
            logger.error("scrolling down  did not succeed ");
            throw(e);
        }
    }

    @Override
    public  void selectFromDropDown(Consumer<Select> consumer, WebElement element) {
        consumer.accept(new Select(element));
    }

    /**
     * @see #goToPage(String)
     * navigates to the given url
     * waits for the page to load completely
     * accepts the cookie policy
     * asserts that the url is correct
     */
    public void goToPage(String url) {
        try{
            d.navigate().to(url);
            waitForPageLoadCompletionAndAcceptCookie();
            String actualUrl = d.getCurrentUrl();
            Assert.assertEquals(actualUrl,url);
            logger.debug("pageload check is done");
        }catch(Exception e){
            logger.error("navigation to "+url + " did not succeed " );
            throw (e);
        }
    }
    public void waitForPageToLoadCompletely( ) {
        try{
            FluentWait wait=returnWait();
            wait.until(driver_here -> String
                    .valueOf(((JavascriptExecutor) driver_here).executeScript("return document.readyState"))
                    .equals("complete"));
        }catch(Exception e){
            logger.error("wait for page to complete - did not succeed " );
            throw (e);
        }
    }
    /**
     * @see #returnWait()
     * generic method to return Fluentwait
     * if other than NoSuchElementException.class should be ignored, then another one should be used
     * .ignoring(NoSuchElementException.class)
     */
    public FluentWait returnWait(){
        FluentWait wait = new FluentWait(d)
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(NoSuchElementException.class)
                .withTimeout(Duration.ofSeconds(getwaitForTime()));
        return wait;
    }
    public void waitForPageLoadCompletionAndAcceptCookie(){
        try{
            waitForPageToLoadCompletely();
            clickOnAcceptCookie();
        }catch (Exception e){
            logger.error("wait for page and accept cookie did no succeed ");
            throw(e);
        }
    }
    public void clickOnAcceptCookie(){
        try{
            d.manage().timeouts().implicitlyWait(Duration.ofMillis(50));
            List<WebElement> cookieButton = d.findElements(cookiePopupSpar);
            if (cookieButton.size()>0) {
                cookieButton.get(0).click();
            }
            d.manage().timeouts().implicitlyWait(Duration.ofSeconds(getwaitForTime()));
        }catch(Exception e){
            logger.error("clicking on cookie did not succeed ");
            throw (e);
        }
    }
    public void clickOnAcceptCookie(By element){
        try{
            d.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
            List<WebElement> cookieButton = d.findElements(element);
            System.out.println("size "+cookieButton.size());
            if (cookieButton.size()>0) {
                cookieButton.get(0).click();
            }
            d.manage().timeouts().implicitlyWait(Duration.ofSeconds(getwaitForTime()));
        }catch(Exception e){
            logger.error("clicking on cookie did not succeed ");
            throw (e);
        }
    }
    public void clickOnNthElementInList(By element,int index) throws Exception {
        waitForAndClick(d.findElements(element).get(index));
    }

    public void addItemsToBasket(List<Integer>finalIndexList) throws Exception {
        //selecting the first highest price index and going back by xpath to that element in the dom
        int counter = 0;
        int limit = 1;
        for(Integer i: finalIndexList) {
            if(counter<=limit){
                waitForPageToLoadCompletely();
                scrollBy(i*100);

                d.findElements( itemClickablePart).get(i).click();
                waitForAndClick(addToBasket);
                waitForAndClick(closeAddToBasketModal);
                if(counter<limit){
                    d.navigate().back();
                }
                if(counter==limit){
                    d.findElement(basketSaturn).click();
                }
                counter++;
            }
        }
    }
}
