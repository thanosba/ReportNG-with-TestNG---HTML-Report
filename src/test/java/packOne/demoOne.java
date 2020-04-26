package packOne;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class demoOne {

    public WebDriver driver;

    @BeforeTest
    public void stUp(){
        System.setProperty("webdriver.chrome.driver", "your path/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();
        //Wait for loading page
        waitForLoad(driver);
    }

    @Test
    public void firstTestCase()
    {
        driver.findElement(By.xpath("/html/body/div[2]/div/div/button")).click();
        System.out.println ("Pop up window closed");

    }
    @Test
    public void secondTestCase ()
    {
        assertTrue(isElementPresent(By.xpath(("//h1[contains(., 'Flipkart: The One-stop Shopping Destination')]"))));
        assertTrue ( isElementPresent ( By.linkText ( "Brand Directory" ) ) );
        driver.findElement(By.xpath("/html/body/div/div/footer/div/div[1]/div/div[1]/a")).click();
        assertTrue ( isElementPresent ( By.linkText ( "Books" ) ) );
        assertTrue (isElementPresent(By.linkText("Baby Care")));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/span[16]/a")).click();
        assertTrue ( isElementPresent ( By.xpath ( "/html/body/div[2]/h1" ) ) );
    }
    @Test
    public void thirdTestCase ()
    {
        driver.findElement(By.xpath("/html/body/div[1]/div/div/a/img")).click();
        assertTrue ( isElementPresent ( By.linkText ( "Cart" ) ) );
    }

    @Test (priority = 4)
    public void finalTestCase(){

        driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div[2]/div[5]/div/div/a")).click();
        assertFalse (isElementPresent (By.xpath ( "/html/body/div/div/div[8]/div/div/div/div/div[2]/div/div[22]" )));
    }

    @AfterTest
    public void afterTest() {
        driver.close();
        driver.quit();
    }

    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(pageLoadCondition);
    }
    public Boolean isElementPresent(By by) {
        if (driver.findElements(by).size() > 0)
            return true;
        else
            return false;
    }

}