package gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * CommonParam及子类对象与Json转换适配器
 * 
 * @author Saintcy Don
 * 
 * @param <T>
 */
public class MapAdapter<T extends Map> implements
    JsonSerializer<T>, JsonDeserializer<T> {

  @SuppressWarnings("unchecked")
  public JsonElement serialize(T src, Type typeOfSrc,
      JsonSerializationContext context) {
    JsonObject commonparam = new JsonObject();
    List params = src.listParams();
    for (int i = 0; i < params.size(); i++) {
      String name = (String) ((Object[]) params.get(i))[0];
      Object value = ((Object[]) params.get(i))[1];
      commonparam.add(name, context.serialize(value));
    }
    return commonparam;
  }

  @SuppressWarnings("unchecked")
  public T deserialize(JsonElement json, Type typeOfT,
      JsonDeserializationContext context) throws JsonParseException {

    T commonparam = null;
    try {
      Class<T> typeAsClass = (Class<T>) typeOfT;
      commonparam = typeAsClass.newInstance();
    } catch (InstantiationException e) {
      throw new JsonParseException(e);
    } catch (IllegalAccessException e) {
      throw new JsonParseException(e);
    }

    if (json.isJsonObject()) {
      JsonObject jsonObj = (JsonObject) json;
      for (Entry<String, JsonElement> en : jsonObj.entrySet()) {
        String name = en.getKey();
        JsonElement value = en.getValue();
        if (value == null || value.isJsonNull()) {
          commonparam.putValue(name, null);
        } else if (value.isJsonArray()) {
          List childList = new ArrayList();
          for (JsonElement child : value.getAsJsonArray()) {
            deserialize(child, childList, typeOfT, context);
          }
          commonparam.putValue(name, childList);
        } else if (value.isJsonPrimitive()) {
          commonparam.putValue(name, context.deserialize(value, String.class));
        } else {
          commonparam.putValue(name, context.deserialize(value, typeOfT));
        }
      }
    }

    return commonparam;
  }

  @SuppressWarnings("unchecked")
  private void deserialize(JsonElement json, List list, Type typeOfT,
      JsonDeserializationContext context) {
    if (json == null || json.isJsonNull()) {

    } else if (json.isJsonArray()) {
      List childList = new ArrayList();
      for (JsonElement child : json.getAsJsonArray()) {
        deserialize(child, childList, typeOfT, context);
      }
      list.add(childList);
    } else if (json.isJsonPrimitive()) {
      list.add(context.deserialize(json, String.class));
    } else {
      list.add(context.deserialize(json, typeOfT));
    }
  }

}
