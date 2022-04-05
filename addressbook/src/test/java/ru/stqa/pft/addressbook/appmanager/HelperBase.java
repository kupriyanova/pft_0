package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void type(By locator, String text) {
        WebElement input = wd.findElement(locator);
        click(input);
        if(text != null) {
            if(!input.getAttribute("value").equals(text)) {
                input.clear();
                input.sendKeys(text);
            }
        }
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }
    protected void click(WebElement locator) {
        locator.click();
    }
    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
