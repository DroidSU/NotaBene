/*
 * Created by Sujoy Datta. Copyright (c) 2019. All rights reserved.
 *
 * To the person who is reading this..
 * When you finally understand how this works, please do explain it to me too at sujoydatta26@gmail.com
 * P.S.: In case you are planning to use this without mentioning me, you will be met with mean judgemental looks and sarcastic comments.
 */

package com.morningstar.notabene.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.morningstar.notabene.R;

import java.util.ArrayList;

public class ImagesRecyclerAdapter extends RecyclerView.Adapter<ImagesRecyclerAdapter.ImageRecyclerViewHolder> {

    private Context context;
    private ArrayList<String> imagePaths;
    private View view;

    public ImagesRecyclerAdapter(Context context, ArrayList<String> imagePaths) {
        this.context = context;
        this.imagePaths = imagePaths;
    }

    @NonNull
    @Override
    public ImageRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_image_recycler, parent, false);

        return new ImageRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return imagePaths.size();
    }

    class ImageRecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_recycler_item_imageView);
        }
    }
}
