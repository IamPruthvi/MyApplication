package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private List<NotificationData> notificationList;

    public NotificationAdapter(List<NotificationData> list) {
        this.notificationList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, text, packageName;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            text = itemView.findViewById(R.id.text);
            packageName = itemView.findViewById(R.id.packageName);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NotificationData noti = notificationList.get(position);
        holder.title.setText(noti.title);
        holder.text.setText(noti.text);
        holder.packageName.setText(noti.packageName);
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }
}

