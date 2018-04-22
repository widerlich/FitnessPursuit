package pursuit.fitness.fitnesspursuit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import static android.app.PendingIntent.FLAG_ONE_SHOT;


public class NotificationReceiver extends BroadcastReceiver{

    public void onReceive(Context context, Intent in) {

        showNotification(context);
    }

    private void showNotification(Context context) {
        Intent myIntent = new Intent(context, CalendarActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                myIntent, FLAG_ONE_SHOT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.fitnesspursuit_launcher_icon)
                        .setContentTitle("Reminder")
                        .setContentText("Dont forget to log your meals for today!")
                        .setContentIntent(contentIntent);
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());

    }
}
