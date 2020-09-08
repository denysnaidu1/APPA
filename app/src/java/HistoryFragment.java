package com.daredevil.appa;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daredevil.appa.Interface.ItemClickListener;
import com.daredevil.appa.Model.Transaksi;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Date;


public class HistoryFragment extends Fragment {

    Integer j=0;
    private View v;
    RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Transaksi");
        databaseReference.keepSynced(true);
        return inflater.inflate(R.layout.history_fragment_layout,container,false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v=view;
    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerView=(RecyclerView)v.findViewById(R.id.history_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerAdapter<Transaksi,HistoryViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Transaksi, HistoryViewHolder>
                (Transaksi.class,R.layout.history_item,HistoryViewHolder.class,databaseReference) {
            @Override
            protected void populateViewHolder(HistoryViewHolder viewHolder, Transaksi model, int position) {
                viewHolder.setId(model.getId());
                viewHolder.setJenis(model.getJenis());
                viewHolder.setNoRek(model.getNoRek());
                viewHolder.setJumlahTrf(model.getJumlah());
                j=model.getJumlah();
                viewHolder.setTanggal(model.getTanggal());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class HistoryViewHolder extends  RecyclerView.ViewHolder{
        View mView;
        private ItemClickListener itemClickListener;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setId(String id){
            TextView post_title=(TextView)mView.findViewById(R.id.textId);
            post_title.setText("ID Donasi       : "+id);
        }

        public void setNoRek(String rek){
            TextView post_title=(TextView)mView.findViewById(R.id.textRek);
            post_title.setText("No Rekening : "+rek);
        }

        public void setTanggal(String tanggal){
            TextView post_tanggal=(TextView)mView.findViewById(R.id.textTanggal);
            post_tanggal.setText("Tanggal         : "+tanggal);
        }

        public void setJumlahTrf(Integer jlh){
            TextView post_jlh=(TextView)mView.findViewById(R.id.textJumlah);
            post_jlh.setText("Jumlah          : "+jlh.toString());
        }

        public void setJenis(String jenis){
            TextView post_jenis=(TextView)mView.findViewById(R.id.textJenis);
            post_jenis.setText("Jenis              : "+jenis);
        }
    }
}
