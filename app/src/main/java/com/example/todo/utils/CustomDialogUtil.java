package com.example.todo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

import com.example.todo.ActionUpdate;
import com.example.todo.AddEditTaskActivity;
import com.example.todo.R;

public class CustomDialogUtil {
    public static String selectedDate = "";
    public static String selectedTime = "";
    public static void showDatePickerDialog(Context context, ActionUpdate callback){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_date_picker_dialog, null);
        CalendarView datePicker = dialogView.findViewById(R.id.datePicker);
        dialog.setView(dialogView);
        datePicker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                selectedDate = year + "-"+ (month+1) +"-"+ date;
            }
        });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                callback.updatedDate(selectedDate);
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    public static void showTimePickerDialog(Context context, ActionUpdate callback){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View timeDialog = LayoutInflater.from(context).inflate(R.layout.layout_time_picker_dialog, null);
        TimePicker timePicker = timeDialog.findViewById(R.id.timePicker);
        dialog.setView(timeDialog);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                selectedTime = hour +":"+minute;
            }
        });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                callback.updatedTime(selectedTime);
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alert = dialog.create();
        alert.show();
    }
}
