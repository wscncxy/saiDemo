package com.sai.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        String json="{\"test\":123}";
        try{
        Map<String, Object> map = mapper.readValue(json, Map.class);
            map.put("add",1);
        System.out.println(mapper.writeValueAsString(map));
            HashMap
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
