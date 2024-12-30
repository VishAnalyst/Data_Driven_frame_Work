package DataProvider;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginDemo {

    @AfterClass

    @Test (dataProvider = "getData")
    public void multipleLogin(String Username,String Password) throws IOException {


    //Initiate the Webdriver and maximize it
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://practicetestautomation.com/practice-test-login/");
        System.out.println("THE WEBSITE IS OPENED");

    //locate usier name field and pass word field and pass the values
        WebElement UsernameField = driver.findElement(By.xpath("//input[@id='username']"));
        UsernameField.sendKeys(Username);
        System.out.println("Username filed is located and passed values");

        WebElement PasswordField = driver.findElement(By.xpath("//input[@id='username']"));
        PasswordField.sendKeys(Password);
        System.out.println("password filed is located and passed values");

        //buttonclick action should be performed.
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submit']"));
        submitButton.click();

        System.out.println("TEST PASSED");

        //Taking screenshot
        TakesScreenshot objScr = (TakesScreenshot) driver;
        File scrFile = objScr.getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("ddMMyyyy_hhmmss").format(new Date());
        String fileType = "IMG"+timeStamp+".png";
        FileUtils.copyFile(scrFile, new File("/Users/preethianil/Downloads/Selenium/ScreenShots/"+fileType));
        System.out.println("The screenshot is taken as"+fileType);

        driver.quit();

    }
    @DataProvider
    //Create a method here and will be assigned to a two diamentional array as the input field has only two values
    //get data is the object name
    //Object[][] is the array
    Object [][] getData() {
        //Letting array know how many row and column is there by passing the object.
        Object [][] data = new Object[4][2];
        //Passing the first value
        data[0][0] = "admin";
        data[0][1] = "admin";

        //Passing the second value
        data[1][0] = "invalid1";
        data[1][1] = "invalid1";

        //Passing the third value
        data[2][0] = "invalid2";
        data[2][1] = "invalid1";

        //Passing the forth value
        data[3][0] = "invalid4";
        data[3][1] = "invalid5";



        //Return data all these data to the method
        return data;
    }


}