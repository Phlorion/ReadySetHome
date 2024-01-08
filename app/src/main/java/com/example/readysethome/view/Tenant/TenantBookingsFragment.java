package com.example.readysethome.view.Tenant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readysethome.R;
import com.example.readysethome.model.Booking;
import com.example.readysethome.model.BookingRequest;
import com.example.readysethome.model.Tenant;
import com.example.readysethome.view.Tenant.TenantMain.TenantMainPresenter;

import java.util.ArrayList;

public class TenantBookingsFragment extends Fragment {

    private RecyclerView recyclerView;
    private TenantBookingsAdapter adapter;
    private Tenant tenant;
    ArrayList<TenantBookingModel> bookingsAndRequests;
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
            bookingsAndRequests = presenter.setUpBookingModels();
            adapter = new TenantBookingsAdapter(getContext(), bookingsAndRequests,this);
            init_recycle_view=true;
            adapter.setBookingModels(bookingsAndRequests);
        } else {
            // set up each time the booking request and bookings models (in case there is a new one)
            adapter.setBookingModels(presenter.setUpBookingModels());
            bookingsAndRequests = presenter.setUpBookingModels();
        }

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }


    public void onItemClick(int position) {
        // Handle item click here
        TenantBookingModel clickedBooking = bookingsAndRequests.get(position);
        System.out.println(position);
        System.out.println(bookingsAndRequests.get(position)==null);

        // Show a confirmation dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Cancel Booking");
        builder.setMessage("Are you sure you want to cancel the booking?");

        // Add the buttons
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked Yes
                // if it is still a request, then cancel booking request
                for (BookingRequest b_r : presenter.getAttachedTenant().getBookingRequests()) {
                    if (b_r.getBooking_id() == clickedBooking.getId()) {
                        cancelBookingRequest(b_r);
                        break;
                    }
                }
                // if it is a registered booking, then cancel booking
                for (Booking b : presenter.getAttachedTenant().getBookings()) {
                    if (b.getId() == clickedBooking.getId()) {
                        cancelBooking(b);
                        break;
                    }
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked No
                dialog.dismiss();  // Dismiss the dialog
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
        adapter.setBookingModels(bookingsAndRequests);
    }
    protected void cancelBooking(Booking booking){
        presenter.cancelBooking(booking);
        cancelProcedure();

    }
    protected void cancelBookingRequest(BookingRequest bookingRequest) {
        presenter.cancelBookingRequest(bookingRequest);
        cancelProcedure();
    }

    private void cancelProcedure() {

        // Update the bookingsAndRequests list with the latest data
        bookingsAndRequests = presenter.setUpBookingModels();
        // Show a confirmation dialog for cancellation completion
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Cancellation Complete");
        builder.setMessage("The booking has been canceled successfully.");

        // Add an OK button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();  // Dismiss the dialog
            }
        });


        AlertDialog completionDialog = builder.create();
        completionDialog.show();
    }



}
