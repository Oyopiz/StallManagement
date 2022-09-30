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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rateme.stallmanagement.Classes.Requests;
import com.rateme.stallmanagement.Classes.Stalls;
import com.rateme.stallmanagement.R;
import com.squareup.picasso.Picasso;

public class RequestsAdapter extends FirebaseRecyclerAdapter<Requests, RequestsAdapter.requestsViewholder> {
    public RequestsAdapter(@NonNull FirebaseRecyclerOptions<Requests> options) {
        super(options);
    }

    FirebaseAuth mAuth;

    @Override
    protected void onBindViewHolder(@NonNull requestsViewholder holder, int position, @NonNull Requests model) {
        Picasso.get().load(model.getImageurl()).into(holder.img);
        DatabaseReference referenceme=FirebaseDatabase.getInstance().getReference("Stalls").child("Interests");
        referenceme.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    holder.requests.setVisibility(View.VISIBLE);
                }
                else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mAuth = FirebaseAuth.getInstance();
        holder.owner.setText(model.getRent());
        holder.landno.setText(model.getRent());
        String keybro = getRef(position).getKey();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Stalls").child(keybro).child("Tenants");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String me = String.valueOf(snapshot.getChildrenCount());
                    holder.requests.setText(me);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @NonNull
    @Override
    public requestsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.requests, parent, false);
        return new RequestsAdapter.requestsViewholder(view);
    }

    public class requestsViewholder extends RecyclerView.ViewHolder {
        TextView landno, owner, requests;
        ImageView img;

        public requestsViewholder(@NonNull View itemView) {
            super(itemView);
            landno = itemView.findViewById(R.id.numberstall);
            owner = itemView.findViewById(R.id.ownerstall);
            img = itemView.findViewById(R.id.imgstall);
            requests = itemView.findViewById(R.id.pending);
        }
    }
}