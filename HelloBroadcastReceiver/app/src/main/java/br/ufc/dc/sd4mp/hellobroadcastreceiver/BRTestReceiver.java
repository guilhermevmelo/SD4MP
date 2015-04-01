package br.ufc.dc.sd4mp.hellobroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by guilherme on 4/1/15.
 */
public class BRTestReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Broadcast received!", Toast.LENGTH_LONG).show();
    }
}
