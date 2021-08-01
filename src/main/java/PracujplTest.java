import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import static org.assertj.core.api.Assertions.assertThat;

public class PracujplTest {
    WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.pracuj.pl/");
        driver.findElement(By.xpath("//button[@data-test='button-accept-all-in-general']")).click();
    }

    @Test
    public void getTitle() {
        MainPage mainPage = new MainPage(driver);
        String title = mainPage.getTitle();
        assertThat(title).isEqualTo("Praca - Pracuj.pl");
    }

    @Test
    public void shouldShowErrorMessageOnBadLoginCredentials() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.getLoginLink().click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.getEmailInput().sendKeys("JanKowalski12345@gmail.com");
        loginPage.getPasswordInput().sendKeys("HasloJanaKowalskiego");
        loginPage.getLoginButton().click();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        By alertMessage = By.xpath("//*[@data-test='text-feedback-message']");
        String expectedMessage = "Możliwe, że nie potwierdziłeś swojego konta lub 3 razy użyłeś złego hasła. Sprawdź pocztę lub spróbuj później.";
        String actualMessage =  wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage)).getText();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}