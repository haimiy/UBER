package com.example.uber;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable {
    Context c;
    ArrayList<Model> models , filterList;
    CustomFilter filter;

    public MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
        this.filterList = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myholder, int i) {
        myholder.mTitle.setText(models.get(i).getTitle());
//        myholder.mDes.setText(models.get(i).getDescription());
//        myholder.mImaeView.setImageResource(models.get(i).getImg());

////when you want to use one activity
//        myholder.setItemClickListener(new ItemClickListener() {
//            @Override
//            public void onItemClickListener(View view, int position) {
//                String gtitle = models.get(position).getTitle();
//                String gdescription = models.get(position).getDescription();
//                BitmapDrawable bitmapDrawable = (BitmapDrawable)myholder.mImaeView.getDrawable();
//
//                Bitmap bitmap = bitmapDrawable.getBitmap();
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                byte[] bytes = stream.toByteArray();
//
//                Intent intent = new Intent(c,AnotherActivity.class);
//                intent.putExtra("iTitle", gtitle);
//                intent.putExtra("iDescription", gdescription);
//                intent.putExtra("iImage", bytes);
//                c.startActivity(intent);
//            }
//        });

 //if you want to use diffrent activity
 myholder.setItemClickListener(new ItemClickListener() {
     @Override
     public void onItemClickListener(View view, int position) {
         Intent intent = new Intent();
         if(models.get(position).getTitle().equals("Register")){
             intent = new Intent(c,RegisterActivity.class);
         }

         if(models.get(position).getTitle().equals("Dashboard")){
             intent = new Intent(c,DashboardActivity.class);
         }
         if(models.get(position).getTitle().equals("Map")){
             intent = new Intent(c,MapsActivity.class);
         }
         if(models.get(position).getTitle().equals("Setting")){
             intent = new Intent(c,SettingActivity.class);
         }
         if(models.get(position).getTitle().equals("History")){
             intent = new Intent(c,HistoryActivity.class);
         }
         intent.putExtra("iTitle", models.get(position).getTitle());
         c.startActivity(intent);
     }
 });
    }
    @Override
    public int getItemCount() {
        return models.size();
    }
    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new CustomFilter(filterList,this);
        }
        return filter;
    }
}
