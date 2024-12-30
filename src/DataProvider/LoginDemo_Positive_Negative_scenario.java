package DataProvider;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class LoginDemo_Positive_Negative_scenario {

    @Test(dataProvider = "getData")
    public void loginPage(String Username, String Password) throws IOException {
        //Initiate the webdriver and call the webpage
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");

        //Locating the username and password field and pass the vlaues using the string method.
        WebElement userNameField = driver.findElement(By.xpath("//input[@id='username']"));
        userNameField.sendKeys(Username);
        System.out.println("Username is found and passed value as"+ Username);
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
        passwordField.sendKeys(Password);
        System.out.println("Password is found and passed value as "+Password);
        //Locating the Loginbutton and click the same
        WebElement loginButton = driver.findElement(By.xpath("//button[@id='submit']"));
        loginButton.click();
        System.out.println("Login button is clicked");

        //For every process screenshould be taken.
        TakesScreenshot objScr = (TakesScreenshot) driver;
        File scrFile = objScr.getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("ddMMyyyy_hhmmss").format(new Date());
        String fileType = "IMG"+timeStamp+".png";
        FileUtils.copyFile(scrFile,new File("/Users/preethianil/Downloads/Selenium/ScreenShots/"+fileType));
        System.out.println("The screenshot for the process is taken and file name is: "+fileType);
        //Quit the browser after every login option.

        try {
            WebElement welComeMessage = driver.findElement(By.xpath("//h1[normalize-space()='Logged In Successfully']"));
            if (welComeMessage.isDisplayed()) {
                System.out.println("The login is sucessful TEST PASSED");
            }
        } catch (NoSuchElementException e) {
            System.out.println("The login failed. TEST FAILED");
        }

        //Quit the browser
        driver.quit();

    }
    @DataProvider
    //creating the methode for the object array
    Object [][] getData(){
        //determine the size of the array ny object creation
        Object [][] data = new Object[5][2];
        //Passing the invalid values
        data[0][0] ="admin";
        data[0][1] ="admin";

        data[1][0] ="in";
        data[1][1] ="123in";

        data[2][0] ="in";
        data[2][1] ="adjjhmin";

        data[3][0] ="testuser";
        data[3][1] ="admin";

        data[4][0] ="student";
        data[4][1] ="Password123";

        return data;
    }
}
