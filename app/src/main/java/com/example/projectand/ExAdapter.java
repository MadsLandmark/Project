package com.example.projectand;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExAdapter extends RecyclerView.Adapter<ExAdapter.ExViewHolder> implements Filterable {

    private List<ExItem> exList;
    private List<ExItem> exListFull;


    class ExViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;

        ExViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);

        }
    }

    ExAdapter(List<ExItem> exList) {
        this.exList = exList;
        exListFull = new ArrayList<>(exList);
    }

    @NonNull
    @Override
    public ExViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ex_item, parent, false);
        return new ExViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExViewHolder holder, int position) {
        ExItem currentItem = exList.get(position);

        holder.imageView.setImageResource(currentItem.getImageResource());
        holder.textView1.setText(currentItem.getText1());
        holder.textView2.setText(currentItem.getText2());
        holder.textView3.setText(currentItem.getText3());
    }

    @Override
    public int getItemCount() {
        return exList.size();
    }

    @Override
    public Filter getFilter() {
        return exFilter;
    }

    private Filter exFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ExItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ExItem item : exListFull) {
                    if (item.getText1().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exList.clear();
            exList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };


}
