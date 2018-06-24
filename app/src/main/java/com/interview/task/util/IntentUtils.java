package com.interview.task.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.interview.task.activity.HomeActivity;

/**
 * Created by ashwanisingh on 25/06/18.
 */
public class IntentUtils {

    /**
     * Opens {@link HomeActivity}
     * @param context
     */
    public static final void openHomeActivity(AppCompatActivity context) {
        context.startActivity(new Intent(context, HomeActivity.class));
        context.finish();
    }
}
