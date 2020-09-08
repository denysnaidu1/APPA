package com.daredevil.appa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.daredevil.appa.Model.Transaksi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class donasiBarang extends AppCompatActivity {

    EditText ed1;
    EditText ed2;
    EditText ed3;
    EditText ed4;
    DatabaseReference databaseReference;

    Integer i=0000;

    static final int MY_PERMISSION_REQUEST_CAMERA=1;
    DrawerLayout mDrawerLayout;
    private int REQUEST_CODE=1;
    ImageView imgV;
    Uri image_selected_uri;
    private static final int CAMERA_REQUEST =1002 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_barang);

        ed1=findViewById(R.id.ed_namaPanti);
        ed2=findViewById(R.id.ed_jenisBrg);
        ed3=findViewById(R.id.ed_qty);
        ed4=findViewById(R.id.deskripsi);


    }


    public void goNext(View view) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Transaksi");
        databaseReference.keepSynced(true);
        DatabaseReference panti = FirebaseDatabase.getInstance().getReference().child("Panti").child("000");
        panti.keepSynced(true);
        addArrayList();
        Intent intent=new Intent(this,cariOrganisasi.class);
        startActivity(intent);


    }

    private void addArrayList(){
        //databaseReference.push().setValue(new Transaksi(i.toString(),"Denys","Barang",ed3.getText().toString(),ed2.getText().toString(),ed4.getText().toString()));
        //boolean sama=false;
        Map<String,Transaksi> transaksi=new HashMap<>();
        /*String nama=ed1.getText().toString().trim();
        String id=databaseReference.push().getKey();
        databaseReference.child(id).child("id").setValue(i.toString());
        databaseReference.child(id).child("user").setValue("Denys");
        databaseReference.child(id).child("bentuk").setValue("Barang");
        databaseReference.child(id).child("qty").setValue(ed3.getText().toString());
        databaseReference.child(id).child("jenisBarang").setValue(ed2.getText().toString());
        databaseReference.child(id).child("deskripsiBrg").setValue(ed4.getText().toString());*/
        //i++;

    }
    public void setFoto(View view) {
        imgV=(ImageView)findViewById(R.id.tambahFoto);
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            Uri uri=data.getData();
            try{
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imgV.setImageBitmap(bitmap);
                CircleImageView img=findViewById(R.id.icTambah);
                img.setImageResource(0);
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
