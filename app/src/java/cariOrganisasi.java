package com.daredevil.appa;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.daredevil.appa.Interface.ItemClickListener;
import com.daredevil.appa.Model.Organisasi;
import com.daredevil.appa.Model.Panti;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class cariOrganisasi extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_organisasi);

        databaseReference=FirebaseDatabase.getInstance().getReference().child("Organisasi");
        databaseReference.keepSynced(true);

        recyclerView=(RecyclerView) findViewById(R.id.recycleOrganisasi);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SearchView searchView1=(SearchView)findViewById(R.id.searchview1);
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
        Query firebaseSearchQuery=databaseReference.orderByChild("namaOrganisasi").startAt(searchText).endAt(searchText+"\uf8ff");

        FirebaseRecyclerAdapter<Organisasi,OrganisasiViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Organisasi,OrganisasiViewHolder>
                (Organisasi.class, R.layout.menu_item_organisasi, OrganisasiViewHolder.class,firebaseSearchQuery) {

            @Override
            protected void populateViewHolder(OrganisasiViewHolder viewHolder, Organisasi model, int position) {
                viewHolder.setTitle(model.getNamaOrganisasi());
                viewHolder.setNamaKampus(model.getNamaKampus());
                viewHolder.setFoto(getApplicationContext(), model.getImage());
                final Organisasi clickitem=model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        goProfilOrganisasi();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Organisasi,OrganisasiViewHolder> adapter = new FirebaseRecyclerAdapter<Organisasi, OrganisasiViewHolder>
                (Organisasi.class, R.layout.menu_item_organisasi, OrganisasiViewHolder.class, databaseReference) {

            @Override
            protected void populateViewHolder(OrganisasiViewHolder viewHolder, Organisasi model, int position) {
                viewHolder.setTitle(model.getNamaOrganisasi());
                viewHolder.setNamaKampus(model.getNamaKampus());
                viewHolder.setFoto(getApplicationContext(), model.getImage());
                final Organisasi clickitem=model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        goProfilOrganisasi();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    public static class OrganisasiViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        View mView;
        private ItemClickListener itemClickListener;
        public OrganisasiViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            itemView.setOnClickListener(this);
        }

        public void setTitle(String title){
            TextView post_title=(TextView)mView.findViewById(R.id.nama_organisasi);
            post_title.setText(title);
        }

        public void setNamaKampus(String title){
            TextView post_title=(TextView)mView.findViewById(R.id.nama_kampus);
            post_title.setText(title);
        }

        public void setFoto(Context ctx, String image){
            ImageView post_Image=(ImageView)mView.findViewById(R.id.foto_organisasi);
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

    public void goProfilOrganisasi() {
        intent=new Intent(cariOrganisasi.this,profilOrganisasi.class);
        startActivity(intent);
    }
}
