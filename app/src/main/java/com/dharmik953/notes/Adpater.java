package com.dharmik953.notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Adpater extends RecyclerView.Adapter <Adpater.MyViewHolder> implements Filterable {

    Context context;
    Activity activity;
    List<Model> notes_list;
    List<Model> newList;

    public Adpater(Context context, Activity activity, List<Model> notes_list) {
        this.context = context;
        this.activity = activity;
        this.notes_list = notes_list;
        newList = new ArrayList<>(notes_list);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adpater.MyViewHolder holder, int position) {

        holder.title.setText(notes_list.get(position).getTitle());
        holder.description.setText(notes_list.get(position).getDescription());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,update_notes.class);
                intent.putExtra("title" , notes_list.get(position).getTitle());
                intent.putExtra("description" , notes_list.get(position).getDescription());
                intent.putExtra("id" , notes_list.get(position).getId());

                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes_list.size();
    }

    @Override
    public Filter getFilter() {

        Filter exampleFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Model> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0){
                    filteredList.addAll(newList);
                }
                else {
                    String filterPattern  = constraint.toString().toLowerCase().trim();

                    for (Model item: newList){
                        if (item.getTitle().toLowerCase().contains(filterPattern)){
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
                newList.clear();
                newList.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };

        return exampleFilter;
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        RelativeLayout layout;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.note);
            layout = itemView.findViewById(R.id.note_layout);
        }
    }

    public List<Model> getList (){

        return  notes_list;
    }

    public void removeItem(int position){
        notes_list.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Model item, int position){
        notes_list.add(position,item);
        notifyItemInserted(position);

    }
}
