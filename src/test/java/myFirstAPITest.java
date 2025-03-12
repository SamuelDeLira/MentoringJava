import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import unosquare.mentoring.api.deserialize.MapsResponseGetLocationInfo;
import unosquare.mentoring.api.deserialize.MapsResponsePostNewLocation;
import unosquare.mentoring.api.helpers.JsonSerializerGoogle;
import unosquare.mentoring.api.helpers.MapperDeserializer;
import unosquare.mentoring.api.helpers.RequestBodyHelper;
import unosquare.mentoring.api.serialize.MapsBodyPostNewLocation;
import unosquare.mentoring.bankingClasses.BbvaDebitCard;
import unosquare.mentoring.logger.Log;
import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1


public class myFirstAPITest {

    //------------------------------------------------------------------------------------------------
    //-----------------------------------Test 1 Post API----------------------------------------------
    //------------------------------------------------------------------------------------------------

    @Test
    public void e2eTest() throws JsonProcessingException { //Why can be removed this?

        //given - all input details
        //when - submit the API --response, http method
        //Then validate the response
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //Generating a dummy body as an object(This instance contains all the necessary info such as website, name, location, etc)
        MapsBodyPostNewLocation postBody = RequestBodyHelper.returnSamplePostMapsBody();

        String response =
        given()
                .log().all()
                .queryParam("Key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(JsonSerializerGoogle.serialize(postBody))
                .when()
                .post("maps/api/place/add/json")
                .then()
                .assertThat()
                    .statusCode(200)
                    .body("scope", equalTo("APP"))
                    .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        Log.print(response);


        JsonPath js=new JsonPath(response); //for parsing Json
        String placeId=js.getString("place_id"); // This will be printed from the desearilizedPostObject
         // Print the place id

        MapsResponsePostNewLocation deserealizedPostObject = MapperDeserializer.deserializedResponse(response, MapsResponsePostNewLocation.class);
        //BbvaDebitCard deserealizedPostObject = MapperDeserializer.deserializedResponse(response, BbvaDebitCard.class); //This line will cause an error (Failed Test)

        /*Log.print("The Status is: "+deserealizedPostObject.status);
        Log.print("The place id is: "+deserealizedPostObject.place_id);
        Log.print("The reference is: "+deserealizedPostObject.reference);
        Log.print("The id is: "+deserealizedPostObject.id);*/


        //------------------------------------------------------------------------------------------------
        //-----------------------------------Test 2 PUT API----------------------------------------------
        //------------------------------------------------------------------------------------------------

        //Update Place

        String newAddress = "Av Testing 1234";

        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "Application/json")
                .body("{\n" +
                        "    \"place_id\": \""+placeId+"\",\n" +
                        "    \"address\": \""+newAddress+"\",\n" +
                        "    \"key\": \"qaclick123\"\n" +
                        "}")
                .when()
                .put("maps/api/place/update/json")
                .then()
                .assertThat()
                    .log()
                    .all()
                    .statusCode(200)
                    .body("msg", equalTo("Address successfully updated"));

        //------------------------------------------------------------------------------------------------
        //-----------------------------------Test 3 Get API----------------------------------------------
        //------------------------------------------------------------------------------------------------

        //Get place "Validate the Address has been updated"
        String getPlaceResponse= ""; //Declare
        getPlaceResponse= given().log().all().queryParam("key", "qaclick123") //Assign
                .queryParam("place_id", placeId)
                .when()
                .get("maps/api/place/get/json")
                .then()
                .assertThat()
                .log()
                .all()
                .statusCode(200).extract().response().asString();

        JsonPath js1= new JsonPath(getPlaceResponse);
        String actualAddress = js1.getString("address");
        String actualLatitude = js1.getString("location.latitude");
        Log.print("The current address is: "+actualAddress);
        Log.print("The current latitude is: "+actualLatitude);
        Assert.assertEquals(actualAddress, newAddress, "Address validation");


         ObjectMapper om = new ObjectMapper();

        MapsResponseGetLocationInfo desearializedObject = null; //Default value for any object is null

         try{
             desearializedObject = om.readValue(getPlaceResponse, MapsResponseGetLocationInfo.class);
         }
         catch(JsonProcessingException jpe){
             throw jpe;
         }
         catch(Exception e){
             throw e;
         }

         Log.print("Language: " +desearializedObject.language);

         //MapsResponsePostNewLocation
        //MapsResponsePutUpdateLocation
    }
}
