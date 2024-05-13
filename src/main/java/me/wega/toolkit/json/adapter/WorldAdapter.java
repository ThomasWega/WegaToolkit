package me.wega.toolkit.json.adapter;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.lang.reflect.Type;

public class WorldAdapter implements JsonSerializer<World>, JsonDeserializer<World> {
    @Override
    public JsonElement serialize(World world, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(world.getName());
    }

    @Override
    public World deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return Bukkit.getWorld(jsonElement.getAsString());
    }
}