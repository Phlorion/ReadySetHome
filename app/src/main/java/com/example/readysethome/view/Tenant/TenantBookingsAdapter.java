package com.example.readysethome.view.Tenant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readysethome.R;

import java.util.ArrayList;

public class TenantBookingsAdapter extends RecyclerView.Adapter<TenantBookingsAdapter.MyViewHolder>{
    TenantBookingsFragment fragment;
    private Context context;
    private ArrayList<TenantBookingModel> bookingModels;

    public TenantBookingsAdapter(Context context, ArrayList<TenantBookingModel> bookingModels, TenantBookingsFragment fragment) {
        this.context = context;
        this.bookingModels = bookingModels;
        this.fragment = fragment;
    }

    public void setBookingModels(ArrayList<TenantBookingModel> bookingModels) {
        this.bookingModels = bookingModels;
        notifyDataSetChanged();
    }



    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_tenant_booking, parent, false);
        return new MyViewHolder(view,fragment);
    }

    @Override

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TenantBookingModel bookingModel = bookingModels.get(position);
        holder.tvTitle.setText(bookingModel.getTitle());
        holder.tvDate.setText(bookingModel.getDate());
        holder.tvStatus.setText(bookingModel.getStatus());
        holder.tvImage.setImageResource(bookingModels.get(position).getImage());

    }


    @Override
    public int getItemCount() {
        return bookingModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView tvImage;

        TextView tvTitle, tvDate, tvStatus;

        public MyViewHolder(@NonNull View itemView,TenantBookingsFragment fragment) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.recycler_view_tenant_booking_title);
            tvDate = itemView.findViewById(R.id.recycler_view_tenant_booking_date);
            tvStatus = itemView.findViewById(R.id.recycler_view_tenant_booking_status);
            tvImage=itemView.findViewById(R.id.recylcler_view_tenant_booking_image);

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
