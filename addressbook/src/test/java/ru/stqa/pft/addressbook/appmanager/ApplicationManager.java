package ru.stqa.pft.addressbook.appmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd;

    private SessionHelper sessionHelper;
    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    private NavigationHelper navigationHelper;
    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    private GroupHelper groupHelper;
    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    private ContactHelper contactHelper;
    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public void init() {
        WebDriverManager.chromedriver().setup();
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/group.php");
        sessionHelper = new SessionHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        getSessionHelper().login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }
}
