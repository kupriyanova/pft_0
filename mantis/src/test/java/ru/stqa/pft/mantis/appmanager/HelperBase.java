package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.*;

import java.io.File;

public class HelperBase {

    protected ApplicationManager app;
    protected WebDriver wd;

    public HelperBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
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
    protected void attach(By locator, File file) {
        WebElement input = wd.findElement(locator);
        if(file != null) {
            input.sendKeys(file.getAbsolutePath());
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
