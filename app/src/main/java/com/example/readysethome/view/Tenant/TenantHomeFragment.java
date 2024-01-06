package com.example.readysethome.view.Tenant;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.readysethome.R;
import com.example.readysethome.view.Tenant.TenantMain.TenantMainPresenter;
import com.example.readysethome.view.Tenant.TenantViewListing.TenantViewListingActivity;

import java.util.ArrayList;

public class TenantHomeFragment extends Fragment {

    private TenantMainPresenter presenter;
    private boolean init_recycle_view;
    private RecyclerView recyclerView;
    private ArrayList<TenantHomeListingModel> homeListingModels;
    private TenantHome_RecyclerViewAdaptor adapter;


    public TenantHomeFragment(TenantMainPresenter presenter) {
        this.presenter = presenter;
        init_recycle_view = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tenant_home, container, false);

        // get the recycler view of tenant home fragment
        recyclerView = view.findViewById(R.id.tenant_home_recyclerView);

        // prevent from adding copies of the existing rows
        if (!init_recycle_view) {
            homeListingModels = presenter.setUpHomeListingModels();
            // make the adapter for the recycler
            adapter = new TenantHome_RecyclerViewAdaptor(getContext(), homeListingModels, TenantHomeFragment.this);
            init_recycle_view = true;
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    public void onItemClick(int pos) {
        Intent intent = new Intent(getContext(), TenantViewListingActivity.class);
        // pass listing id
        intent.putExtra("LISTING_ID", homeListingModels.get(pos).getId());
        intent.putExtra("tenant_id",presenter.getAttachedTenant().getId());
        startActivity(intent);
    }
}