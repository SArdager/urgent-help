package kz.kdlolymp.gynecology.controller.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import kz.kdlolymp.gynecology.entity.Visit;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VisitSerializer implements JsonSerializer<Visit> {
    @Override
    public JsonElement serialize(Visit visit, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jObject = new JsonObject();
        jObject.addProperty("id", visit.getId());
        jObject.addProperty("userName", visit.getUser().getUserFirstname() + " " + visit.getUser().getUserSurname());
        LocalDateTime visitDate = visit.getVisitDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String visitDateString = visitDate.format(formatter);
        jObject.addProperty("visitDate", visitDateString);
        jObject.addProperty("region", visit.getRegion().getRegionName());
        jObject.addProperty("workPlace", visit.getUser().getWorkPlace());
        jObject.addProperty("position", visit.getUser().getPosition());
        return jObject;
    }
}
