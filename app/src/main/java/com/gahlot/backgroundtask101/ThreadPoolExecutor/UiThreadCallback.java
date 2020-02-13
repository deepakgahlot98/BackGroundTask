package com.gahlot.backgroundtask101.ThreadPoolExecutor;

import android.os.Message;

public interface UiThreadCallback {

        void publishToUiThread(Message message);
}
