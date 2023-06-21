package com.example.demo.utils;

public class NumberUtils {

    public static boolean isInteger(String value){
        if (value == null){
            return false;
        }
        try{
            Integer integer = Integer.parseInt(value);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
