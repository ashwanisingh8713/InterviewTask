package com.interview.task.activity;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.interview.task.R;
import com.interview.task.util.Alerts;
import com.interview.task.util.IntentUtils;
import com.multidots.fingerprintauth.AuthErrorCodes;
import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;

/**
 * Created by ashwanisingh on 24/06/18.
 */
public class MainActivity extends BaseActivity implements FingerPrintAuthCallback {

    private TextView mAuthMsgTv;
    private ViewSwitcher mSwitcher;
    private FingerPrintAuthHelper mFingerPrintAuthHelper;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initializing the views
        mSwitcher = findViewById(R.id.viewSwitcher);
        mAuthMsgTv = findViewById(R.id.pin_et);
        EditText pinEt = findViewById(R.id.pin_et);


        // Text Change Listener for Passwd
        pinEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("1234")) {
                    Alerts.showToast(MainActivity.this, "Authentication succeeded.");
                    IntentUtils.openHomeActivity(MainActivity.this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Initializing of FingerPrintAuthHelper
        mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(this, this);


    }


    @Override
    protected void onResume() {
        super.onResume();
        mAuthMsgTv.setText("Please scan your finger");

        //start finger print authentication
        mFingerPrintAuthHelper.startAuth();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFingerPrintAuthHelper.stopAuth();
    }


    @Override
    public void onNoFingerPrintHardwareFound() {
        mAuthMsgTv.setText("Your device does not have finger print scanner. Please type 1234 to authenticate.");
        mSwitcher.showNext();
    }

    @Override
    public void onNoFingerPrintRegistered() {
        mAuthMsgTv.setText("There are no finger prints registered on this device. " +
                "Please register your finger from settings.");
    }

    @Override
    public void onBelowMarshmallow() {
        mAuthMsgTv.setText("You are running older version of android that does not support finger print authentication. Please type 1234 to authenticate.");
        mSwitcher.showNext();
    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {
        Alerts.showToast(this, "Authentication succeeded.");
        IntentUtils.openHomeActivity(this);
    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {
        switch (errorCode) {
            case AuthErrorCodes.CANNOT_RECOGNIZE_ERROR:
                mAuthMsgTv.setText("Cannot recognize your finger print. Please try again.");
                break;
            case AuthErrorCodes.NON_RECOVERABLE_ERROR:
                mAuthMsgTv.setText("Cannot initialize finger print authentication. Please type 1234 to authenticate.");
                mSwitcher.showNext();
                break;
            case AuthErrorCodes.RECOVERABLE_ERROR:
                mAuthMsgTv.setText(errorMessage);
                break;
        }
    }
}
