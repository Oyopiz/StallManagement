package com.rateme.stallmanagement.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rateme.stallmanagement.Adapters.RequestsAdapter;
import com.rateme.stallmanagement.Adapters.StallAdapter;
import com.rateme.stallmanagement.Classes.Requests;
import com.rateme.stallmanagement.Classes.Stalls;
import com.rateme.stallmanagement.R;

public class RequestsFragment extends Fragment {
    FirebaseDatabase rootNode;
    FirebaseAuth mAuth;
    DatabaseReference mbase, tenrec;
    RadioButton ten, twenty, thirty, fourty, all;
    RadioGroup radioGroup;
    RequestsAdapter adapter;
    FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requests, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.home);

        rootNode = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mbase = FirebaseDatabase.getInstance().getReference("Stalls");
        Query query = mbase.orderByChild("uid").equalTo(mAuth.getCurrentUser().getUid());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerOptions<Requests> options = new FirebaseRecyclerOptions.Builder<Requests>().setQuery(query, Requests.class).build();
        adapter = new RequestsAdapter(options);
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
