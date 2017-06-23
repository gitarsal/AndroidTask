package com.android.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hp on 6/23/2017.
 */

public class Utils {

    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
