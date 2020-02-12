package com.gahlot.backgroundtask101.basicthreadImplementation;

import android.os.Handler;
import android.widget.ProgressBar;

public class MyThread implements Runnable {

    Handler handler;
    private ProgressBar progressBar;

    public MyThread(Handler handler, ProgressBar progressBar) {
        this.handler = handler;
        this.progressBar = progressBar;
    }

    @Override
    public void run() {
        for (int i = 0; i <=30; i++) {
            final int currentProgressCount = i;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setProgress(currentProgressCount);
                }
            });
        }
    }
}
