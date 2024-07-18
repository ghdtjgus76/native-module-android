package com.nativemoduleandroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class ImagePickerModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private static final int IMAGE_PICKER_REQUEST = 1;
    private Promise mPickerPromise;
    public ImagePickerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(this);
    }

    @Override
    public String getName() {
        return "ImagePickerModule";
    }

    @ReactMethod
    public void pickImage(final Promise promise) {
        Activity currentActivity = getCurrentActivity();

        if (currentActivity == null) {
            promise.reject("ACTIVITY_NOT_FOUND", "Activity not found");
            return;
        }

        mPickerPromise = promise;

        try {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK);
            galleryIntent.setType("image/*");

            Intent chooserIntent = Intent.createChooser(galleryIntent, "Pick an image");
            currentActivity.startActivityForResult(chooserIntent, IMAGE_PICKER_REQUEST);
        } catch (Exception e) {
            mPickerPromise.reject("PICKER_ERROR", e.getMessage());
            mPickerPromise = null;
        }
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == IMAGE_PICKER_REQUEST) {
            if (mPickerPromise != null) {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    Uri uri = data.getData();
                    if (uri != null) {
                        mPickerPromise.resolve(uri.toString());
                    } else {
                        mPickerPromise.reject("NO_IMAGE_DATA", "No image data found");
                    }
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    mPickerPromise.reject("USER_CANCELLED", "User cancelled image picker");
                }

                mPickerPromise = null;
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {}
}
