package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

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

  public boolean isIssueOpen(BigInteger issueId) throws MalformedURLException, ServiceException, RemoteException {
    ObjectRef status = app.soup().getIssueData(issueId).getStatus();
    if (status.getName().equals("resolved")||status.getName().equals("closed"))
      return false;
    else return true;
  }

  public void skipNotFixed(BigInteger issueId) throws MalformedURLException, ServiceException, RemoteException {
    if(isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
