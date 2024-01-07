package com.example.readysethome.view.Tenant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readysethome.R;
import com.example.readysethome.view.Tenant.TenantMain.TenantMainPresenter;

public class TenantProfileFragment extends Fragment {

    private TenantMainPresenter presenter;
    public TenantProfileFragment(TenantMainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tenant_profile, container, false);

        ((TextView) view.findViewById(R.id.tenant_profile_nameView)).setText(presenter.getAttachedTenant().getFirstName());
        ((TextView) view.findViewById(R.id.tenant_profile_surnameView)).setText(presenter.getAttachedTenant().getLastName());
        ((TextView) view.findViewById(R.id.tenant_profile_emailView)).setText(presenter.getAttachedTenant().getEmail().getAddress());
        ((ImageView)view.findViewById(R.id.tenant_profile_profilePicture)).setImageResource(R.drawable.child_po);

        return view;
    }
}