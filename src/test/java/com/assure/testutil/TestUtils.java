package com.assure.testutil;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
//import com.jayway.jsonpath.JsonPath
import com.jayway.restassured.response.Response;
import com.pet.utils.ApiUtils;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;

/**
 * Created by neodevan
 */
public class TestUtils {

	public int id;
	
	private static final Logger log = Logger.getLogger(TestUtils.class);

	public TestUtils(int id){
		this.id=id;
	}
	
	public void checkStatusIs200(Response res) {
		Assert.assertEquals(res.getStatusCode(), 200, "Status Check Failed!");
	}

	public Response createPet() {
		return ApiUtils.postRequest();
	}

	public Response updatePetDetails(int id, String name, String status) {
		return ApiUtils.postRequestURLEncoded(id, name, status);
	}

	public Response getPetDetails(int id) {
		return ApiUtils.getResponseBody(id);
	}

	public Response deletePet(int id) {
		return ApiUtils.deleteRequest(id);
	}

	public void verifyPetCreated(Response response) throws JSONException {
		JSONAssert.assertEquals(ApiUtils.payload.replaceAll("[\\n\\t ]", ""),
				response.body().asString(), true);
	}

	public void verifySingleData(String key, Response response,
			Object expectedData) {
		Assert.assertEquals(JsonPath.parse(ApiUtils.getJsonPath(response))
				.read("$['" + key + "']"), expectedData);
	}

	public void verifyPetDeleted(String key, Response response,
			Object expectedData) {
		Assert.assertEquals(JsonPath.parse(ApiUtils.getJsonPath(response))
				.read("$['" + key + "']"), expectedData);
	}

	public void verifyAPIDataByFilterCriteria(String criteriaKey,
			Object criteraValue, String parentNode, Response response,
			String expectedKey, Object expectedValue) {
		String ActualData = filterJsonData(criteriaKey, criteraValue,
				parentNode, response, expectedKey).toString();
		Assert.assertEquals(ActualData.contains((CharSequence) expectedValue),
				true);
	}

	public Object filterJsonData(String criteriaKey, Object criteraValue,
			String parentNode, Response response, String expectedKey) {
		Filter criteriaFilter = Filter.filter(Criteria.where(criteriaKey).eq(
				criteraValue));
		List<Map<String, Object>> filteredData = JsonPath.parse(
				ApiUtils.getJsonPath(response)).read(
				"$['" + parentNode + "'][?]", criteriaFilter);
		for (Map<String, Object> m : filteredData) {
			if (m.containsKey(expectedKey)) {
				return m.get(expectedKey);
			}
		}
		return null;
	}

}
