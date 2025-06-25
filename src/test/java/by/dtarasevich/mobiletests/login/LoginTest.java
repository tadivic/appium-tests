package by.dtarasevich.mobiletests.login;


import by.dtarasevich.pages.HomePage;
import by.dtarasevich.pages.LoginPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

public class LoginTest extends BaseTest {


    @Epic("Тесты логина")
    @Test(description = "Тест на заголовок", priority = 1)
    public void testTitle() {
        Allure.step("Тест на заголовок", () -> {
            LoginPage loginPage = new LoginPage(driver);
            Assert.assertEquals(loginPage.getTitle(), "Вход в Alfa-Test", "Неправильный заголовок входа");
        });
    }

    @Test(description = "Неуспешный логин1", priority = 2)
    @Epic("Тесты логина")
    public void testLoginFailure1() {
        Allure.step("Тест на неуспешный логин", () -> {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setLogin("Login1");
            loginPage.setPassword("Password");
            loginPage.clickLogin();
            try {
                await().atMost(Duration.ofSeconds(10)).until(() -> {
                    return loginPage.getError().equals("Введены неверные данные");
                });
            } catch (Throwable ignored) {

            }
            Assert.assertEquals(loginPage.getError(), "Введены неверные данные");
        });
    }

    @Test(description = "Неуспешный логин2", priority = 3)
    @Epic("Тесты логина")
    public void testLoginFailure2() {
        Allure.step("Тест на неуспешный логин", () -> {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setLogin("Login");
            loginPage.setPassword("Password1");
            loginPage.clickLogin();
            try {
                await().atMost(Duration.ofSeconds(10)).until(() -> {
                    return loginPage.getError().equals("Введены неверные данные");
                });
            } catch (Throwable ignored) {

            }
            Assert.assertEquals(loginPage.getError(), "Введены неверные данные");
        });
    }

    @Test(description = "Неуспешный логин3", priority = 4)
    @Epic("Тесты логина")
    public void testLoginFailure3() {
        Allure.step("Тест на неуспешный логин", () -> {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setLogin("");
            loginPage.setPassword("");
            loginPage.clickLogin();
            try {
                await().atMost(Duration.ofSeconds(10)).until(() -> {
                    return loginPage.getError().equals("Введены неверные данные");
                });
            } catch (Throwable ignored) {

            }
            Assert.assertEquals(loginPage.getError(), "Введены неверные данные");
        });
    }

    @Test(description = "Успешный логин", priority = 6)
    @Epic("Тесты логина")
    public void testLoginSuccess() {
        Allure.step("Тест на успешный логин", () -> {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setLogin("Login");
            loginPage.setPassword("Password");
            loginPage.clickLogin();
            HomePage homePage = new HomePage(driver);
            Assert.assertTrue(homePage.titleDisplayed(), "Вход не выполнен");
        });
    }

    @Test(description = "Отображение пароля", priority = 5)
    @Epic("Тесты логина")
    public void testLoginShowPass() {
        Allure.step("Тест на отображение пароля", () -> {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.setPassword("Password");
            loginPage.clickShowPass();
            try {
                await().atMost(Duration.ofSeconds(10)).until(() -> {
                    return loginPage.getPassword().equals("Password");
                });
            } catch (Throwable ignored) {

            }
            Assert.assertEquals(loginPage.getPassword(), "Password");
        });
    }
}