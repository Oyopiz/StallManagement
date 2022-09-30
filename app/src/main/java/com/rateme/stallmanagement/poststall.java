package com.rateme.stallmanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rateme.stallmanagement.Adapters.StallAdapter;
import com.rateme.stallmanagement.Classes.Stalls;

public class poststall extends AppCompatActivity {
    EditText owner, number, type, size, rent, stallnumber;
    CardView cardView;
    Button post, pick;
    ImageView stallimg;
    FirebaseAuth auth;
    Uri selectedImage;
    FirebaseStorage storage;
    ProgressBar dialog;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poststall);
        owner = findViewById(R.id.stallowner);
        mAuth = FirebaseAuth.getInstance();
        number = findViewById(R.id.landno);
        dialog = findViewById(R.id.progressmebro);
        auth = FirebaseAuth.getInstance();
        type = findViewById(R.id.stalltype);
        size = findViewById(R.id.stallsize);
        stallnumber = findViewById(R.id.stallno);
        rent = findViewById(R.id.stallrent);
        cardView = findViewById(R.id.carddimg);
        post = findViewById(R.id.stallpost);
        pick = findViewById(R.id.pickstall);
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 45);
            }
        });
        stallimg = findViewById(R.id.stallimg);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ownerme = owner.getText().toString();
                String numberme = number.getText().toString();
                String typeme = type.getText().toString();
                String sizeme = size.getText().toString();
                String rentme = rent.getText().toString();
                String stallnumb = stallnumber.getText().toString();
                if (ownerme.isEmpty()) {
                    Toast.makeText(poststall.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                } else if (ownerme.isEmpty()) {
                    Toast.makeText(poststall.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                } else if (ownerme.isEmpty()) {
                    Toast.makeText(poststall.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                } else if (ownerme.isEmpty()) {
                    Toast.makeText(poststall.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                } else if (ownerme.isEmpty()) {
                    Toast.makeText(poststall.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                } else if (stallnumb.isEmpty()) {
                    Toast.makeText(poststall.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                } else if (stallimg.getDrawable() == null) {
                    Toast.makeText(poststall.this, "Please choose image", Toast.LENGTH_SHORT).show();
                } else {
                    Upload();
                }

            }
        });

    }

    private void Upload() {
        dialog.setVisibility(View.VISIBLE);
        storage = FirebaseStorage.getInstance();
        StorageReference reference1 = storage.getReference().child("images").child(auth.getCurrentUser().getUid());
        reference1.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    reference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String ownerme = owner.getText().toString();
                            String uid = mAuth.getCurrentUser().getUid();
                            String numberme = number.getText().toString();
                            String typeme = type.getText().toString();
                            String sizeme = size.getText().toString();
                            String rentme = rent.getText().toString();
                            String stallnumb = stallnumber.getText().toString();
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Stalls");
                            Stalls stalls = new Stalls(numberme, ownerme, typeme, sizeme, rentme, uri.toString(), uid, stallnumb,mAuth.getCurrentUser().getEmail());
                            reference.push().setValue(stalls);
                            dialog.setVisibility(View.GONE);
                            finish();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (data.getData() != null) {
                stallimg.setImageURI(data.getData());
                cardView.setVisibility(View.VISIBLE);
                selectedImage = data.getData();
            }
        }
    }
}