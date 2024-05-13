package me.wega.toolkit.json.adapter;

import com.google.gson.*;
import me.wega.toolkit.utils.WGUtils;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import java.lang.reflect.Type;

public class WGRegionAdapter implements JsonSerializer<ProtectedRegion>, JsonDeserializer<ProtectedRegion> {

    @Override
    public JsonElement serialize(ProtectedRegion region, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(region.getId());
    }

    @Override
    public ProtectedRegion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return WGUtils.getRegion(json.getAsString()).orElse(null);
    }
}
