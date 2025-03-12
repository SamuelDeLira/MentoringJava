//package com.ejimenez;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

/*import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1

import Deserialize.GetLocationResponse;

import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
import com.fasterxml.jackson.core.JsonProcessingException;*/
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import unosquare.mentoring.logger.Log;

public class myTestRestAssureE
{
    @Test
    public void myFirstTestRestAssured() // throws JsonProcessingException //throws JsonMappingException, JsonProcessingException
    {
        //validate if Add Place API is working as expected

        //given - all input details
        //when - submit the API (resource/endpoint and http method)
        //then - validate the response

        RestAssured.baseURI= "https://rahulshettyacademy.com";

        //..............................................................................................................................
        //..................................................... STEP 1 - ADD PLACE .....................................................
        //..............................................................................................................................

        String originalAddress = "111 Primera Direccion (Original)";
        String response=
                given()
                        .log().all()
                        .queryParam("key", "qaclick123")
                        .header("Content-Type","application/json")
                        .body("{\n" +
                                "    \"location\": {\n" +
                                "        \"lat\": -38.383494,\n" +
                                "        \"lng\": 33.427362\n" +
                                "    },\n" +
                                "    \"accuracy\": 5,\n" +
                                "    \"name\": \"Mentoring Test\",\n" +
                                "    \"phone_number\": \"(52) 55 4000 4000\",\n" +
                                "    \"address\": \"111 Primera Direccion (Original)\",\n" +
                                "    \"types\": [\n" +
                                "        \"coffe\",\n" +
                                "        \"restaurant\"\n" +
                                "    ],\n" +
                                "    \"website\": \"https://unosquare.com\",\n" +
                                "    \"language\": \"French-IN\"\n" +
                                "}")
                        .when()
                        .post("maps/api/place/add/json")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .body("scope", equalTo("APP"))
                        .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();


        Log.print(response);

        JsonPath js=new JsonPath(response); //for parsing Json
        String placeId=js.getString("place_id");

        Log.print(placeId);


        //..............................................................................................................................
        //................................................... STEP 2 - UPDATE PLACE ....................................................
        //..............................................................................................................................

        String newAddress = "222 Segunda Direccion (Nueva)";

        given()
                .log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type","application/json")
                .body("{\r\n" +
                        "\"place_id\":\"" + placeId + "\",\r\n" +
                        "\"address\":\"" + newAddress + "\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}")
                .when()
                .put("maps/api/place/update/json")
                .then()
                .assertThat().log().all()
                .statusCode(200)
                .body("msg", equalTo("Address successfully updated"));



        //..............................................................................................................................
        //..................................................... STEP 3 - GET PLACE .....................................................
        //..............................................................................................................................

        /*String getPlaceResponse =
                given()
                        .log().all()
                        .queryParam("key", "qaclick123")
                        .queryParam("place_id", placeId)
                        .when()
                        .get("maps/api/place/get/json")
                        .then()
                        .assertThat().log().all()
                        .statusCode(200)
                        .extract().response().asString();

        JsonPath js1=ReUsableMethods.RawToJson(getPlaceResponse);
        String actualAddress =js1.getString("address");

        Log.print(actualAddress);
        Assert.assertEquals(actualAddress, newAddress, "Address validation");


        //..............................................................................................................................
        //................................................... STEP 4 - Deserialize .....................................................
        //..............................................................................................................................

        // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
        // import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
		 ObjectMapper om = new ObjectMapper();
		Root root = om.readValue(myJsonString), Root.class);

        ObjectMapper objectMapper = new ObjectMapper();

        //GetLocationResponse getLocationResponse0 = null;		
        GetLocationResponse getLocationResponse = objectMapper.readValue(getPlaceResponse, GetLocationResponse.class);

        Log.print("Location > Latitude :: " + getLocationResponse.location.latitude);
        Log.print("Location > Longitude :: " + getLocationResponse.location.longitude);
        Log.print("Accuracy :: " + getLocationResponse.accuracy);
        Log.print("Name :: " + getLocationResponse.name);
        Log.print("Phone number :: " + getLocationResponse.phone_number);
        Log.print("Address :: " + getLocationResponse.address);
        Log.print("Types :: " + getLocationResponse.types);
        Log.print("Website :: " + getLocationResponse.website);
        Log.print("Language :: " + getLocationResponse.language);

        Log.printRowLineWithId();*/
    }
}
