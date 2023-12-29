package com.example.readysethome.view.Owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readysethome.R;

import java.util.ArrayList;

public class OwnerHome_RecyclerViewAdaptor extends RecyclerView.Adapter<OwnerHome_RecyclerViewAdaptor.MyViewHolder> {
    Context context;
    ArrayList<OwnerHomeListingModel> listingModels;
    public OwnerHome_RecyclerViewAdaptor(Context context, ArrayList<OwnerHomeListingModel> listingModels) {
        this.context = context;
        this.listingModels = listingModels;
    }

    @NonNull
    @Override
    public OwnerHome_RecyclerViewAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout, give the look to our rows
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_owner_home, parent, false);

        return new OwnerHome_RecyclerViewAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnerHome_RecyclerViewAdaptor.MyViewHolder holder, int position) {
        // assigning values to the views we created in the recycler_view_owner_home layout file
        // based on the position of the recycler view
        holder.tvTitle.setText(listingModels.get(position).getTitle());
        holder.tvDesc.setText(listingModels.get(position).getDesc());
        holder.tvPrice.setText(listingModels.get(position).getPrice());
        holder.imageView.setImageResource(listingModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        // the recycler view wants to know how many items we want to be displayed
        return listingModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // grabbing the views from our recycler_view_owner_home layout file

        ImageView imageView;
        TextView tvTitle, tvDesc, tvPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.recycler_view_owner_home_image);
            tvTitle = itemView.findViewById(R.id.recycler_view_owner_home_title);
            tvDesc = itemView.findViewById(R.id.recycler_view_owner_home_desc);
            tvPrice = itemView.findViewById(R.id.recycler_view_owner_home_price);
        }
    }

}
