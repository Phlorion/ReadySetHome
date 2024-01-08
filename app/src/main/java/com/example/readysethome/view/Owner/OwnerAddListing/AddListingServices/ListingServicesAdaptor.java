package com.example.readysethome.view.Owner.OwnerAddListing.AddListingServices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readysethome.R;
import com.example.readysethome.view.Owner.OwnerAddListing.AddChargingPolicies.ChargingPolicyAdaptor;

import java.util.ArrayList;

public class ListingServicesAdaptor extends RecyclerView.Adapter<ListingServicesAdaptor.MyViewHolder>{
    Context context;
    ArrayList<ListingServicesModel> listingServicesModels;

    public ListingServicesAdaptor(Context context, ArrayList<ListingServicesModel> listingServicesModels) {
        this.context = context;
        this.listingServicesModels = listingServicesModels;
    }

    public void setListingServicesModels(ArrayList<ListingServicesModel> listingServicesModels) {
        this.listingServicesModels = listingServicesModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListingServicesAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout, give the look to our rows
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_listing_service, parent, false);

        return new ListingServicesAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListingServicesAdaptor.MyViewHolder holder, int position) {
        // assigning values to the views we created in the recycler_view_owner_home layout file
        // based on the position of the recycler view
        holder.tvType.setText(listingServicesModels.get(position).getType());
        holder.tvPrice.setText(listingServicesModels.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return listingServicesModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvType, tvPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvType = itemView.findViewById(R.id.textView23);
            tvPrice = itemView.findViewById(R.id.textView24);
        }
    }

}
