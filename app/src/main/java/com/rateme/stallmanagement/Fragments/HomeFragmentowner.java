package com.rateme.stallmanagement.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rateme.stallmanagement.Adapters.StallAdapter;
import com.rateme.stallmanagement.Classes.Stalls;
import com.rateme.stallmanagement.R;
import com.rateme.stallmanagement.poststall;

public class HomeFragmentowner extends Fragment {

    FirebaseDatabase rootNode;
    FirebaseAuth mAuth;
    DatabaseReference mbase, tenrec;
    RadioButton ten, twenty, thirty, fourty, all;
    RadioGroup radioGroup;
    StallAdapter adapter;
    FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stalls, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.stalls);
        floatingActionButton = view.findViewById(R.id.stalladd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), poststall.class));
            }
        });
        rootNode = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mbase = FirebaseDatabase.getInstance().getReference("Stalls");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerOptions<Stalls> options = new FirebaseRecyclerOptions.Builder<Stalls>().setQuery(mbase, Stalls.class).build();
        adapter = new StallAdapter(options);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}
