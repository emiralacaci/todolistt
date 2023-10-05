package com.example.todolistnew.utils;

public class Utils {
    public static boolean areNullOrEmpty(String... datas) {
        for(String data : datas){
            if(data==null || data.isEmpty()){
                return true;
            }
        }
        //We need false according to this method. Because if method return false then there is no null or empty input from user.
        return false;
    }
}
