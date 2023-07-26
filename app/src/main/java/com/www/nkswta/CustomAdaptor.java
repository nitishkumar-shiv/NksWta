package com.www.nkswta;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> {
    private ModalClass[] listData;

    public CustomAdaptor(ModalClass[] listData) {
        this.listData = listData;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView taskID, taskTitle, priority, taskType;
        public ProgressBar progress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.taskID = itemView.findViewById(R.id.taskID);
            this.taskTitle = itemView.findViewById(R.id.taskTitle);
            this.priority = itemView.findViewById(R.id.priority);
            this.taskType = itemView.findViewById(R.id.taskType);
            this.progress = itemView.findViewById(R.id.progressBar);

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModalClass myListData = listData[position];
        holder.taskID.setText(listData[position].getTaskID());
        holder.taskTitle.setText(listData[position].getTaskTitle());
        holder.priority.setText(listData[position].getPriority());
        holder.taskType.setText(listData[position].getTaskType());
        holder.progress.setProgress(listData[position].getProgress(),true);

    }

    @Override
    public int getItemCount() {
        return listData.length;
    }
}
