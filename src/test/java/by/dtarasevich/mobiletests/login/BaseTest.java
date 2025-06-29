package by.dtarasevich.mobiletests.login;

import by.dtarasevich.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.fail;

public class BaseTest {
    AppiumDriver driver;

    @AfterMethod
    public void afterMethod(ITestResult res) {
        try {
                Allure.addAttachment("Скриншот", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));


        } catch (Throwable ignored) {

        }
    }

    @BeforeSuite
    public void setUp() {
        try {
            driver = DriverManager.getMobileDriver();
        } catch (Throwable e) {
            fail("ПРОБЛЕМА С СОЗДАНИЕМ ДРАЙВЕРА");
        }
    }
    @AfterSuite
    public void tearDown() {
        try {
          DriverManager.quitDriver();
        } catch (Throwable e) {
            fail("ПРОБЛЕМА С СОЗДАНИЕМ ДРАЙВЕРА");
        }
    }

}
