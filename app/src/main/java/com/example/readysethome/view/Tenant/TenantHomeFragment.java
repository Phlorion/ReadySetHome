package com.example.readysethome.view.Tenant;

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
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.model.BookingRequest;
import com.example.readysethome.view.Owner.OwnerHomeListingModel;
import com.example.readysethome.view.Tenant.TenantFilterListings.FilterHolder;
import com.example.readysethome.view.Tenant.TenantFilterListings.TenantFilterListingsActivity;
import com.example.readysethome.view.Tenant.TenantMain.TenantMainActivity;
import com.example.readysethome.view.Tenant.TenantMain.TenantMainPresenter;
import com.example.readysethome.view.Tenant.TenantMain.TenantMainView;
import com.example.readysethome.view.Tenant.TenantViewListing.TenantViewListingActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TenantHomeFragment extends Fragment {

    private TenantMainPresenter presenter;
    private boolean init_recycle_view;
    private RecyclerView recyclerView;
    private ArrayList<TenantHomeListingModel> defaultHomeListingModels;
    private ArrayList<TenantHomeListingModel> homeListingModels;
    private TenantHome_RecyclerViewAdaptor adapter;
    private FilterHolder applied_filters;
    private SearchView searchView;

    public TenantHomeFragment(TenantMainPresenter presenter) {
        this.presenter = presenter;
        init_recycle_view = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tenant_home, container, false);

        /**
         * Παίρνουμε το αποτέλεσμα ενός activity
         */
        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if (o != null && o.getResultCode() == 10) {
                    if (o.getData() != null) {
                        applied_filters = (FilterHolder) o.getData().getSerializableExtra("FILTERS");
                        filterByAdditional(applied_filters);
                    }
                }
            }
        });

        // Filter Button
        Button filterBtn = view.findViewById(R.id.tenant_home_filterBtn);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filter_intent = new Intent(getContext(), TenantFilterListingsActivity.class);
                if (applied_filters != null) {
                    filter_intent.putExtra("APPLIED_FILTERS", applied_filters);
                }
                startForResult.launch(filter_intent);
            }
        });

        // Clear Filters Button
        Button clearFilterBtn = view.findViewById(R.id.tenant_home_filterClearBtn);
        clearFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applied_filters = null;
                homeListingModels = defaultHomeListingModels;
                adapter.setHomeListingModels(homeListingModels);
            }
        });

        // get the search view and add event listener
        searchView = view.findViewById(R.id.tenant_home_searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterHomeListingModels(newText);
                return true;
            }
        });

        // get the recycler view of tenant home fragment
        recyclerView = view.findViewById(R.id.tenant_home_recyclerView);

        // prevent from adding copies of the existing rows
        if (!init_recycle_view) {
            homeListingModels = presenter.setUpHomeListingModels(); // set all the listings of the listingDAO
            defaultHomeListingModels = homeListingModels;

            // make the adapter for the recycler
            adapter = new TenantHome_RecyclerViewAdaptor(getContext(), homeListingModels, TenantHomeFragment.this);
            init_recycle_view = true;
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    /**
     * Helper method for filterHomeListingModels. Check if the listingModel agrees with the
     * given filters of price and checkIn-checkOut
     * @param listingModel The listingModel
     * @return If it agrees or not
     */
    private boolean priceAndCheckInCheckOut(TenantHomeListingModel listingModel, FilterHolder additional) {
        boolean accountForCheckInCheckOut;
        accountForCheckInCheckOut = !additional.getCheck_in().equals("") || !additional.getCheck_out().equals("");

        // no check_in, check_out filters and price agrees with filter
        if (!accountForCheckInCheckOut && Double.parseDouble(listingModel.getPrice().substring(0, listingModel.getPrice().length() - 1)) <= Double.parseDouble(additional.getPrice())) return true;
        else if (!accountForCheckInCheckOut) return false; // else if price does not agree

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date checkIn;
        Date checkOut;
        try {
            checkIn = format.parse(additional.getCheck_in());
            checkOut = format.parse(additional.getCheck_out());
        } catch (ParseException e) { // can't parse date
            return false;
        }
        // yes check_in, check_out filters and price agrees with filters
        return (Double.parseDouble(listingModel.getPrice().substring(0, listingModel.getPrice().length() - 1)) <= Double.parseDouble(additional.getPrice())) && listingModel.getListing().getCalendar().isAvailable(checkIn, checkOut);
    }

    /**
     * Filter all listings
     * @param text Search view text
     */
    private void filterHomeListingModels(String text) {
        Set<TenantHomeListingModel> filtered = new HashSet<>();

        if (text.equals("")) { // no filters at all
            applied_filters = null;
            homeListingModels = defaultHomeListingModels;
            adapter.setHomeListingModels(homeListingModels);
        }

        for (TenantHomeListingModel listingModel : homeListingModels) {
            // search bar data
            if (listingModel.getTitle().toLowerCase().contains(text.toLowerCase()) || listingModel.getAp_address().toLowerCase().contains(text.toLowerCase())
            || listingModel.getDesc().toLowerCase().contains(text.toLowerCase())) {
                filtered.add(listingModel);
            }
        }

        homeListingModels = new ArrayList<>();
        homeListingModels.addAll(filtered);

        adapter.setHomeListingModels(homeListingModels);
    }

    public void filterByAdditional(FilterHolder additional) {
        Set<TenantHomeListingModel> filtered = new HashSet<>();
        homeListingModels = defaultHomeListingModels;

        // applied_filters
        for (TenantHomeListingModel listingModel : homeListingModels) {
            if (priceAndCheckInCheckOut(listingModel, additional)) {
                if (!additional.isAth() && !additional.isThes() && !additional.isPatra() && !additional.isHrakleio() &&
                        !additional.isIwan() && !additional.isVolos() && !additional.isSyros() && !additional.isNafplion()) {
                    filtered.add(listingModel);
                }
                else if (additional.isAth() && listingModel.getAp_address().toLowerCase().contains("athens")) filtered.add(listingModel);
                else if (additional.isPatra() && listingModel.getAp_address().toLowerCase().contains("patra")) filtered.add(listingModel);
                else if (additional.isHrakleio() && listingModel.getAp_address().toLowerCase().contains("hrakleio")) filtered.add(listingModel);
                else if (additional.isIwan() && listingModel.getAp_address().toLowerCase().contains("iwannina")) filtered.add(listingModel);
                else if (additional.isVolos() && listingModel.getAp_address().toLowerCase().contains("volos")) filtered.add(listingModel);
                else if (additional.isSyros() && listingModel.getAp_address().toLowerCase().contains("syros")) filtered.add(listingModel);
                else if (additional.isNafplion() && listingModel.getAp_address().toLowerCase().contains("nafplion")) filtered.add(listingModel);
            }
        }

        homeListingModels = new ArrayList<>();
        homeListingModels.addAll(filtered);

        adapter.setHomeListingModels(homeListingModels);
    }

    public void onItemClick(int pos) {
        Intent intent = new Intent(getContext(), TenantViewListingActivity.class);
        // pass listing id
        intent.putExtra("LISTING_ID", homeListingModels.get(pos).getId());
        intent.putExtra("tenant_id",presenter.getAttachedTenant().getId());
        startActivity(intent);
    }
}