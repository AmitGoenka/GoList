package org.agoenka.golist.utils;

import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Author: agoenka
 * Created At: 9/30/2016
 * Version: ${VERSION}
 */

public class DateUtils {

    public static String formatDate(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            return String.valueOf(month) + "/" + day + "/" + year;
        }
        return null;
    }

    public static Calendar getCalendar(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        }
        return null;
    }

    public static Date getDate(DatePicker picker) {
        if (picker != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(picker.getYear(), picker.getMonth(), picker.getDayOfMonth());
            return calendar.getTime();
        }
        return null;
    }

}
