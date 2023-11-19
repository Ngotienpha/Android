package com.example.mhike;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ObservationAdapter extends RecyclerView.Adapter<ObservationAdapter.MyViewHolder> {
    private Context context;
    private List<String> name1_id;
    private List<String> name2_id;
    private List<String> name3_id;
    private List<String> time_id;
    private List<String> comment_id;
    public ObservationAdapter(Context context, ArrayList name1_id, ArrayList name2_id, ArrayList name3_id, ArrayList time_id, ArrayList comment_id) {
        this.context = context;
        this.name1_id = name1_id;
        this.name2_id = name2_id;
        this.name3_id = name3_id;
        this.time_id = time_id;
        this.comment_id = comment_id;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user2entry,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name1_id.setText(String.valueOf(name1_id.get(position)));
        holder.name2_id.setText(String.valueOf(name2_id.get(position)));
        holder.name3_id.setText(String.valueOf(name3_id.get(position)));
        holder.time_id.setText(String.valueOf(time_id.get(position)));
        holder.comment_id.setText(String.valueOf(comment_id.get(position)));

    }

    @Override
    public int getItemCount() {
        return name1_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name1_id, name2_id, name3_id, time_id, comment_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name1_id = itemView.findViewById(R.id.textname1);
            name2_id = itemView.findViewById(R.id.textname2);
            name3_id = itemView.findViewById(R.id.textname3);
            time_id = itemView.findViewById(R.id.texttime);
            comment_id = itemView.findViewById(R.id.textcomment);
        }
    }
}
