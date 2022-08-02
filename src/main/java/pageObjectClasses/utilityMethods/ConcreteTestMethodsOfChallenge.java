package pageObjectClasses.utilityMethods;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;

public class ConcreteTestMethodsOfChallenge extends GenericMethods{
        private static final org.apache.logging.log4j.core.Logger logger = (org.apache.logging.log4j.core.Logger) LogManager.getLogger(ConcreteTestMethodsOfChallenge.class);
    /**
     * @see ConcreteTestMethodsOfChallenge is inheriting from Utility
     * constructor without parameters is necessary to define otherwise in the inheriting class
     * a super(d); should have been called in the constructor
     */
    WebDriver d;
    public ConcreteTestMethodsOfChallenge(WebDriver d) {
        super(d);
        this.d = d;
        PageFactory.initElements(d, this);
    }

    public void enterTextToConcreteSearchForm(By element,String str) throws Exception {
        enterTextToSearchForm(element,str);
    }

    public void findStartingElementAfterSomeOtherActionsOrOnNewPage(By element){
        waitForElementToBePresent(element);
        //waitForElementVisiblityByElement(element);
    }

}
