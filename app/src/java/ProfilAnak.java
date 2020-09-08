package com.daredevil.appa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProfilAnak extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_anak);
    }

    public void goDataAnak(View view) {
        intent=new Intent(this,dataAnak.class);
        startActivity(intent);
    }
}
