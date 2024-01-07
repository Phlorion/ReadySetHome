package com.example.readysethome.view.Owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readysethome.R;
import com.example.readysethome.model.BookingRequest;
import com.example.readysethome.view.Owner.OwnerMain.OwnerMainPresenter;

import java.util.ArrayList;

public class OwnerPending_RecyclerViewAdaptor extends RecyclerView.Adapter<OwnerPending_RecyclerViewAdaptor.MyViewHolder> {

    Context context;
    OwnerMainPresenter presenter;
    ArrayList<OwnerPendingModel> pendingModels;
    public OwnerPending_RecyclerViewAdaptor(Context context, ArrayList<OwnerPendingModel> pendingModels, OwnerMainPresenter presenter) {
        this.context = context;
        this.pendingModels = pendingModels;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public OwnerPending_RecyclerViewAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout, give the look to our rows
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.owner_pending_recycler_view_row, parent, false);
        return new OwnerPending_RecyclerViewAdaptor.MyViewHolder(view, presenter, pendingModels);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnerPending_RecyclerViewAdaptor.MyViewHolder holder, int position) {
        // assigning values to the views we created in the recycler_view_owner_home layout file
        // based on the position of the recycler view
        holder.listingsTitle.setText(pendingModels.get(position).getListingsTitle());
        holder.tenantsName.setText(pendingModels.get(position).getTenants_Name());
        holder.imageView.setImageResource(pendingModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        // the recycler view just wants to know the number of items you want displayed
        return pendingModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // grabbing the views from our owner_pending_recycler_view_row layout file
        // Kinda like in the onCreate method

        ImageView imageView;
        TextView tenantsName, listingsTitle;
        public MyViewHolder(@NonNull View itemView, OwnerMainPresenter presenter, ArrayList<OwnerPendingModel> pendingModels) {
            super(itemView);
            imageView = itemView.findViewById(R.id.owner_pending_recycler_view_row_image);
            tenantsName = itemView.findViewById(R.id.owner_pending_recycler_view_tenant_name);
            listingsTitle = itemView.findViewById(R.id.owner_pending_recycler_view_listing_title);
            Button buttonConfirm = itemView.findViewById(R.id.owner_pending_recycler_view_confirm);
            Button buttonDecline = itemView.findViewById(R.id.owner_pending_recycler_view_decline);

            buttonConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    presenter.pendingConfirm(pos);
                }
            });

            buttonDecline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    presenter.pendingDecline(pos);
                }
            });
        }
    }
}
