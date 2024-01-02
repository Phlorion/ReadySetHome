package com.example.readysethome.view.Owner;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.example.readysethome.R;
import com.example.readysethome.model.Listing;
import com.example.readysethome.view.Owner.OwnerAddListing.OwnerAddListingActivity;
import com.example.readysethome.view.Owner.OwnerMain.OwnerMainPresenter;
import com.example.readysethome.view.Owner.OwnerViewListing.OwnerViewListingActivity;

import java.util.ArrayList;

public class OwnerHomeFragment extends Fragment {
    private OwnerMainPresenter presenter;
    private boolean init_recycle_view;
    private SearchView searchView;
    private Button addButton;
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

        /**
         * Παίρνουμε το αποτέλεσμα ενός activity
         */
        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if (o != null && o.getResultCode() == 0) {
                    if (o.getData() != null && o.getData().getSerializableExtra("NEW_LISTING") != null) {
                        listingModels = presenter.addListingModel((Listing) o.getData().getSerializableExtra("NEW_LISTING"));
                        adapter.setListingModels(listingModels);
                    }
                }
            }
        });

        // get the search view and add event listener
        searchView = view.findViewById(R.id.owner_home_searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterListingModels(newText);
                return true;
            }
        });

        // get the add-button and add event listener
        addButton = view.findViewById(R.id.owner_home_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OwnerAddListingActivity.class);
                intent.putExtra("OWNER", presenter.getAttachedOwner());
                // θέλουμε να μας επιστραφεί αποτέλεσμα από το νέο activity
                startForResult.launch(intent);
            }
        });

        // get the recycler view of owner home fragment
        recyclerView = view.findViewById(R.id.owner_home_recycler);

        // prevent from adding copies of the existing rows
        if (!init_recycle_view) {
            listingModels = presenter.setUpListingModels();
            // make the adapter for the recycler
            adapter = new OwnerHome_RecyclerViewAdaptor(getContext(), listingModels, OwnerHomeFragment.this);
            init_recycle_view = true;
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private void filterListingModels(String text) {
        ArrayList<OwnerHomeListingModel> filtered = new ArrayList<>();
        for (OwnerHomeListingModel listingModel : listingModels) {
            if (listingModel.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filtered.add(listingModel);
            }
        }
        adapter.setListingModels(filtered);
    }

    protected void onItemClick(int pos) {
        Intent intent = new Intent(getContext(), OwnerViewListingActivity.class);
        intent.putExtra("TITLE", listingModels.get(pos).getTitle());
        intent.putExtra("DESCRIPTION", listingModels.get(pos).getDesc());
        intent.putExtra("PRICE", listingModels.get(pos).getPrice());
        intent.putExtra("IMAGE", listingModels.get(pos).getImage());

        startActivity(intent);
    }
}