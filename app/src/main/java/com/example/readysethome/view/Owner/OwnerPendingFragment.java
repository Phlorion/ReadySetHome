package com.example.readysethome.view.Owner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.readysethome.R;
import com.example.readysethome.model.BookingRequest;
import com.example.readysethome.model.Owner;
import com.example.readysethome.view.Owner.OwnerMain.OwnerMainPresenter;

import java.util.ArrayList;


public class OwnerPendingFragment extends Fragment {
    ArrayList<BookingRequest> bookingRequests = new ArrayList<>();
    private boolean init_recycle_view;
    private OwnerMainPresenter presenter;
    private RecyclerView recyclerView;
    private ArrayList<OwnerPendingModel> pendingModels;
    private ArrayList<BookingRequest> allBookingRequesets;
    private Owner owner;
    private OwnerPending_RecyclerViewAdaptor adapter;


    public OwnerPendingFragment(OwnerMainPresenter presenter) {
        this.presenter = presenter;
        init_recycle_view = false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_owner_pending, container, false);

        // get the recycler view of owner pending fragment
        recyclerView = view.findViewById(R.id.owner_pending_recycler);
        pendingModels = presenter.setUpPendingModels();
        OwnerPending_RecyclerViewAdaptor adapter = new OwnerPending_RecyclerViewAdaptor(getContext(), pendingModels, presenter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    public void onItemClick(int pos) {

    }
}