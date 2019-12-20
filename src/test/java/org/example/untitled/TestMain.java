package org.example.untitled;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestMain {
    @Test
    public void testApi(){
        String URL = "http://dummy.restapiexample.com/api/v1/employee/1";
        //BDD Behaviour driven develomnet
        //given дано
        //when когда
        //then тогда
        JsonPath body = given().log().all()
                .when().get(URL)
                .then().extract().body().jsonPath();
        System.out.println(body.get("employee_name"));

    }

    @Test
    public void testMarketData(){
        String URL = "https://api.iextrading.com/1.0/stats/intraday";
        JsonPath body = given().log().all()
                .when().get(URL)
                .then().extract().body().jsonPath();

        Assert.assertTrue(((Long)body.get("symbolsTraded.lastUpdated"))>1L);
    }

    @Test
    public void testPost(){
        String URL = "http://dummy.restapiexample.com/api/v1/create";
        String postBody = "{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\",\"id\":\"719\"}";
        given().log().all().body(postBody)
                .when().post(URL).
                then().statusCode(200);

        //RequestFilter
    }
}
