package com.daredevil.appa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.daredevil.appa.Model.Transaksi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class donasiUang extends AppCompatActivity {
    public String id;
    DatabaseReference panti;
    DatabaseReference databaseReference;
    Integer i=1000;
    RadioButton rbBNI;
    RadioButton rbBRI;
    RadioButton rbBCA;
    RadioButton rbMandiri;
    EditText edRek;
    EditText edNominal;
    private String KEY_ID="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_uang);
        rbBNI = (RadioButton)findViewById(R.id.rb_BNI);
        rbBRI = (RadioButton)findViewById(R.id.rb_BRI);
        rbMandiri = (RadioButton)findViewById(R.id.rb_mandiri);
        rbBCA = (RadioButton)findViewById(R.id.rb_BCA);
        edRek=findViewById(R.id.ed_rekPanti);
        edNominal=findViewById(R.id.ed_nominalDonasi);
    }

    public void checkedButton(View view) {
        boolean checked=((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.rb_BNI:
                if(checked){
                    rbBRI.setChecked(false);
                    rbBCA.setChecked(false);
                    rbMandiri.setChecked(false);
                }
                break;
            case  R.id.rb_BCA:
                if(checked){
                    rbBRI.setChecked(false);
                    rbBNI.setChecked(false);
                    rbMandiri.setChecked(false);
                }
                break;
            case R.id.rb_BRI:
                if(checked){
                    rbBNI.setChecked(false);
                    rbBCA.setChecked(false);
                    rbMandiri.setChecked(false);
                }
                break;
            case R.id.rb_mandiri:
                if(checked){
                    rbBRI.setChecked(false);
                    rbBCA.setChecked(false);
                    rbBNI.setChecked(false);
                }
                break;
        }
            }

    public void goKonfirmasi_uang(View view) {
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Transaksi");
        databaseReference.keepSynced(true);
        panti=FirebaseDatabase.getInstance().getReference().child("Panti").child("000");
        panti.keepSynced(true);
        addArrayList();
        Intent intent=new Intent(this,konfirmasiDonasiUang.class);
        intent.putExtra(KEY_ID,id);
        startActivity(intent);


    }

    private void addArrayList(){
        String rek=edRek.getText().toString().trim();
        final Integer nominal=Integer.parseInt(edNominal.getText().toString());
        panti.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String temp=dataSnapshot.child("donasi").getValue().toString();
                dataSnapshot.getRef().child("donasi").setValue(nominal+Integer.parseInt(temp));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        id=databaseReference.push().getKey();
        Date tgl=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM y");
        String formattedDateString = formatter.format(tgl);
        Transaksi transaksi=new Transaksi("Uang",id,"Denys",rek.toString(),nominal,formattedDateString);
        databaseReference.child(id).child("jenis").setValue("Uang");
        databaseReference.child(id).child("id").setValue(id);
        databaseReference.child(id).child("user").setValue("Denys");
        databaseReference.child(id).child("noRek").setValue(rek.toString());
        databaseReference.child(id).child("jumlah").setValue(nominal);
        databaseReference.child(id).child("tanggal").setValue(formattedDateString);
        i++;

    }
}
