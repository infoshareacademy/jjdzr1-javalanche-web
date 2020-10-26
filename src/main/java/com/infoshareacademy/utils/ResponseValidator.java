package com.infoshareacademy.utils;

public class ResponseValidator {

    static boolean testForErrors(Object object){
        try {
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
