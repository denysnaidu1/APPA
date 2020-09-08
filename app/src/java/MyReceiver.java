package com.daredevil.appa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.w("Test Receiver","message");
        Log.w("Test Receiver","Message : "+ intent.getAction());
    }


}
