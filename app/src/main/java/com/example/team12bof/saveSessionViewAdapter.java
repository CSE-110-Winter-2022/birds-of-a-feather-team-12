package com.example.team12bof;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team12bof.db.Item;

import java.util.List;

public class saveSessionViewAdapter extends RecyclerView.Adapter<saveSessionViewAdapter.ViewHolder> {


    private final List<? extends Item> myItems;


    public saveSessionViewAdapter(List<? extends Item> myItems) {
        super();
        this.myItems = myItems;
    }

    @NonNull
    @Override
    public saveSessionViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull saveSessionViewAdapter.ViewHolder holder, int position) {
        holder.setItems(myItems.get(position));
    }

    @Override
    public int getItemCount() {
        return this.myItems.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private final TextView itemNameView;
        private Item item;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemNameView = itemView.findViewById(R.id.item_row_name);
            itemView.setOnClickListener(this);
        }

        public void setItems(Item item) {
            this.item = item;
            this.itemNameView.setText(item.getItemName());
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, sessionStudentActivity.class);
            intent.putExtra("item_id", this.item.getItemId());

            context.startActivity(intent);
        }
    }

}
