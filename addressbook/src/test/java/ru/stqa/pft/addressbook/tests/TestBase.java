package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.tests.groups.GroupCreationTests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;


public class TestBase {

    Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);
    protected static final ApplicationManager app = new ApplicationManager(
            System.getProperty("browser",  BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }
    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test "+ m.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test "+ m.getName());
    }
}
