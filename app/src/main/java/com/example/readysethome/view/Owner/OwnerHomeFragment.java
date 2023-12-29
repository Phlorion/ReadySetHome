package com.example.readysethome.view.Owner;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.readysethome.R;
import com.example.readysethome.view.Owner.OwnerMain.OwnerMainActivity;
import com.example.readysethome.view.Owner.OwnerMain.OwnerMainPresenter;

import java.util.ArrayList;

public class OwnerHomeFragment extends Fragment {
    private OwnerMainPresenter presenter;
    private boolean init_recycle_view;
    private RecyclerView recyclerView;
    private ArrayList<OwnerHomeListingModel> listingModels;
    private OwnerHome_RecyclerViewAdaptor adapter;

    public OwnerHomeFragment(OwnerMainPresenter presenter) {
        this.presenter = presenter;
        init_recycle_view = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_owner_home, container, false);

        // get the recycler view of owner home fragment
        recyclerView = view.findViewById(R.id.owner_home_recycler);

        // prevent from adding copies of the existing rows
        if (!init_recycle_view) {
            listingModels = presenter.setUpListingModels();
            // make the adapter for the recycler
            adapter = new OwnerHome_RecyclerViewAdaptor(getContext(), listingModels);
            init_recycle_view = true;
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}