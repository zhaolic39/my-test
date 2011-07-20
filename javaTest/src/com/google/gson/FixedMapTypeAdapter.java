package com.google.gson;  
import java.lang.reflect.Type;  
import java.util.Map;  
import java.util.Set;  
import com.google.gson.DefaultTypeAdapters.MapTypeAdapter;  

public class FixedMapTypeAdapter extends MapTypeAdapter {  
    @SuppressWarnings("unchecked")  
    @Override  
    public JsonElement serialize(Map src, Type typeOfSrc,  
            JsonSerializationContext context) {  
        JsonObject map = new JsonObject();  
        for (Map.Entry entry : (Set<Map.Entry>) src.entrySet()) {  
            Object value = entry.getValue();  
            JsonElement valueElement;  
            if (value == null) {  
                valueElement = JsonNull.createJsonNull();  
            } else {  
                Type childType = value.getClass();  
                valueElement = context.serialize(value, childType);  
            }  
            map.add(String.valueOf(entry.getKey()), valueElement);  
        }  
        return map;  
    }  
    @SuppressWarnings("unchecked")  
    @Override  
    public Map deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {  
        // Use ObjectConstructor to create instance instead of hard-coding a  
        // specific type.  
        // This handles cases where users are using their own subclass of Map.  
        Map<Object, Object> map = constructMapType(typeOfT, context);  
        TypeInfoMap mapTypeInfo = new TypeInfoMap(typeOfT);  
        for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {  
            Object key = context.deserialize(new JsonPrimitive(entry.getKey()), mapTypeInfo.getKeyType());  
            JsonElement jsonElement = entry.getValue();  
            String jsonValue = jsonElement.toString();  
            Type valueType = mapTypeInfo.getValueType();  
            if(jsonValue.startsWith("{")){  
                valueType = new ParameterizedTypeImpl(Map.class, new Type[]{String.class, Object.class}, null);  
            }  
            Object value = context.deserialize(jsonElement, valueType);  
            map.put(key, value);  
        }  
        return map;  
    }  
    
    @SuppressWarnings("unchecked")  
    private Map constructMapType(Type mapType,  
            JsonDeserializationContext context) {  
        JsonDeserializationContextDefault contextImpl = (JsonDeserializationContextDefault) context;  
        ObjectConstructor objectConstructor = contextImpl  
                .getObjectConstructor();  
        return (Map) objectConstructor.construct(mapType);  
    }  
}