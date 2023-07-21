import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.openqa.selenium.By.*;

/***
 * open web browser to launch Konga
 * Click to sign in Successfully
 * click on the computers and accessories on "Categories"
 * click on laptop
 * click on Apple MacBooks
 * Add an item to the cart
 * Select Address
 * Continue to Make Payment
 * Select a card Payment Method
 * input invalid card details
 * printout the error message: invalid card Number
 * Close the iFrame that displays the input card modal
 * quit browser
 */

    //identifying the class
public class KongaProjectTest {
    private WebDriver driver;
    //this line tells the program to execute this task before conducting the test
    @BeforeTest
    public void StartSetup() throws InterruptedException {
        //allows the test to determine the base environment
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        //launches the browser
        driver.get("https://www.konga.com/");
        Thread.sleep(5000);
        //maximizes the window
        driver.manage().window().maximize();
        Thread.sleep(5000);
        //This line executes the login page
        driver.findElement(xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
        Thread.sleep(5000);
    }

    //this tells the program to execute this test first
    @Test(priority = 0)
    public void LoginPage() throws InterruptedException {
        //This executes the task to input the username and password
        driver.findElement(id("username")).sendKeys("audreyebuka94@gmail.com");
        Thread.sleep(2000);
        driver.findElement(id("password")).sendKeys("Mypasswordispassword");
        Thread.sleep(3000);
        // This line clicks on the login tab
        driver.findElement(xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(6000);
    }

    @Test(priority = 1)
    public void CategoryPage() throws InterruptedException {
        //This line is to find the category from the HOme page
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();
        Thread.sleep(12000);
        //This line is to click on the laptop option from the category
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/label/span")).click();
        Thread.sleep(5000);
        //This line executes the option to pick Apple Laptop
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li/a/ul/li[1]/a/label/span")).click();
        Thread.sleep(10000);
        //This line adds the selected choice of laptop to cart
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[22]/div/div/div[2]/form/div[3]/button")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 2)
    public void AddressSelection() throws InterruptedException {
        //To line executes the option to check the cart to view goods selected
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]/span[1]")).click();
        Thread.sleep(5000);
        //proceed to checkout
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();
        Thread.sleep(10000);
        //Change address
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[1]/div[2]/div/button")).click();
        //To add Address
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button")).click();
        Thread.sleep(3000);
        //Select address
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div/form/button")).click();
        Thread.sleep(3000);
        //confirm address
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();
        Thread.sleep(3000);
        //to click on pay with card
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
        Thread.sleep(5000);
        //continue with payment
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
        Thread.sleep(10000);
    }

    @Test(priority = 3)
    public void CardPayment() throws InterruptedException {
        //This allows the test to switch to iframe and creates a popup to input card details
        By localframe = By.xpath("//*[@id=\"kpg-frame-component\"]");
        driver.switchTo().frame(driver.findElement(localframe));
        Thread.sleep(10000);
        //chooses card and input card details
        driver.findElement(By.xpath("//*[@id=\"channel-template\"]/div[2]/div/div[2]/button")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("card-number")).sendKeys("1234567891234578");
        Thread.sleep(3000);
        driver.findElement(By.id("expiry")).sendKeys("2222");
        Thread.sleep(3000);
        driver.findElement(By.id("cvv")).sendKeys("987");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"validateCardForm\"]")).click();
        Thread.sleep(3000);
        //Shows invalid card input
        if (driver.findElement(By.id("card-number_unhappy")).isDisplayed())
            System.out.println("Invalid Card Details");
        else
            System.out.println("Valid Card Details");

        Thread.sleep(5000);

        //This part executes the closing of the Popup and executes switching off iframe and closing the popup
        WebElement ClosePopUp = driver.findElement(By.xpath("/html/body/section/section/section/div[2]/div[1]/aside"));
        ClosePopUp.click();
        driver.switchTo().defaultContent();
        System.out.println("Close pop-up");
    }
    //This line allows the browser to close after the tests has been executed
    @AfterTest
    public void CloseBrowser() {
        driver.quit();
    }

}

