package com.daredevil.appa;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.daredevil.appa.Model.Event;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    View v;
    DatabaseReference event;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        event = FirebaseDatabase.getInstance().getReference().child("Event");
        event.keepSynced(true);
        return inflater.inflate(R.layout.home_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v=view;
    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerView=(RecyclerView)v.findViewById(R.id.recycler_Event);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        FirebaseRecyclerAdapter<Event, EventViewHolder> adapter = new FirebaseRecyclerAdapter<Event,EventViewHolder>
                (Event.class, R.layout.event_item, EventViewHolder.class, event) {
            @Override
            protected void populateViewHolder(EventViewHolder viewHolder, Event model, int position) {
                viewHolder.setTitle(model.getNamaEvent());
                viewHolder.setTanggal(model.getTanggal());
                viewHolder.setFoto(getContext(), model.getGambar());
                final Event clickitem=model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //masih kosong
                        Toast.makeText(getContext(), "asfasfasfasfas", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }


    public static class EventViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        View mView;
        private ItemClickListener itemClickListener;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            itemView.setOnClickListener(this);
        }

        public void setTitle(String title){
            TextView post_title=(TextView)mView.findViewById(R.id.judulEvent);
            post_title.setText(title);
        }

        public void setTanggal(String tanggal){
            TextView post_tanggal=(TextView)mView.findViewById(R.id.tanggalEvent);
            post_tanggal.setText(tanggal);
        }

        public void setFoto(Context ctx, String image){
            ImageView post_Image=(ImageView)mView.findViewById(R.id.gambarEvent);
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
}
