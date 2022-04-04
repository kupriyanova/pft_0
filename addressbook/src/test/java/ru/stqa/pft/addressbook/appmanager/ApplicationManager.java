package ru.stqa.pft.addressbook.appmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final String browser;
    WebDriver wd;

    private SessionHelper sessionHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

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
        if (browser.equals(BrowserType.FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            WebDriverManager.chromedriver().setup();
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            WebDriverManager.iedriver().setup();
            wd = new InternetExplorerDriver();
        }

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
