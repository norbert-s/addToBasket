package pageObjectClasses.implementableMethods;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.function.Consumer;

public interface  SelectFromDropDown {
    public  void selectFromDropDown(Consumer<Select> consumer, WebElement element);
}
