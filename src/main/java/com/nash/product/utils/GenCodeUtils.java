package com.nash.product.utils;

import java.util.Random;

public class GenCodeUtils {
    public  static String genCode(){
        String sources = "0123456789";
        Random rand = new Random();
        StringBuffer code = new StringBuffer();
        for (int j = 0; j < 6; j++) {
            code.append(sources.charAt(rand.nextInt(9)) + "");
        }
        return code.toString();
    }
}
