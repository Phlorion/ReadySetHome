package com.example.readysethome.view.Tenant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readysethome.R;
import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.model.Tenant;
import com.example.readysethome.view.Tenant.TenantMain.TenantMainPresenter;

import java.util.ArrayList;

public class TenantBookingsFragment extends Fragment {

    private RecyclerView recyclerView;
    private TenantBookingsAdapter adapter;
    private TenantDAO tenantDAO;
    private Tenant tenant;

    TenantMainPresenter presenter;


    private boolean init_recycle_view;

    public TenantBookingsFragment(TenantMainPresenter presenter) {
        this.presenter = presenter;
        tenant = presenter.getAttachedTenant();
        init_recycle_view=false;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tenant_bookings, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);


        if(!init_recycle_view){
            // Get all bookings and requests for the tenant using the presenter method
            ArrayList<TenantBookingModel> bookingsAndRequests = presenter.setBookingModels();
            adapter = new TenantBookingsAdapter(getContext(), bookingsAndRequests,this);
            init_recycle_view=true;
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
