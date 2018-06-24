package com.interview.task.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ashwanisingh on 25/06/18.
 */
public class Alerts {

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
