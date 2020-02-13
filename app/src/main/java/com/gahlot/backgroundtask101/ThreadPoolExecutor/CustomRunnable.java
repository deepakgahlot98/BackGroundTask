package com.gahlot.backgroundtask101.ThreadPoolExecutor;

import android.os.Message;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class CustomRunnable implements Runnable {

    // Keep a weak reference to ui callback, so we can send a message to the UI thread
    // Use weak reference to avoid leaking activity object
    private WeakReference<UiThreadCallback> uiThreadCallbackWeakReference;
    private QuoteFetcher quoteFetcher = QuoteFetcher.getInstance();

    @Override
    public void run() {

        String quote = "";

        try {
            // Before running some lengthy and blocking work, check if the thread has been interrupted
            if (Thread.interrupted()) throw new InterruptedException();

            // In real world app, you might do some blocking IO operation
            // In this example, I just let the thread sleep for 3 second
            try {
                quote = quoteFetcher.makeRequest();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // After work is finished, send a message to UI thread
            Message message = Util.createMessage(Util.MESSAGE_ID,
                     quote);

            if(uiThreadCallbackWeakReference != null && uiThreadCallbackWeakReference.get() != null) {
                uiThreadCallbackWeakReference.get().publishToUiThread(message);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
