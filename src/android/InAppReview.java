package com.adeccoappglobal.inappreview;

import android.app.Activity;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class InAppReview extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("requestReview")) {
            this.requestReview(callbackContext);
            return true;
        }
        return false;
    }

    private void requestReview(CallbackContext callbackContext) {
        final Activity activity = cordova.getActivity();
        final ReviewManager manager = ReviewManagerFactory.create(activity);

        try {
            manager.requestReviewFlow().addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
                @Override
                public void onComplete(Task<ReviewInfo> task) {
                    manager.launchReviewFlow(activity, task.getResult()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                                callbackContext.success();
                        }
                    });
                }
            });
        } catch(Exception e) {
            callbackContext.error(e.getMessage());
        }
    }
}
