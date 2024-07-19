package com.nativemoduleandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class PhoneModule extends ReactContextBaseJavaModule {
    private Promise resultPromise;
    private String phoneNumber;
    private static final int REQUEST_PHONE_CALL = 1;

    public PhoneModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(activityEventListener);
    }

    @Override
    public String getName() {
        return "PhoneModule";
    }

    @ReactMethod
    public void makeCall(String phoneNumber, Promise promise) {
        Activity currentActivity = getCurrentActivity();

        if (currentActivity == null) {
            promise.reject("ACTIVITY_NOT_FOUND", "Activity not found");
            return;
        }

        this.phoneNumber = phoneNumber;
        this.resultPromise = promise;

        checkPermissionAndRequestCall(currentActivity);
    }

    private void startCall(Activity activity, String phoneNumber) {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            activity.startActivity(callIntent);
            resultPromise.resolve("Call started successfully");
        } catch (Exception e) {
            resultPromise.reject("PHONE_MODULE_ERROR", e.getMessage());
            resultPromise = null;
        }
    }

    private void checkPermissionAndRequestCall(Activity currentActivity) {
        if (ContextCompat.checkSelfPermission(currentActivity, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(currentActivity, new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
        } else {
            startCall(currentActivity, phoneNumber);
        }
    }

    private final ActivityEventListener activityEventListener = new BaseActivityEventListener() {
        public void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
            if (requestCode == REQUEST_PHONE_CALL) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCall(activity, phoneNumber);
                } else {
                    if (resultPromise != null) {
                        resultPromise.reject("PERMISSION_DENIED", "CALL_PHONE permission denied");
                        resultPromise = null;
                    }
                }
            }
        }
    };
}
