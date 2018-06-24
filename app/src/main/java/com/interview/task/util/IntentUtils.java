package com.interview.task.util;

import android.content.Context;
import android.content.Intent;

import com.interview.task.activity.HomeActivity;

/**
 * Created by ashwanisingh on 25/06/18.
 */
public class IntentUtils {

    public static final void openHomeActivity(Context context) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }
}
