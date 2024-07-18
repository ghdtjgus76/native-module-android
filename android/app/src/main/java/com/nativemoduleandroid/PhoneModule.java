package com.nativemoduleandroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class PhoneModule extends ReactContextBaseJavaModule {
    private Promise resultPromise;

    public PhoneModule(ReactApplicationContext reactContext) {
        super(reactContext);
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

        resultPromise = promise;

        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            currentActivity.startActivity(callIntent);
            resultPromise.resolve("Call started successfully");
        } catch (Exception e) {
            resultPromise.reject("PHONE_MODULE_ERROR", e.getMessage());
            resultPromise = null;
        }
    }
}
