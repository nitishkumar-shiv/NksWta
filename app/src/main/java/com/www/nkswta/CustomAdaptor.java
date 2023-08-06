package com.www.nkswta;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> {
    private ArrayList<ModalClass> listData;
    private OnItemClickListener clickListener;
    FragmentHome fragmentHome;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public CustomAdaptor(ArrayList<ModalClass> listData, OnItemClickListener clickListener) {
        this.listData = listData;
        this.clickListener = clickListener;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView taskID, taskTitle, priority, taskType;
        public ProgressBar progress;
        public CardView itemCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.taskID = itemView.findViewById(R.id.taskID);
            this.taskTitle = itemView.findViewById(R.id.taskTitle);
            this.priority = itemView.findViewById(R.id.priority);
            this.taskType = itemView.findViewById(R.id.taskType);
            this.progress = itemView.findViewById(R.id.progressBar);
            this.itemCard =itemView.findViewById(R.id.taskItemCard);

        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.recycler_view_items,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {
        final ModalClass myListData = listData.get(position);
        holder.taskID.setText(listData.get(position).getTaskID());
        holder.taskTitle.setText(listData.get(position).getTaskTitle());
        holder.priority.setText(listData.get(position).getPriority());
        holder.taskType.setText(listData.get(position).getTaskType());
        holder.progress.setProgress(listData.get(position).getProgress(),true);

        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION && clickListener != null) {
                    clickListener.onItemClick(adapterPosition);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
