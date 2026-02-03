import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.io.File;

public class LoginTest {

    @Test
    public void loginAutomationTest() throws Exception {

        // Setup ChromeDriver automatically
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Jenkins safe
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        // Use relative path
        String loginPage = new File("src/test/resources/login.html").getAbsolutePath();
        driver.get("file:///" + loginPage.replace("\\", "/"));

        // Automation input - change email/password anytime
        driver.findElement(By.id("username")).sendKeys("bhagyasree2@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Strong@123");
        driver.findElement(By.tagName("button")).click();

        // Wait until message appears (max 5 sec)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("msg")));

        String text = msg.getText();
        Assert.assertEquals(text, "Login Successful");

        driver.quit();
    }
}
