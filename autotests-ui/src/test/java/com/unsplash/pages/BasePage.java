package com.unsplash.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.unsplash.stepdefs.World;
import com.unsplash.testdata.CommonData;
import com.unsplash.utils.ObjectMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Base class for page objects.
 */
@Component
public class BasePage {

    @Autowired
    private World world;

    public void open(String url) {
        world.webDriverFactory.getDriver().get(url);
    }

    public String getCurrentURL() {
        return world.webDriverFactory.getDriver().getCurrentUrl();
    }

    public String getTitle() {
        return world.webDriverFactory.getDriver().getTitle();
    }

    protected void clickElement(By locator) {
        clickElement(locator, CommonData.DEFAULT_TIMEOUT);
    }

    protected void clickElement(By locator, long timeout) {
        clickElement(locator, false, timeout);
    }

    public void clickElement(By locator, boolean isScrolled, long timeout) {
        WebElement elm;
        if (isScrolled) {
            scrollIntoView(locator, true);
        }
        elm = waitElementToBeClickable(locator, timeout);
        elm.click();
    }

    protected void clickElement(WebElement elm) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(elm));
        elm.click();
    }

    /**
     * The DropDown is not feasible for normal Selenium Click. using Action Click instead.
     */
    protected void clickElementUsingActions(By locator) {
        Actions action = new Actions(world.webDriverFactory.getDriver());
        WebElement elm = waitElementToBeAppear(locator);
        action.click(elm).perform();
    }

    protected String getText(WebElement elm) {
        return elm.getText();
    }

    protected String getText(By locator) {
        WebElement elm = waitElementToBeAppear(locator);
        return elm.getText();
    }

    protected List<String> getTexts(By locator) {
        List<String> result = new ArrayList<>();
        List<WebElement> elms = waitElementsToBeExist(locator);
        for (WebElement elm : elms) {
            result.add(elm.getText());
        }
        return result;
    }

    /**
     * Get the "value" attribute of the element
     */
    protected String getValue(By locator) {
        WebElement elm = waitElementToBeExist(locator);
        return elm.getAttribute("value");
    }

    protected void dragAndDrop(By drag, By drop) {
        WebElement dragElm = waitElementToBeClickable(drag);
        WebElement dropElm = waitElementToBeClickable(drop);
        Actions act = new Actions(world.webDriverFactory.getDriver());
        act.dragAndDrop(dragElm, dropElm).perform();
    }

    protected void dragAndDrop(WebElement dragElm, WebElement dropElm) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(dragElm));
        wait.until(ExpectedConditions.elementToBeClickable(dropElm));
        Actions act = new Actions(world.webDriverFactory.getDriver());
        act.dragAndDrop(dragElm, dropElm).perform();
    }

    protected void setValue(By locator, String value) {
        setValue(locator, value, CommonData.DEFAULT_TIMEOUT);
    }

    protected void setValue(By locator, String value, long timeout) {
        WebElement elm = waitElementToBeClickable(locator, timeout);
        elm.clear();
        elm.sendKeys(value);
    }

    protected void typeText(By locator, String value) {
        WebElement elm = waitElementToBeClickable(locator);
        elm.sendKeys(value);
    }

    protected void sendKeys(By locator, CharSequence... keysToSend) {
        WebElement elm = waitElementToBeExist(locator);
        elm.sendKeys(keysToSend);
    }

    protected void selectAndTypeText(By locator, String value) {
        WebElement elm = waitElementToBeClickable(locator);
        elm.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        elm.sendKeys(value);
    }

    protected void typeTextToActiveElement(CharSequence... keysToSend) {
        world.webDriverFactory.getDriver().switchTo().activeElement().sendKeys(keysToSend);
    }

    protected WebElement waitElementToBeClickable(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitElementToBeClickable(By locator) {
        return waitElementToBeClickable(locator, CommonData.DEFAULT_TIMEOUT);
    }

    protected void waitElementToBeDisappear(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), timeout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitElementToBeDisappear(By locator) {
        waitElementToBeDisappear(locator, CommonData.DEFAULT_TIMEOUT);
    }

    protected WebElement waitElementToBeAppear(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitElementToBeAppear(By locator) {
        return waitElementToBeAppear(locator, CommonData.DEFAULT_TIMEOUT);
    }

    protected List<WebElement> waitElementsToBeAppear(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected List<WebElement> waitElementsToBeAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.DEFAULT_TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected List<WebElement> waitElementsToBeExist(By locator) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.DEFAULT_TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected WebElement waitElementToBeExist(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement waitElementToBeExist(By locator) {
        return waitElementToBeExist(locator, CommonData.DEFAULT_TIMEOUT);
    }

    protected void waitElementToContainExpectedText(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    protected boolean isElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.CONDITION_TIMEOUT);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isElementDisplayed(By locator, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), timeOutInSeconds);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isElementDisplayed(By locator) {
        return isElementDisplayed(locator, CommonData.CONDITION_TIMEOUT);
    }

    protected boolean isElementDisappear(By locator) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.CONDITION_TIMEOUT);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isElementExisted(By locator) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.CONDITION_TIMEOUT);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isElementSelected(By locator) {
        WebDriverWait wait = new WebDriverWait(world.webDriverFactory.getDriver(), CommonData.CONDITION_TIMEOUT);
        try {
            wait.until(ExpectedConditions.elementToBeSelected(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void selectOptionByValue(By locator, String text) {
        WebElement elm = waitElementToBeAppear(locator);
        Select select = new Select(elm);
        select.selectByValue(text);
    }

    //TODO: implement custom Select method to replace selenium Select
    protected void selectOptionByText(By locator, String text) {
        WebElement elm = waitElementToBeAppear(locator);
        waitElementToBeExist(By.xpath(".//option[normalize-space(.) = " + Quotes.escape(text) + "]"));
        Select select = new Select(elm);
        select.selectByVisibleText(text);
    }

    protected void selectOptionContainingText(By locator, String text) {
        WebElement elm = waitElementToBeAppear(locator);
        Select select = new Select(elm);
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            if (option.getText().contains(text)) {
                option.click();
                break;
            }
        }
    }

    protected void hoverOnElement(By locator) {
        WebElement elm = waitElementToBeAppear(locator);
        Actions action = new Actions(world.webDriverFactory.getDriver());
        action.moveToElement(elm).build().perform();
    }

    protected void hoverOnElementByJs(By locator) {
        WebElement elm = waitElementToBeAppear(locator);
        String executeScript = "var fireOnThis = arguments[0];"
                + "var mouseEventObj = document.createEvent('MouseEvents');"
                + "mouseEventObj.initEvent( 'mouseover', true, true );"
                + "fireOnThis.dispatchEvent(mouseEventObj);";
        JavascriptExecutor je = (JavascriptExecutor) world.webDriverFactory.getDriver();
        je.executeScript(executeScript, elm);
    }

    protected void scrollIntoView(By locator, boolean alignToTop) {
        WebElement elm = waitElementToBeExist(locator);
        JavascriptExecutor je = (JavascriptExecutor) world.webDriverFactory.getDriver();
        if (alignToTop) {
            je.executeScript("arguments[0].scrollIntoView(true);", elm);
        } else {
            je.executeScript("arguments[0].scrollIntoView(false);", elm);
        }
    }

    protected void scrollIntoView(By locator, String option) {
        WebElement elm = waitElementToBeExist(locator);
        JavascriptExecutor je = (JavascriptExecutor) world.webDriverFactory.getDriver();
        String command = "arguments[0].scrollIntoView(" + option + ");";
        je.executeScript(command, elm);
    }

    protected String getFormatStringByType(String productType) {
        String formatStr = "";
        if (productType.equals("Packages")) {
            formatStr = "packages";
        } else if (productType.equals("Customizable Packages")) {
            formatStr = "customPackages";
        } else if (productType.equals("Add-Ons")) {
            formatStr = "addOns";
        }
        return formatStr;
    }

    public void switchToNewWindow() {
        for (String winHandle : world.webDriverFactory.getDriver().getWindowHandles()) {
            switchToWindow(winHandle);
        }
    }

    public void switchToWindow(String nameOrHandle) {
        world.webDriverFactory.getDriver().switchTo().window(nameOrHandle);
    }

    protected void switchToFrame(By locator) {
        WebElement elm = waitElementToBeExist(locator);
        world.webDriverFactory.getDriver().switchTo().frame(elm);
    }

    protected void switchToDefaultContent() {
        world.webDriverFactory.getDriver().switchTo().defaultContent();
    }

    protected String getCssValue(By locator, String cssAtr) {
        WebElement elm = waitElementToBeExist(locator);
        return elm.getCssValue(cssAtr);
    }

    public String getTokenFromCookie() {
        Set<Cookie> cookieLst = world.webDriverFactory.getDriver().manage().getCookies();
        for (Cookie cookie : cookieLst) {
            if (cookie.getName().equalsIgnoreCase("zf_token")) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public List<WebElement> $$(By selector) {
        return world.webDriverFactory.getDriver().findElements(selector);
    }
}
