package com.systembackpoc.utils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormattUtils {

    public static String decodeParam(String text){
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static Date convertDate(String textDate, Date defaultValue){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return dateFormat.parse(textDate);
        } catch (ParseException e) {
            return defaultValue;
        }
    }

    public static Date adjustsDateEnd(Date date){
        Integer mil = 1000;
        Integer seg = 60;
        Integer min = 60;
        Integer day = 24;
        Integer oneMillisecond = day * min * seg * mil;
        return new Date(date.getTime() + oneMillisecond);
    }
}
