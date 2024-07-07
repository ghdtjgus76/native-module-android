package com.nativemoduleandroid;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class CalendarModule extends ReactContextBaseJavaModule {
    ReactApplicationContext reactContext;
    private int listenerCount = 0;

    CalendarModule(ReactApplicationContext context) {
        super(context);
        this.reactContext = context;
    }

    @ReactMethod
    public void createCalendarEvent(String name, String location, Promise promise) {
        Log.d("CalendarModule", "Create event called with name: " + name + " and location: " + location);

        try {
            Integer eventId = 1;
            promise.resolve(eventId);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    private void sendEvent(String eventName, WritableMap params) {
        this.reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    @ReactMethod
    public void addListener(String eventName) {
        if (listenerCount == 0) {
            WritableMap params = Arguments.createMap();
            params.putString("eventProperty", "eventProperty1");
            sendEvent(eventName, params);
        }
        listenerCount += 1;
    }

    @ReactMethod
    public void removeListeners(int count) {
        listenerCount -= count;
        if (listenerCount < 0) {
            listenerCount = 0;
        }
    }

    @NonNull
    @Override
    public String getName() {
        return "CalendarModule";
    }
}
