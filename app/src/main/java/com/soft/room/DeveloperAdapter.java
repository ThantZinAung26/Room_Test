package com.soft.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.soft.room.model.entity.Developer;

public class DeveloperAdapter extends ListAdapter<Developer, DeveloperAdapter.DeveloperViewHolder> {


    public interface OnAdapterItemClickListener {
        void onClick(Developer dev);
    }

    private static final DiffUtil.ItemCallback<Developer> DIFF_UTIL = new DiffUtil.ItemCallback<Developer>() {
        @Override
        public boolean areItemsTheSame(@NonNull Developer oldItem, @NonNull Developer newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Developer oldItem, @NonNull Developer newItem) {
            return oldItem.equals(newItem);
        }
    };

    private OnAdapterItemClickListener onAdapterItemClickListener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickListener onAdapterItemClickListener) {
        this.onAdapterItemClickListener = onAdapterItemClickListener;
    }

    public DeveloperAdapter(){
        super(DIFF_UTIL);
    }

    @NonNull
    @Override
    public DeveloperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_developer, parent, false);
        return new DeveloperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeveloperViewHolder holder, int position) {

        Developer dev = getItem(position);
        holder.tvName.setText(dev.getName());
        holder.tvSkill.setText(dev.getSkill().name());
        holder.tvDepartment.setText(dev.getDeparment().getName());

    }

    class DeveloperViewHolder extends RecyclerView.ViewHolder {

        final TextView tvName, tvSkill, tvDepartment;

        public DeveloperViewHolder(@NonNull View itemView) {

            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvSkill = itemView.findViewById(R.id.tvSkill);
            tvDepartment = itemView.findViewById(R.id.tvDeperment);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onAdapterItemClickListener != null) {
                        onAdapterItemClickListener.onClick(getItem(getAdapterPosition()));
                    }
                }
            });

        }
    }

}
