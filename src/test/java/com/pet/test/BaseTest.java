package com.pet.test;


import org.apache.log4j.Logger;
import org.junit.BeforeClass;

import com.assure.testutil.TestUtils;
import com.jayway.restassured.response.Response;
import com.pet.utils.ApiUtils;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/**
 * Created by Vipin Alias Neo De Van
 */
public class BaseTest {

	protected Logger log=Logger.getLogger(BaseTest.class);
    TestUtils testUtils;
    protected static Response response;

    @BeforeSuite
    public void setup (){
    	ApiUtils.setBaseURI();
        ApiUtils.MAINID=ApiUtils.getID();
        testUtils=new TestUtils(ApiUtils.MAINID);
        ApiUtils.setPayload(3423, "Doggie", "Bruno", "https://www.searchenginewatch.com/wp-content/uploads/2018/10/ThinkstockPhotos-503426092-300x225.jpg",
        		3432, "BullDog", "Available");
    }
    
    

}
