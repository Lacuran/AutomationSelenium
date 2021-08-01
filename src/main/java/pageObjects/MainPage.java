package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public WebElement getLoginLink(){
        By loginLinkSelector = By.xpath("//div[@data-test='section-desktopLayout']//a[@data-test='anchor-login']");

        WebDriverWait wait = new WebDriverWait(driver, 1);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loginLinkSelector));
        } catch (Exception e){

        }

        wait = new WebDriverWait(driver, 3);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginLinkSelector));
    }
}
