package kz.kdlolymp.gynecology.controller.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import kz.kdlolymp.gynecology.entity.RegionVisits;

import java.lang.reflect.Type;

public class RegionVisitsSerializer implements JsonSerializer<RegionVisits> {
    @Override
    public JsonElement serialize(RegionVisits regionVisits, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jObject = new JsonObject();
        jObject.addProperty("regionName", regionVisits.getRegionName());
        jObject.addProperty("visitsCount", regionVisits.getVisitsCount());
        return jObject;
    }
}
