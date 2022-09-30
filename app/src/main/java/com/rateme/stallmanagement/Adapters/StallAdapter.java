package com.rateme.stallmanagement.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.rateme.stallmanagement.Classes.Stalls;
import com.rateme.stallmanagement.R;
import com.squareup.picasso.Picasso;

public class StallAdapter extends FirebaseRecyclerAdapter<Stalls, StallAdapter.stallsViewholder> {
    public StallAdapter(@NonNull FirebaseRecyclerOptions<Stalls> options) {
        super(options);
    }

    FirebaseAuth mAuth;

    @Override
    protected void onBindViewHolder(@NonNull StallAdapter.stallsViewholder holder, int position, @NonNull Stalls model) {
        Picasso.get().load(model.getImageurl()).into(holder.img);
        holder.rent.setText("Ksh"+" "+model.getRent()+" "+"p.m");
        holder.owner.setText(model.getOwner());
        holder.stallno.setText(model.getStallnumber());
        mAuth = FirebaseAuth.getInstance();
        holder.landno.setText(model.getLandno());
        holder.type.setText(model.getType());
        holder.size.setText(model.getSize());

    }

    @NonNull
    @Override
    public StallAdapter.stallsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stalls, parent, false);
        return new StallAdapter.stallsViewholder(view);
    }

    public class stallsViewholder extends RecyclerView.ViewHolder {
        TextView landno, owner, type, size, rent, stallno;
        ImageView img;

        public stallsViewholder(@NonNull View itemView) {
            super(itemView);
            landno = itemView.findViewById(R.id.numberstall);
            owner = itemView.findViewById(R.id.ownerstall);
            type = itemView.findViewById(R.id.typestall);
            stallno = itemView.findViewById(R.id.stallnumber);
            size = itemView.findViewById(R.id.sizestall);
            rent = itemView.findViewById(R.id.rentstall);
            img = itemView.findViewById(R.id.imgstall);
        }
    }
}