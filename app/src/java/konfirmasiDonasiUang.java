package com.daredevil.appa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class konfirmasiDonasiUang extends AppCompatActivity {
    private donasiUang donasi_uang;
    private TextView idTrans;
    private TextView nominal;
    private TextView noRek;
    private TextView tanggal;
    private String KEY_ID="1";
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_donasi_uang);
        idTrans=findViewById(R.id.idTransaksi);
        nominal=findViewById(R.id.txt_nominal);
        noRek=findViewById(R.id.txt_noRek);
        tanggal=findViewById(R.id.txtDate);
        Bundle extras=getIntent().getExtras();
        id=extras.getString(KEY_ID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        donasi_uang=new donasiUang();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Transaksi").child(id);
        databaseReference.keepSynced(true);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String id1=dataSnapshot.child("id").getValue().toString();
                String jlh=dataSnapshot.child("jumlah").getValue().toString();
                String rek=dataSnapshot.child("noRek").getValue().toString();
                Date tgl=new Date();
                int x=tgl.getYear();
                int y=tgl.getMonth();
                int z=tgl.getDate()+2;
                tgl=new Date(x,y,z);
                SimpleDateFormat formatter = new SimpleDateFormat("EEEE, d MMMM y");
                String formattedDateString = formatter.format(tgl);
                tanggal.setText("Dimohon agar melakukan transaksi sebelum "+formattedDateString+" atau donasi akan dibatalkan.");
                idTrans.setText(("Donasi dengan ID : "+id1+" sedang diproses"));
                nominal.setText(("Rp "+jlh));
                noRek.setText(rek);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void backToHome(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
