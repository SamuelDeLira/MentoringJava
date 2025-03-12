package unosquare.mentoring.api.helpers;

import com.google.gson.Gson;
import unosquare.mentoring.logger.Log;

public final class JsonSerializerGoogle {

    //BELOW METHOD TRANSFORM ANY OBJECT FROM ANY CLASS INTO A STRING/JSON REPRESENTATION

    public static String serialize(Object object){
        String jsonBody = new Gson().toJson(object);
        Log.print("Serialized body :" +jsonBody);
        return jsonBody;
    }

}
