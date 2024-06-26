package br.edu.ifs.rss_g1.notices_g1.utils;

import org.springframework.expression.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDate {
    public static Date parseDate(String date , SimpleDateFormat dateFormat) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
