package com.gahlot.backgroundtask101.ThreadPoolExecutor;

import android.os.Message;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

public class CustomCallable implements Callable {

    // Keep a weak reference to the CustomThreadPoolManager singleton object, so we can send a
    // message. Use of weak reference is not a must here because CustomThreadPoolManager lives
    // across the whole application lifecycle
    private WeakReference<CustomThreadPoolManager> mCustomThreadPoolManagerWeakReference;
    private QuoteFetcher quoteFetcher = QuoteFetcher.getInstance();

    @Override
    public Object call() {

        String quote = "";

        try {
            // check if thread is interrupted before lengthy operation
            if (Thread.interrupted()) throw new InterruptedException();

            try {
                quote = quoteFetcher.makeRequest();
            } catch (IOException e) {
                e.printStackTrace();
            }// After work is finished, send a message to CustomThreadPoolManager
            Message message = Util.createMessage(Util.MESSAGE_ID,
                    Thread.currentThread().getName() + quote);

            if(mCustomThreadPoolManagerWeakReference != null
                    && mCustomThreadPoolManagerWeakReference.get() != null) {

                mCustomThreadPoolManagerWeakReference.get().sendMessageToUiThread(message);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setCustomThreadPoolManager(CustomThreadPoolManager customThreadPoolManager) {
        this.mCustomThreadPoolManagerWeakReference = new WeakReference<CustomThreadPoolManager>(customThreadPoolManager);
    }
}
