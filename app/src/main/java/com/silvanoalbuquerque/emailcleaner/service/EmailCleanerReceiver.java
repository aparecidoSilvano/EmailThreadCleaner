package com.silvanoalbuquerque.emailcleaner.service;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class EmailCleanerReceiver extends ResultReceiver {

    private IEventsListener eventsListener;

    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    public EmailCleanerReceiver(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (eventsListener != null) {
            eventsListener.onReceiveResult(resultCode, resultData);
        }
    }

    public void setReceiver(IEventsListener eventsListener) {
        this.eventsListener = eventsListener;
    }

    public interface IEventsListener {
        void onReceiveResult(int resultCode, Bundle resultData);
    }
}
