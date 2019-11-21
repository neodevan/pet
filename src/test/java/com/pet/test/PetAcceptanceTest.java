package com.pet.test;

import org.json.JSONException;
import org.testng.annotations.*;



/**
 * Created by Vipin Alias Neo De Van
 */
public class PetAcceptanceTest extends BaseTest{

    
	@Test(priority=1)
    public void verifyCreated() throws JSONException {
        testUtils.createPet();
        response=testUtils.getPetDetails(testUtils.id);
        testUtils.verifyPetCreated(response);
    }
    
	
	@Test(priority=2)
    public void verifyUpated() throws JSONException {
        testUtils.updatePetDetails(testUtils.id, "Sasi", "pending");
        response=testUtils.getPetDetails(testUtils.id);
        testUtils.verifySingleData("name", response, "Sasi");
    }
	
	
	@Test(priority=3)
    public void verifyDeleted() throws JSONException {
        testUtils.deletePet(testUtils.id);
        response=testUtils.getPetDetails(testUtils.id);
        testUtils.verifySingleData("message", response, "Pet not found");
    }
    
}
