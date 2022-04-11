package ru.stqa.pft.addressbook.appmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final String browser;
    private final Properties properties;
    WebDriver wd;

    private SessionHelper sessionHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    private NavigationHelper navigationHelper;

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    private GroupHelper groupHelper;
    public GroupHelper group() {
        return groupHelper;
    }

    private ContactHelper contactHelper;
    public ContactHelper contact() {
        return contactHelper;
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));

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

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        sessionHelper = new SessionHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        getSessionHelper().login(
                properties.getProperty("web.adminLogin"),
                properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        wd.quit();
    }
}
