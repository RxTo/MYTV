package com.example.yuki.yukitv.utils;

import java.text.DecimalFormat;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/15
 */

public class DecimalFormatUtil {

    private static final DecimalFormat decimalFormat = new DecimalFormat();


    public static String formatW(int value){
        if(value>=10000){
            float l = value/10000.0f;

            return format(l);
        }
        return String.valueOf(value);
    }

    public static String format(float value){
        decimalFormat.applyPattern("#.#'W'");
        return decimalFormat.format(value);
    }
}
