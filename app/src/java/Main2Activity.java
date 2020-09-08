package com.daredevil.appa;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    /*SimpleAsycnTask myTask;
    ProgressBar bar;
    int click=0;
    Button btn;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notif);
    }

/*
    public void StartTask(View view){
        TextView tv = findViewById(R.id.tv1);
        Button btn = findViewById(R.id.btn1);
        if(click==0) {
            click=1;
            btn.setText("Start Tast");
            bar = findViewById(R.id.indeterminateBar);
            myTask = new SimpleAsycnTask(tv, bar);
            tv.setText("Execute");
            myTask.execute("Task 1");
        }
        else{
            btn.setText("Cancel");
            cancel();
            click=0;
        }
    }
    public void cancel(){
        bar=findViewById(R.id.indeterminateBar);
        if(bar.getProgress()<100) {
            myTask.cancel(true);

        }
        else{
            myTask.cancel(false);
        }
    }*/

    private static final int NOTIV_ID1=0;

    public static final String YES_ACTION ="YES_ACTION";
    public static final String NO_ACTION="NO_ACTION";
    public void sendNotiv(View view){
        NotificationCompat.Builder mbuild= new NotificationCompat.Builder(this,Notification.EXTRA_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("App Notif")
                .setContentText("This is first Notif")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle()
                    .bigText
                            ("Much longer text"))
                .setOngoing(true);

        NotificationManagerCompat notificationmanager= NotificationManagerCompat.from(this);
        notificationmanager.notify(NOTIV_ID1,mbuild.build());
    }

    public  void updateNotif(View view){
        NotificationCompat.Builder mbuild= new NotificationCompat.Builder(this,Notification.EXTRA_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("App Notif")
                .setContentText("This is first Notif")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText
                                ("Much longer text"));

        Intent yesIntent= new Intent(this,MyReceiver.class);
        yesIntent.setAction(YES_ACTION);

        mbuild.setAutoCancel(true);

        PendingIntent yesPending=PendingIntent.getBroadcast(this,0,yesIntent,0);

        Intent noIntent=new Intent (this,MyReceiver.class);
        noIntent.setAction(NO_ACTION);

        PendingIntent noPendingIntent=PendingIntent.getBroadcast(this,0,noIntent,0);

        mbuild.addAction(R.drawable.ic_launcher_background,"Yes",yesPending);

        mbuild.addAction(R.drawable.ic_launcher_background,"No",noPendingIntent);


        NotificationManagerCompat notificationmanager= NotificationManagerCompat.from(this);
        notificationmanager.notify(NOTIV_ID1,mbuild.build());
    }
}
