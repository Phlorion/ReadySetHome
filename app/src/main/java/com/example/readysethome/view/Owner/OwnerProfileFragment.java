package com.example.readysethome.view.Owner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readysethome.R;
import com.example.readysethome.view.Owner.OwnerMain.OwnerMainPresenter;

public class OwnerProfileFragment extends Fragment {

    private OwnerMainPresenter presenter;

    public OwnerProfileFragment(OwnerMainPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_owner_profile, container, false);

        ((TextView) view.findViewById(R.id.owner_profile_nameView)).setText(presenter.getAttachedOwner().getFirstName());
        ((TextView) view.findViewById(R.id.owner_profile_surnameView)).setText(presenter.getAttachedOwner().getLastName());
        ((TextView) view.findViewById(R.id.owner_profile_emailView)).setText(presenter.getAttachedOwner().getEmail().getAddress());
        ((ImageView)view.findViewById(R.id.owner_profile_profilePicture)).setImageResource(R.drawable.child_po);

        return view;
    }
}