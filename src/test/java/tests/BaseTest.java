package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "/Users/maksimsubbotin/Documents/Я - программер/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://uniticket.ru/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));

    }

    @After
    public void tearDown() {
        driver.quit();
        if (driver!=null){
            driver = null;
        }
    }
}
