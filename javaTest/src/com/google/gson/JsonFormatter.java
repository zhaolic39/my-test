package com.google.gson;

import java.io.StringReader;

import com.google.gson.stream.JsonReader;

public class JsonFormatter {
  public static void main(String[] args) {
    String s = "{\"child\":{\"name\":\"hank\"},\"age\":\"10\",\"name\":\"jhon\",\"addr\":\"street 11\"}";
    
    JsonReader jr = new JsonReader(new StringReader(s));
    JsonElement root = Streams.parse(jr);
    
    GsonBuilder builder = new GsonBuilder();
    builder.setPrettyPrinting();
    Gson gson = builder.create();
    s= gson.toJson(root);
    System.out.println(s);
  }
  
  public static String formtJson(String json) {
    JsonReader jr = new JsonReader(new StringReader(json));
    JsonElement root = Streams.parse(jr);
    
    GsonBuilder builder = new GsonBuilder();
    builder.setPrettyPrinting();
    Gson gson = builder.create();
    json = gson.toJson(root);
    return json;
  }
}
