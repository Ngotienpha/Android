package com.example.mhike;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<String> name_id;
    private List<String> location_id;
    private List<String> date_id;
    private List<String> length_id;
    private List<String> level_id;



    public MyAdapter(Context context, ArrayList name_id, ArrayList location_id, ArrayList date_id, ArrayList length_id, ArrayList level_id) {
        this.context = context;
        this.name_id = name_id;
        this.location_id = location_id;
        this.date_id = date_id;
        this.length_id = length_id;
        this.level_id = level_id;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.location_id.setText(String.valueOf(location_id.get(position)));
        holder.date_id.setText(String.valueOf(date_id.get(position)));
        holder.length_id.setText(String.valueOf(length_id.get(position)));
        holder.level_id.setText(String.valueOf(level_id.get(position)));

    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public void filterList(ArrayList<String> filterList){
        name_id = filterList;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        Button button;
        TextView name_id, location_id, date_id, length_id, level_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_id = itemView.findViewById(R.id.textname);
            location_id = itemView.findViewById(R.id.textlocation);
            date_id = itemView.findViewById(R.id.textdate);
            length_id = itemView.findViewById(R.id.textlength);
            level_id = itemView.findViewById(R.id.textlevel);
            button = itemView.findViewById(R.id.Observation);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ObservationActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
