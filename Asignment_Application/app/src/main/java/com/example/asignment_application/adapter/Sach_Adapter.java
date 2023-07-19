package com.example.asignment_application.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asignment_application.R;
import com.example.asignment_application.apiService.Sach_ApiService;
import com.example.asignment_application.apiService.Sach_PostService;
import com.example.asignment_application.model.Sach;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Sach_Adapter extends RecyclerView.Adapter<Sach_Adapter.ViewHolder> {
    private Context context;
    private ArrayList<Sach> list;
    private SelecListener listener;

    public Sach_Adapter(Context context, ArrayList<Sach> list, SelecListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());
        String id = list.get(position).getId();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_tvTenSach);
            price = itemView.findViewById(R.id.item_tvGia);
            cardView = itemView.findViewById(R.id.crvSach);
        }
    }


}
