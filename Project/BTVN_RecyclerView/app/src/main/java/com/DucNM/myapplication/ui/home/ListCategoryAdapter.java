package com.DucNM.myapplication.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.DucNM.myapplication.R;

import java.util.List;

public class ListCategoryAdapter extends RecyclerView.Adapter<ListCategoryAdapter.ViewHolder> {

    private List<Category> data;

    public ListCategoryAdapter(List<Category> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ListCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.categories_item, parent, false);
        Log.d("DucNM_Debug", "create View Holder roi nha");
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCategoryAdapter.ViewHolder holder, int position) {
        Category category = data.get(position);
        holder.setData(category);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivCategory;
        private TextView tvCategory;

        private void bindingView(){
            ivCategory = itemView.findViewById(R.id.ivCategory);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
        private void bindingAction(){

        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bindingView();
            bindingAction();
            Log.d("DucNM_Debug", "ivCategory: " + ivCategory);
            Log.d("DucNM_Debug", "tvCategory: " + tvCategory);
        }

        public void setData(Category category) {
            ivCategory.setImageResource(category.getCategoryResId());
            tvCategory.setText(category.getCategoryName());
        }
    }
}
