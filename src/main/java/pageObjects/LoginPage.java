package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmailInput() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        By emailInput =By.xpath("//input[@data-test='input-email']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
    }

    public WebElement getPasswordInput() {
        By passwordInput = By.xpath("//*[@data-test='input-password']");
        return driver.findElement(passwordInput);
    }
    public WebElement getLoginButton() {
        By loginButton = By.xpath("//*[@data-test='button-login']");
        return driver.findElement(loginButton);
    }
}
