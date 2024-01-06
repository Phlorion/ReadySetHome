package com.example.readysethome.view.Owner.OwnerAddListing.AddChargingPolicies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readysethome.R;

import java.util.ArrayList;

public class ChargingPolicyAdaptor extends RecyclerView.Adapter<ChargingPolicyAdaptor.MyViewHolder> {
    Context context;
    ArrayList<ChargingPolicyModel> chargingModels;

    public ChargingPolicyAdaptor(Context context, ArrayList<ChargingPolicyModel> chargingModels) {
        this.context = context;
        this.chargingModels = chargingModels;
    }

    public void setChargingModels(ArrayList<ChargingPolicyModel> chargingModels) {
        this.chargingModels = chargingModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChargingPolicyAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout, give the look to our rows
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_charging_policy, parent, false);

        return new ChargingPolicyAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChargingPolicyAdaptor.MyViewHolder holder, int position) {
        // assigning values to the views we created in the recycler_view_owner_home layout file
        // based on the position of the recycler view
        holder.tvStartInd.setText(chargingModels.get(position).getStart_index());
        holder.tvEndInd.setText(chargingModels.get(position).getEnd_index());
        holder.tvDesc.setText(chargingModels.get(position).getDesc());
        holder.tvPriceDif.setText(chargingModels.get(position).getPrice_diff());
    }

    @Override
    public int getItemCount() {
        return chargingModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvStartInd, tvEndInd, tvDesc, tvPriceDif;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStartInd = itemView.findViewById(R.id.start_index);
            tvEndInd = itemView.findViewById(R.id.end_index);
            tvDesc = itemView.findViewById(R.id.desc);
            tvPriceDif = itemView.findViewById(R.id.price_diff);
        }
    }
}
