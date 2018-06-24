package com.interview.task.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.interview.task.R;

/**
 * Created by ashwanisingh on 24/06/18.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getLayoutRes();

    /** Toolbar declaration, single implementation can be used in many activity */
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutRes());

        mToolbar = findViewById(R.id.toolbar);

        if(mToolbar != null) {
            mToolbar.findViewById(R.id.back_btn).setOnClickListener((v)-> {
                finish();
            });
        }
    }

}
