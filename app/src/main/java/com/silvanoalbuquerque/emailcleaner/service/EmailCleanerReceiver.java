package com.silvanoalbuquerque.emailcleaner.service;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class EmailCleanerReceiver extends ResultReceiver {

    private IEventsListener eventsListener;

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
