package com.actoJava.qa.pages;

import com.actoJava.qa.base.BaseTest;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseTest {

    // object repository for home page
    @FindBy(xpath = "//input[@id='txtStationFrom']")
    private WebElement fromStation;

    @FindBy(xpath = "//input[@id='txtStationTo']")
    private WebElement toStation;

    @FindBy(xpath = "//input[@title='Select Departure date for availability']")
    private WebElement departureDatePicker;
    
    @FindBy(xpath = "//input[@id='buttonFromTo']")
    private WebElement getTrains;
    
  
    @FindBy(xpath = "//div[@class='autocomplete'][1]/div")
    private List<WebElement> selectDropdownFromStation;
    
    // constructor with PageFactory to initiate all the page objects
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    // actions or functions on the Home Page
    // recommended update for isDisplayed code : wrap it with try catch, as done in Login Page
    public boolean isHomePageDisplayed() {
        return fromStation.isDisplayed();
    }
    
    public void clearFromStation() {
    	fromStation.clear();
    }
    
    public String clickAtPosition(int index) {
    	return selectDropdownFromStation.get(index).getText();
    }

    public void enterDataInFromStation(String data) {
    	fromStation.sendKeys(data);
    }
    
   

	public void writeToFile() {
		String listOfStationNames = "";
		for(WebElement ele : selectDropdownFromStation) {
;			listOfStationNames = listOfStationNames + ele.getText().replace("\n", "") + ",";
		}
		
		String filePath = "./files/ActualStationNames.xlsx";
		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo.getConnection(filePath);
			connection.createTable("Sheet1", new String[] { "StationNames" });

			String arr[] = listOfStationNames.split(",");
			for(String name : arr) {
				String query = "INSERT INTO \"Sheet1\"(\"StationNames\") VALUES('"+name+"')";
				connection.executeUpdate(query);
			}
			connection.close();
		} catch (FilloException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean compareActualWithExpectedStationNames() {
		// TODO Auto-generated method stub
		return false;
	}
  
}
