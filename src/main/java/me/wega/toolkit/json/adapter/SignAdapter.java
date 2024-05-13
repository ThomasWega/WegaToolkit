package me.wega.toolkit.json.adapter;

import com.google.gson.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import java.lang.reflect.Type;

public class SignAdapter implements JsonSerializer<Sign>, JsonDeserializer<Sign> {

    @Override
    public JsonElement serialize(Sign sign, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(sign.getBlock(), Block.class);
    }

    @Override
    public Sign deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        Block block = jsonDeserializationContext.deserialize(jsonElement, Block.class);
        return ((Sign) block.getState());
    }
}