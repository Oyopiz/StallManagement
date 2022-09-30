package com.rateme.stallmanagement.Adapters;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rateme.stallmanagement.Classes.Interests;
import com.rateme.stallmanagement.Classes.Stalls;
import com.rateme.stallmanagement.R;
import com.squareup.picasso.Picasso;

public class StalltenantAdapter extends FirebaseRecyclerAdapter<Stalls, StalltenantAdapter.stallsViewholder> {
    public StalltenantAdapter(@NonNull FirebaseRecyclerOptions<Stalls> options) {
        super(options);
    }

    FirebaseAuth mAuth;

    @Override
    protected void onBindViewHolder(@NonNull StalltenantAdapter.stallsViewholder holder, int position, @NonNull Stalls model) {
        Picasso.get().load(model.getImageurl()).into(holder.img);
        holder.rent.setText(model.getRent());
        mAuth = FirebaseAuth.getInstance();
        String keybro = getRef(position).getKey();
        holder.owner.setText(model.getOwner());
        holder.stallbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(holder.stallbook.getContext(), R.style.BottomSheetDialog);
                // bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
                LinearLayout interest = bottomSheetDialog.findViewById(R.id.contactLinearLayout);
                LinearLayout contact = bottomSheetDialog.findViewById(R.id.InterestLayout);
                interest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                        builder.setMessage("This will notify"+" "+ model.getOwner());
                        builder.setTitle("CONTACT");
                        builder.setCancelable(false);
                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Stalls").child(keybro).child("Interests");
                                Interests interests=new Interests(mAuth.getCurrentUser().getUid(),mAuth.getCurrentUser().getEmail());
                                reference.setValue(interests);
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                });
                contact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{model.getEmail()});
                        i.putExtra(Intent.EXTRA_SUBJECT, "Stall number"+" "+ model.getStallnumber());
                        i.putExtra(Intent.EXTRA_TEXT   , "Hi, I am interested in this stall");
                        try {
                            holder.itemView.getContext().startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(holder.itemView.getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                bottomSheetDialog.show();
            }
        });
        holder.landno.setText(model.getLandno());
        holder.type.setText(model.getType());
        holder.stallno.setText(model.getStallnumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.size.setText(model.getSize());
    }

    @NonNull
    @Override
    public StalltenantAdapter.stallsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stallstenant, parent, false);
        return new StalltenantAdapter.stallsViewholder(view);
    }

    public class stallsViewholder extends RecyclerView.ViewHolder {
        TextView landno, owner, type, size, rent, stallno;
        ImageView img;
        FloatingActionButton stallbook;

        public stallsViewholder(@NonNull View itemView) {
            super(itemView);
            landno = itemView.findViewById(R.id.numberstall);
            owner = itemView.findViewById(R.id.ownerstall);
            type = itemView.findViewById(R.id.typestall);
            size = itemView.findViewById(R.id.sizestall);
            stallno = itemView.findViewById(R.id.stallnumbertnt);
            rent = itemView.findViewById(R.id.rentstall);
            img = itemView.findViewById(R.id.imgstall);
            stallbook = itemView.findViewById(R.id.stallbook);
        }
    }
}