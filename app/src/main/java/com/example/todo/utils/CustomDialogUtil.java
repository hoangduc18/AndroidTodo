package com.example.todo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TimePicker;

import com.example.todo.IActionUpdateDateTime;
import com.example.todo.R;

public class CustomDialogUtil {
    public static String selectedDate = "";
    public static String selectedTime = "";

    public static void showDatePickerDialog(Context context, IActionUpdateDateTime callback) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_date_picker_dialog, null);
        CalendarView datePicker = dialogView.findViewById(R.id.datePicker);
        dialog.setView(dialogView);
        datePicker.setOnDateChangeListener((calendarView, year, month, date) -> selectedDate = year + "-" + (month + 1) + "-" + date);
        dialog.setPositiveButton("OK", (dialogInterface, i) -> {
            dialogInterface.dismiss();
            callback.updatedDate(selectedDate);
        });
        dialog.setNegativeButton("CANCEL", (dialogInterface, i) -> dialogInterface.dismiss());
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    public static void showTimePickerDialog(Context context, IActionUpdateDateTime callback) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View timeDialog = LayoutInflater.from(context).inflate(R.layout.layout_time_picker_dialog, null);
        TimePicker timePicker = timeDialog.findViewById(R.id.timePicker);
        dialog.setView(timeDialog);
        timePicker.setOnTimeChangedListener((timePicker1, hour, minute) -> selectedTime = hour + ":" + minute);
        dialog.setPositiveButton("OK", (dialogInterface, i) -> {
            dialogInterface.dismiss();
            callback.updatedTime(selectedTime);
        });
        dialog.setNegativeButton("CANCEL", (dialogInterface, i) -> dialogInterface.dismiss());
        AlertDialog alert = dialog.create();
        alert.show();
    }
}
