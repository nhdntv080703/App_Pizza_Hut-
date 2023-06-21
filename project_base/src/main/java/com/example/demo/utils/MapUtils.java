package com.example.demo.utils;

import java.util.Map;

public class MapUtils {

    public static <T> T getObject(Map<String, Object> maps, String key, Class<T> tClass){
        try{
            Object object = maps.getOrDefault(key, null);
            if(object != null){
                if(tClass.getTypeName().equals("java.lang.Long")){
//                    object = Long.valueOf(object.toString());
                    object = object != "" ? Long.valueOf(object.toString()) : null;
                } else if(tClass.getTypeName().equals("java.lang.Integer")){
//                    object = Integer.valueOf(object.toString());
                    object = object != "" ? Integer.valueOf(object.toString()) : null;
                }else if(tClass.getTypeName().equals("java.lang.String")){
                    object = object.toString();
                }
                return tClass.cast(object);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
}
