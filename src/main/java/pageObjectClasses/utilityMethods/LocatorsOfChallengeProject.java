package pageObjectClasses.utilityMethods;

import org.openqa.selenium.By;

public interface LocatorsOfChallengeProject {
    public By cookiePopupMediaMarkt= By.xpath("//span[text()=\"Elfogad\"]");
//    public By cookiePopupSpar= By.id("cmpbntyestxt");
    public By cookiePopupSpar= By.xpath("span[text()=\"Minden cookie engedélyezése\"]");
    public By searchFormMediaMarkt = By.cssSelector("input[data-identifier=\"search-input-searchterm\"]");
    public By welcomePostalCodeExit = By.cssSelector("button[tabindex=\"1\"]");



    public By cookiePopupSaturn = By.id("pwa-consent-layer-accept-all-button");
    public By searchFormSaturn = By.cssSelector("input[aria-label=\"Was suchen Sie?\"]");

    //public By basket = By.cssSelector("a[aria-label=\"Warenkorb\"]");
    public By basket = By.xpath("//a[@aria-label=\"Warenkorb\"]/descendant::div");

    public By checkOutTotal = By.cssSelector("h3[data-test=\"checkout-total\"]");
    public By addToBasket = By.cssSelector("button[id=\"pdp-add-to-cart-button\"]");
    public By closeAddToBasketModal = By.cssSelector("button[data-test=\"modal-close-button\"]");
}
