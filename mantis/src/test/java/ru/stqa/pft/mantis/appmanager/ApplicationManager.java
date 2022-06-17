package ru.stqa.pft.mantis.appmanager;

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

    private final Properties properties;
    private WebDriver wd;

    private String browser;
    private RegistrationHelper regHelper;
    private FtpHelper ftpHelper;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load((new FileReader((new File(String.format("src/test/resources/%s.properties", target))))));
    }

    public void stop() {
        if (wd != null)
            wd.quit();
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if(regHelper == null)
            regHelper = new RegistrationHelper(this);
        return regHelper;
    }

    public FtpHelper ftp() {
        if(ftpHelper == null)
            ftpHelper = new FtpHelper(this);
        return ftpHelper;
    }

    public MailHelper mail() {
        if(mailHelper == null)
            mailHelper = new MailHelper(this);
        return mailHelper;
    }

    public JamesHelper james() {
        if(jamesHelper == null)
            jamesHelper = new JamesHelper(this);
        return jamesHelper;
    }

    public DbHelper db() {
        if (dbHelper == null) {
            dbHelper = new DbHelper(this);
        }
        return dbHelper;
    }

    public WebDriver getDriver() {
        if(wd == null) {
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
        }
        return wd;
    }
}
