package com.example.todo.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateConverter {
    public static String getCurrentDate(Context context) {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
        return sdf.format(calendar.getTime());
    }
}
