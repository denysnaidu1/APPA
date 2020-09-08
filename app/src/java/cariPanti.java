package com.daredevil.appa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.daredevil.appa.Interface.ItemClickListener;
import com.daredevil.appa.Model.Panti;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.core.Context;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class cariPanti extends AppCompatActivity {

    DatabaseReference panti;
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Panti,dataViewHolder> recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;

    Intent intent;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_panti);

        //Load panti
        recyclerView=(RecyclerView) findViewById(R.id.recycle_menu123);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SearchView searchView1=(SearchView)findViewById(R.id.searchview);
        searchView1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebaseSearch(newText);
                return false;
            }
        });
    }


    private void firebaseSearch(String searchText){
        Query firebaseSearchQuery=panti.orderByChild("Nama").startAt(searchText).endAt(searchText+"\uf8ff");

        FirebaseRecyclerAdapter<Panti,dataViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Panti, dataViewHolder>
                (Panti.class, R.layout.testinglayout, dataViewHolder.class,firebaseSearchQuery) {
            @Override
            protected void populateViewHolder(dataViewHolder viewHolder, Panti model, int position) {
                viewHolder.setTitle(model.getNama());
                viewHolder.setDonasi(model.getDonasi());
                viewHolder.setFoto(getApplicationContext(), model.getImage());
                final Panti clickitem=model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        goProfilPanti();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        panti=FirebaseDatabase.getInstance().getReference().child("Panti");
        panti.keepSynced(true);
        FirebaseRecyclerAdapter<Panti, dataViewHolder> adapter = new FirebaseRecyclerAdapter<Panti, dataViewHolder>
                (Panti.class, R.layout.testinglayout, dataViewHolder.class, panti) {
            @Override
            protected void populateViewHolder(dataViewHolder viewHolder, Panti model, int position) {
                viewHolder.setTitle(model.getNama());
                viewHolder.setDonasi(model.getDonasi());
                viewHolder.setFoto(getApplicationContext(), model.getImage());
                final Panti clickitem=model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        goProfilPanti();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

   public static class dataViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        View mView;
        private ItemClickListener itemClickListener;
        public dataViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            itemView.setOnClickListener(this);
        }

        public void setTitle(String title){
            TextView post_title=(TextView)mView.findViewById(R.id.panti_name12);
            post_title.setText(title);
        }

        public void setDonasi(Integer donasi_sementara){
            TextView post_donasi=(TextView)mView.findViewById(R.id.donasi_sementara);
            post_donasi.setText("Rp "+donasi_sementara.toString());
        }

        public void setFoto(Context ctx, String image){
            ImageView post_Image=(ImageView)mView.findViewById(R.id.panti_image1);
            Picasso.with(ctx).load(image).into(post_Image);
        }

        public void setItemClickListener (ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }
    }

    public void goProfilPanti() {
        intent=new Intent(this,profil_Panti.class);
        startActivity(intent);
    }
}
