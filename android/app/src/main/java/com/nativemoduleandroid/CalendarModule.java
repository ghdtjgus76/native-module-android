package com.nativemoduleandroid;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class CalendarModule extends ReactContextBaseJavaModule {
    CalendarModule(ReactApplicationContext context) {
        super(context);
    }

    @ReactMethod
    public void createCalendarEvent(String name, String location, Callback myFailureCallback, Callback mySuccessCallback) {
        Log.d("CalendarModule", "Create event called with name: " + name + " and location: " + location);

        try {
            Integer eventId = 1;
            mySuccessCallback.invoke(eventId);
        } catch (Exception e) {
            myFailureCallback.invoke(e.getMessage());
        }
    }

    @NonNull
    @Override
    public String getName() {
        return "CalendarModule";
    }
}
