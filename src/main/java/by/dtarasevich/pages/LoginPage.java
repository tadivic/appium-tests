package by.dtarasevich.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final AppiumDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.alfabank.qapp:id/tvTitle\"]")
    private WebElement title;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"com.alfabank.qapp:id/etUsername\"]")
    private WebElement login;
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"com.alfabank.qapp:id/etPassword\"]")
    private WebElement password;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.alfabank.qapp:id/tvError\"]")
    private WebElement errorMsg;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Show password\"]")
    private WebElement showPass;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.alfabank.qapp:id/btnConfirm\"]")
    private WebElement loginButton;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getTitle() {
        return title.getText();
    }

    public void setLogin(String val) {
        login.sendKeys(val);
    }

    public void setPassword(String val) {
        password.sendKeys(val);
    }
    public String getPassword() {
        return password.getText();
    }

    public void clickLogin() {
        loginButton.click();
    }
    public void clickShowPass() {
        showPass.click();
    }

    public String getError() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(errorMsg));
        return errorMsg.getText();
    }
}