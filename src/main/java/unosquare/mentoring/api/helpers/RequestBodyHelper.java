package unosquare.mentoring.api.helpers;

import unosquare.mentoring.api.serialize.MapsBodyPostNewLocation;
import unosquare.mentoring.api.serialize.MapsBodyPostNewLocationCoordinates;

import java.util.ArrayList;
import java.util.Arrays;

//This method created a dummy object that will be used later as a body for the post maps endpoint

public final class RequestBodyHelper { // Final means we cannot inherit from this class.

    public static MapsBodyPostNewLocation returnSamplePostMapsBody(){

        ArrayList<String> tags = new ArrayList<String>(Arrays.asList("coffe", "restaurant", "grill")); // we are declaring a list

        MapsBodyPostNewLocationCoordinates coordinates = new MapsBodyPostNewLocationCoordinates();
        coordinates.lat = -38.383494;
        coordinates.lng = 33.427362;

        MapsBodyPostNewLocation body = new MapsBodyPostNewLocation();
        body.name = "Java Test";
        body.address = "Patria 567";
        body.language = "Spanish - US";
        body.accuracy = 5;
        body.phone_number = "(52) 55 4000 4010";
        body.website = "https://unosquare.com";

        body.types = tags;      //this is comming from the line 13
        body.location = coordinates; // this is coming from the lines 15-17.
        return body;
    }

}
