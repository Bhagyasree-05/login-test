import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    @Test
    public void loginAutomationTest() throws Exception {

        // ChromeDriver automatic setup
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Jenkins safe
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        driver.get("file:///C:/Users/LENOVO/Desktop/login-test/src/test/resources/login.html");

        // Automation input - change anytime
        driver.findElement(By.id("username")).sendKeys("bhagyasree2@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Strong@123");
        driver.findElement(By.tagName("button")).click();

        Thread.sleep(2000); // wait for message update

        String msg = driver.findElement(By.id("msg")).getText();
        Assert.assertEquals(msg, "Login Successful");

        driver.quit();
    }
}
