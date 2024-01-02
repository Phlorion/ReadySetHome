package com.example.readysethome.view.Tenant;

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

public class TenantHome_RecyclerViewAdaptor extends RecyclerView.Adapter<TenantHome_RecyclerViewAdaptor.MyViewHolder>{
    TenantHomeFragment fragment;
    Context context;
    ArrayList<TenantHomeListingModel> homeListingModels;
    public TenantHome_RecyclerViewAdaptor(Context context, ArrayList<TenantHomeListingModel> homeListingModels, TenantHomeFragment fragment) {
        this.context = context;
        this.homeListingModels = homeListingModels;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public TenantHome_RecyclerViewAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout, give the look to our rows
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_tenant_home, parent, false);

        return new TenantHome_RecyclerViewAdaptor.MyViewHolder(view, fragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TenantHome_RecyclerViewAdaptor.MyViewHolder holder, int position) {
        // assigning values to the views we created in the recycler_view_owner_home layout file
        // based on the position of the recycler view
        holder.tvTitle.setText(homeListingModels.get(position).getTitle());
        holder.tvDesc.setText(homeListingModels.get(position).getDesc());
        holder.tvPrice.setText(homeListingModels.get(position).getPrice());
        holder.imageView.setImageResource(homeListingModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        // the recycler view wants to know how many items we want to be displayed
        return homeListingModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // grabbing the views from our recycler_view_tenant_home layout file
        ImageView imageView;
        TextView tvTitle, tvDesc, tvPrice;
        public MyViewHolder(@NonNull View itemView, TenantHomeFragment fragment) {
            super(itemView);

            imageView = itemView.findViewById(R.id.recycler_view_tenant_home_image);
            tvTitle = itemView.findViewById(R.id.recycler_view_tenant_home_title);
            tvDesc = itemView.findViewById(R.id.recycler_view_tenant_home_desc);
            tvPrice = itemView.findViewById(R.id.recycler_view_tenant_home_price);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fragment != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            fragment.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

}
