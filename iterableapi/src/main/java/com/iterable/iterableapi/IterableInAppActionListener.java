package com.iterable.iterableapi;

import android.app.Dialog;
import android.view.View;

/**
 * Created by David Truong dt@iterable.com.
 *
 *  IterableInAppActionListener is a custom OnClickListener
 */
public class IterableInAppActionListener implements View.OnClickListener {
    Dialog dialog;
    int index;
    String actionName;
    IterableHelper.IterableActionHandler onClickCallback;
    InAppTrackParams trackParams;

    /**
     * A custom onClickListener which stores data about the dialog context.
     * @param dialog
     * @param index
     * @param actionName
     * @param trackParams
     * @param onClickCallback
     */
    IterableInAppActionListener(Dialog dialog, int index, String actionName, InAppTrackParams trackParams, IterableHelper.IterableActionHandler onClickCallback){
        this.index = index;
        this.actionName  = actionName;
        this.onClickCallback = onClickCallback;
        this.dialog = dialog;
        this.trackParams = trackParams;
    }

    /**
     * Dismisses the dialog when a click is processed.
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (trackParams != null) {
            IterableApi.sharedInstance.trackInAppClick(trackParams.campaignId, trackParams.templateId, index);
        }
        if (onClickCallback != null) {
            onClickCallback.execute(actionName);
        }
        dialog.dismiss();
    }
}
