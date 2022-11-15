package kz.kdlolymp.gynecology.controller.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import kz.kdlolymp.gynecology.entity.UserVisits;

import java.lang.reflect.Type;

public class UserVisitsSerializer implements JsonSerializer<UserVisits> {
    @Override
    public JsonElement serialize(UserVisits userVisits, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jObject = new JsonObject();
        jObject.addProperty("regionName", userVisits.getUser().getRegionName());
        jObject.addProperty("userName", userVisits.getUser().getUserSurname() + " " + userVisits.getUser().getUserFirstname());
        jObject.addProperty("workPlace", userVisits.getUser().getWorkPlace());
        jObject.addProperty("position", userVisits.getUser().getPosition());
        jObject.addProperty("visitsCount", userVisits.getTotal());
        return jObject;
    }
}
