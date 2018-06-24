package com.interview.task.util;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by ashwanisingh on 25/06/18.
 */

public class GlideUtil {


    public static void loadImage(Context context, final ImageView imageView, String url, int placeholderResId) {
        GlideApp.with(context)
                .load(url)
                .placeholder(placeholderResId)
                .into(imageView);
    }

}
