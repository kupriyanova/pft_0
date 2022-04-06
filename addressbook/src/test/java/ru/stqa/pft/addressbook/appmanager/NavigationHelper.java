package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoHomePage() {
        if (isElementPresent(By.id("maintable")))
            return;
        click(By.linkText("home"));
    }
    public void returnToHomePage() {
        if (isElementPresent(By.linkText("home page")))
            click(By.linkText("home page"));
    }

    public void groupPage() {
        if(isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.tagName("new")))
            return;
        click(By.linkText("groups"));
    }
}
