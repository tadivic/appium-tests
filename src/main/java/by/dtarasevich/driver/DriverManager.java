package by.dtarasevich.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


public class DriverManager {
    private static final String PATH_TO_APP_PATTERN = "src/test/resources/mobileBuilds/%s";
    public static final boolean DEFAULT_FULL_RESET_VALUE = true;
    private static ThreadLocal<AppiumDriver> threadLocalDriver=new ThreadLocal<>();
    private static final Logger log = LoggerFactory.getLogger(DriverManager.class.getName());
    private static ThreadLocal<DriverManager> threadLocalInstance;
    private static Dimension windowSize;
    private static Set<AppiumDriver> driverSet = new HashSet<>();



    public static AppiumDriver getMobileDriver() throws MalformedURLException {
        if (threadLocalDriver.get() == null) {
            createMobileDriver();
        }
        return threadLocalDriver.get();
    }


    public static void quitDriver() {
        threadLocalDriver.get().quit();
        threadLocalDriver.set(null);
    }

    private static void createMobileDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Pixel 9");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", "/Users/tadivic/AndroidStudioProjects/qa-mobile-2/app/build/outputs/apk/debug/app-debug.apk");

        URL driverURL = new URL("http://127.0.0.1:4723/");

        threadLocalDriver = ThreadLocal.withInitial(() -> new AndroidDriver(driverURL, capabilities));

    }
}