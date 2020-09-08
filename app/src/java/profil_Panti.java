package com.daredevil.appa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class profil_Panti extends AppCompatActivity {
    ViewPager viewpager;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil__panti);
        DatabaseReference panti=FirebaseDatabase.getInstance().getReference().child("Panti");
        panti.keepSynced(true);
        viewpager=findViewById(R.id.viewpager);
        ImageAdapter imageAdapter=new ImageAdapter(this);
        viewpager.setAdapter(imageAdapter);
        this.setTitle("Profil Panti");

        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);
    }

    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            profil_Panti.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewpager.getCurrentItem()==0){
                        viewpager.setCurrentItem(1);
                    }
                    else if(viewpager.getCurrentItem()==1){
                        viewpager.setCurrentItem(2);
                    }
                    else{
                        viewpager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public void goProfilAnak(View view) {
        intent=new Intent(this,ProfilAnak.class);
        startActivity(intent);
    }

    public void goRekening(View view) {
        intent=new Intent(this,noRekening.class);
        startActivity(intent);
    }
}
