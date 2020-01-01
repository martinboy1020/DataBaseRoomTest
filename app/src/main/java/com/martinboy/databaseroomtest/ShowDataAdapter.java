package com.martinboy.databaseroomtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.martinboy.databaseroomtest.database.UserEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShowDataAdapter extends RecyclerView.Adapter<ShowDataAdapter.ViewHolder> {

    private List<UserEntity> userEntityList;
    private ShowDataActivity showDataActivity;

    public void setShowDataActivity(ShowDataActivity act) {
        showDataActivity = act;
    }

    @NonNull
    @Override
    public ShowDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_show_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.textName.setText(userEntityList.get(holder.getAdapterPosition()).getName());
        holder.textLocation.setText(userEntityList.get(holder.getAdapterPosition()).getLocation());
        holder.layoutBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showDataActivity != null)
                    showDataActivity.editData(userEntityList.get(holder.getAdapterPosition()));
            }
        });

    }

    void setUserEntityLiveData(List<UserEntity> userEntityLiveData) {
        this.userEntityList = userEntityLiveData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (userEntityList != null){
            return userEntityList.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layoutBg;
        TextView textName;
        TextView textLocation;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutBg = itemView.findViewById(R.id.layout_bg);
            textName = itemView.findViewById(R.id.text_name);
            textLocation = itemView.findViewById(R.id.text_location);
        }
    }
}
