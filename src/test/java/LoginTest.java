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

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        // ✅ Correct way to load local HTML page
        File loginFile = new File("src/test/resources/login.html");
        String loginPath = loginFile.getAbsolutePath().replace("\\", "/");
        driver.get("file:///" + loginPath);

        driver.findElement(By.id("username")).sendKeys("bhagyasree2@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Strong@123");
        driver.findElement(By.tagName("button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("msg")));

        String text = msg.getText();
        Assert.assertEquals(text, "Login Successful");

        driver.quit();
    }
}
