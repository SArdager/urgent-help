package kz.kdlolymp.gynecology.controller.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import kz.kdlolymp.gynecology.entity.User;

import java.lang.reflect.Type;

public class UserSerializer implements JsonSerializer<User>{
    @Override
    public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jObject = new JsonObject();
        jObject.addProperty("id", user.getId());
        jObject.addProperty("userSurname", user.getUserSurname());
        jObject.addProperty("userFirstname", user.getUserFirstname());
        jObject.addProperty("phone", user.getUsername());
        jObject.addProperty("workPlace", user.getWorkPlace());
        jObject.addProperty("position", user.getPosition());
        jObject.addProperty("email", user.getEmail());
        jObject.addProperty("regionName", user.getRegionName());
        jObject.addProperty("isEnabled", user.isEnabled());
        jObject.addProperty("isTemporary", user.isTemporary());
        jObject.addProperty("role", user.getRole());
        return jObject;
    }
}
