package unosquare.mentoring.api.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import unosquare.mentoring.logger.Log;

public final class MapperDeserializer {
    //  BELOW METHOD TRANSFORMS A STRING INTO ANY OBJECT FROM ANY CLASS.

    // Using Generics: This means whenever we call this method we are going to specify what type/class...
    //...of object we want to receive (return type is customizable)
    public static <T> T deserializedResponse(String responseJson, Class<T> valueType) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        try {
            //Instance an object of class "T"(Can be anything: specified as parameters in the .deserializedResponse(x,y))
            return mapper.readValue(responseJson, valueType);
        }
        catch (InvalidDefinitionException ide) {
            Log.print("Exception: " +ide.getMessage());
            throw ide;
        }
        catch (JsonMappingException jme) {
            Log.print("JsonMappingException: " +jme.getMessage());
            throw jme;
        }
        catch (JsonProcessingException jpe) {
            Log.print("JsonProcessingException: " +jpe.getMessage());
            throw jpe;
        }
        catch (Exception e) {
            Log.print("Exception: " +e.getMessage());
            throw e;
        }
    }

}
