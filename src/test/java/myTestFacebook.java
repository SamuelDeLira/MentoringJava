import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import unosquare.mentoring.logger.Log;

public class myTestFacebook {

    @BeforeMethod
    public void beforeMethod(){
        Log.print("BeforeMethod");
    }

    @BeforeClass
    public void beforeClass(){
        Log.print("BeforeClass");
    }


    @Test
    public void googleSearchFacebook() {
        // Setup ChromeDriver
        //new DriverManager().SetUpDriver(new ChromeConfig());
        WebDriver driver; //Declaring driver as interface

        // Set up ChromeOptions
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");

        driver = new ChromeDriver(chromeOptions); // create instance and disguising driver as a Chrome driver (Can be firefox, etc)

        //Move to 2nd screen (in my case 1st screen is Left, moving to 2nd (Right)
        driver.manage().window().setPosition(new Point(2000, 0)); //open in 2nd screen (when 2nd is on right position)
        driver.manage().window().maximize();

        try {
            // Navigate to Google
            driver.navigate().to("https://www.facebook.com/");

            // Locate username field using CSS Selector and enter value
            WebElement username = driver.findElement(By.cssSelector("input#email"));
            username.sendKeys("user.test@gmail.com");

            // Locate password field using CSS Selector and enter value
            WebElement password = driver.findElement(By.cssSelector("input#pass"));
            password.sendKeys("Pass123");

            // Wait for results to load
            Thread.sleep(3000);

           // Print the title of the first result
            var firstResult = driver.findElement(By.cssSelector("h3"));
            Log.print("First Search Result: " + firstResult.getText());
        } catch (Exception ex) {
            Log.print("Error: " + ex.getMessage());
        } finally {
            // Close the browser
            driver.close();
            //driver.quit();
        }
    }

    @AfterMethod
    public void afterMethod(){
        Log.print("AfterMethod");
    }

    @AfterClass
    public void afterClass(){
        Log.print("AfterClass");
    }
}
