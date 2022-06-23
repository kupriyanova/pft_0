package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

public class TestBase {

  public String ADMIN_LOGIN;
  public String ADMIN_PASSWORD;
  public String MAIL_LOGIN;
  public String MAIL_PASSWORD;

  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"),
            "config_inc.php",
            "config_inc.php.back");
    ADMIN_LOGIN = app.getProperty("web.adminLogin");
    ADMIN_PASSWORD = app.getProperty("web.adminPassword");
    MAIL_LOGIN = app.getProperty("mailServer.adminLogin");
    MAIL_PASSWORD = app.getProperty("mailServer.adminPassword");
  }
  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php", "config_inc.php.back");
    app.stop();
  }
}
