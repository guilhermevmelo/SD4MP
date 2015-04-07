package br.ufc.dc.sd4mp.alertnotification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by guilherme on 4/6/15.
 */
public class BroadCastReceiverForTheWin extends BroadcastReceiver {
    private boolean BATTERY = false;
    private boolean AIRPLANE = false;
    private boolean POWER_CON = false;
    private boolean POWER_DIS = false;

    protected void createNotification(Context context, String status, String title, String content) {
        NotificationManager manager;
        manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setTicker(status);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);

        Intent intent = new Intent(context, NotificationHandler.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
        builder.setContentIntent(pIntent);
        Notification notification = builder.build();
        manager.notify(R.string.app_name, notification);
    }

    private void sendNotification(Context context, String whatHappened) {
       /* if (((whatHappened.equals("BATTERY_CHANGED")) && BATTERY) ||
            ((whatHappened.equals("AIRPLANE_MODE")) && AIRPLANE) ||
            ((whatHappened.equals("ACTION_POWER_CONNECTED")) && POWER_CON) ||
            ((whatHappened.equals("ACTION_POWER_DISCONNECTED")) && POWER_DIS)) */
            createNotification(context, whatHappened, whatHappened, whatHappened);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case "br.ufc.dc.action.BATTERY_CHANGED_TRUE":
                BATTERY = true;
                break;

            case "br.ufc.dc.action.BATTERY_CHANGED_FALSE":
                BATTERY = false;
                break;

            case "br.ufc.dc.action.AIRPLANE_MODE_TRUE":
                AIRPLANE = true;
                break;

            case "br.ufc.dc.action.AIRPLANE_MODE_FALSE":
                AIRPLANE = false;
                break;

            case "br.ufc.dc.action.ACTION_POWER_CONNECTED_TRUE":
                POWER_CON = true;
                break;

            case "br.ufc.dc.action.ACTION_POWER_CONNECTED_FALSE":
                POWER_CON = false;
                break;

            case "br.ufc.dc.action.ACTION_POWER_DISCONNECTED_TRUE":
                POWER_DIS = true;
                break;

            case "br.ufc.dc.action.ACTION_POWER_DISCONNECTED_FALSE":
                POWER_DIS = false;
                break;

            case "android.intent.action.BATTERY_CHANGED":
                sendNotification(context, "BATTERY_CHANGED");

                break;

            case "android.intent.action.AIRPLANE_MODE":
                sendNotification(context, "AIRPLANE_MODE");
                break;

            case "android.intent.action.ACTION_POWER_CONNECTED":
                sendNotification(context, "ACTION_POWER_CONNECTED");
                break;

            case "android.intent.action.ACTION_POWER_DISCONNECTED":
                sendNotification(context, "ACTION_POWER_DISCONNECTED");
                break;

            default:
                break;
        }


    }
}
