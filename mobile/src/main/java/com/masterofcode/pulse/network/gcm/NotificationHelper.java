package com.masterofcode.pulse.network.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.masterofcode.pulse.MainActivity;
import com.masterofcode.pulse.R;
import com.masterofcode.pulse.models.Vote;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public class NotificationHelper {

    static Set<Vote> votes = new HashSet<Vote>();
    private static NotificationManager mNotificationManager;
    private static Context context;

    public static void init(Context context){
        NotificationHelper.context = context;
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }


    public static void cancelAll(){
        if(mNotificationManager != null){
            mNotificationManager.cancelAll();
        }
        votes.clear();
    }

    public static void showNotification(Vote vote) {

// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(context, MainActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        final int size = votes.size();
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher);
        if(size > 0){
            mBuilder.setContentTitle(context.getString(R.string.have_events_to_vote))
                    .setNumber(size);
        }else {
            mBuilder.setContentTitle(vote.getName())
                    .setContentText(context.getString(R.string.vote_me));
        }

        mBuilder.setContentIntent(resultPendingIntent);

// mId allows you to update the notification later on.
        votes.add(vote);
        mNotificationManager.notify(1, mBuilder.build());
    }
}
