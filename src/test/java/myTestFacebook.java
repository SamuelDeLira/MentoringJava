import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import unosquare.mentoring.logger.Log;

import java.time.Duration;
import java.util.List;

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
    public  void googleSearchFacebook() {
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

    @Test
    public  void registerFacebookUser() throws InterruptedException {
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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //WaitImplicit Declaration.

        //Move to 2nd screen (in my case 1st screen is Left, moving to 2nd (Right)
        driver.manage().window().setPosition(new Point(2000, 0)); //open in 2nd screen (when 2nd is on right position)
        driver.manage().window().maximize();

        try {
            // Navigate to Google
            driver.navigate().to("https://www.facebook.com/");


            //driver.findElement(By.cssSelector("[data-testid='open-registration-form-button']")).click();

            //This four (110-114) lines will replace the implementation from the 108 line. Explicit Wait

            // Create an explicit wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Wait until the button is clickable
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='open-registration-form-button']")));
            button.click();

            //Click on the Month dropdown. (Using the native select functionality from Selenium)
            WebElement ddlMonth = driver.findElement(By.id("month"));
            Select myselect = new Select(ddlMonth);
            myselect.selectByValue("6");

            //Click on the day dropdown.(Click in the regular way without using) for dropdowns that are not declared as "select"
            WebElement ddlDay = driver.findElement(By.id("day"));
            ddlDay.click(); //First click is to expand
             List<WebElement> allDays;
             allDays = ddlDay.findElements(By.xpath(".//option")); //First click is to expand

            boolean myFlag = false;
            for(WebElement currentDay : allDays){     //Iterate over every webelement within all days list

              String attributeValu = "";
              attributeValu = currentDay.getAttribute("value");

              if(attributeValu.equals("10")){
                  Log.print("found it");
                  myFlag = true;
                  currentDay.click();
              }
            }

            Assert.assertTrue(myFlag, "The day should be found inside the available options");


            Log.print("Testing");

           /* // Locate username field using CSS Selector and enter value
            WebElement username = driver.findElement(By.cssSelector("input#email"));
            username.sendKeys("user.test@gmail.com");

            // Locate password field using CSS Selector and enter value
            WebElement password = driver.findElement(By.cssSelector("input#pass"));
            password.sendKeys("Pass123");

            // Wait for results to load
            Thread.sleep(3000);

            // Print the title of the first result
            var firstResult = driver.findElement(By.cssSelector("h3"));
            Log.print("First Search Result: " + firstResult.getText());*/
        }

        catch (org.openqa.selenium.TimeoutException te) {
            Log.print("\nTimeout Exception Samo: " + te.getMessage() +"\n");
            throw te;

        }
        catch (NoSuchElementException nsee) {
            Log.print("No such element exception: " + nsee.getMessage());
            throw nsee;

        }
        catch (Exception ex) {
            Log.print("Error: " + ex.getMessage());
            throw ex;

        }


        finally {
            // Close the browser
            //driver.close();
            Thread.sleep(3000);
            driver.quit();  //
        }
    }
}
