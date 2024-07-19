package com.nativemoduleandroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

public class ImagePickerModule extends ReactContextBaseJavaModule {
    private Promise resultPromise;
    private static final int IMAGE_PICKER_REQUEST = 1;

    public ImagePickerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(mActivityEventListener);
    }

    @Override
    public String getName() {
        return "ImagePickerModule";
    }

    @ReactMethod
    public void pickImage(Promise promise) {
        Activity currentActivity = getCurrentActivity();

        if (currentActivity == null) {
            promise.reject("ACTIVITY_NOT_FOUND", "Activity not found");
            return;
        }

        resultPromise = promise;

        try {
            Intent imagePickerIntent = new Intent(Intent.ACTION_PICK);
            imagePickerIntent.setType("image/*");

            currentActivity.startActivityForResult(imagePickerIntent, IMAGE_PICKER_REQUEST);
        } catch (Exception e) {
            resultPromise.reject("IMAGE_PICKER_MODULE_ERROR", e.getMessage());
            resultPromise = null;
        }
    }

    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {
        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
            if (requestCode == IMAGE_PICKER_REQUEST) {
                if (resultPromise != null) {
                    if (resultCode == Activity.RESULT_OK) {
                        Uri uri = data.getData();
                        WritableMap map = Arguments.createMap();
                        map.putString("uri", uri.toString());
                        resultPromise.resolve(map);
                    } else {
                        resultPromise.reject("IMAGE_PICKER_CANCELLED", "Image picker was cancelled");
                    }
                    resultPromise = null;
                }
            }
        }
    };
}