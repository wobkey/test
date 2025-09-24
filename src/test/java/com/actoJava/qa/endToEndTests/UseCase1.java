package com.actoJava.qa.endToEndTests;

import com.actoJava.qa.base.BaseTest;
import com.actoJava.qa.pages.HomePage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class UseCase1 extends BaseTest {

   
    @Test(priority = 1, enabled = true)
    public void testFromStationAndVerifyStationNames() {
        Reporter.log("======Open application======", true);
        HomePage hp = new HomePage();

        hp.clearFromStation();
        hp.enterDataInFromStation("DEL");
        Reporter.log(hp.clickAtPosition(4));
        hp.writeToFile();
      
        Assert.assertTrue( hp.compareActualWithExpectedStationNames(),"Station names validated.");
    }
}
