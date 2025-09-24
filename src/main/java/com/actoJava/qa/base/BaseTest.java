package com.actoJava.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.safari.SafariDriver;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;



public class BaseTest {

    // declaring all variables
    public static Properties prop;
    public static InputStream fileInputStream;

    public static WebDriver driver;
    

    @BeforeMethod
    public void setup() {
    // this method will run Before each @Test method we will have

        try {
            //read properties file
            String propFilePath = System.getProperty("user.dir") + "/src/main/resources/config/config.properties";
            System.out.println(propFilePath);
            fileInputStream = new FileInputStream(propFilePath);

            // load properties file in Properties
            prop = new Properties();
            prop.load(fileInputStream);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        // read which browser is required for test from config file
        String browser = prop.getProperty("browser").toUpperCase();

        // launch appropriate browser
        switch (browser) {
            case "CHROME":
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "SAFARI":
                driver = new SafariDriver();
                break;
            case "EDGE":
                driver = new EdgeDriver();
                break;
            case "IEXPLORER":
                driver = new InternetExplorerDriver();
                break;
        }
        Reporter.log("======Launch Browser======", true);


        // navigate to application and set the pace of script
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(prop.getProperty("url"));

    }

    @AfterMethod
    public void tearDown() {
        // this method will run Before each @Test method we will have

        driver.quit();
        Reporter.log("======Browser Closed======");
    }

}