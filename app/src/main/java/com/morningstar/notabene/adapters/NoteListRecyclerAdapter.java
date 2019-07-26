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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.morningstar.notabene.R;
import com.morningstar.notabene.models.realmmodels.NoteModel;

import java.util.ArrayList;

public class NoteListRecyclerAdapter extends RecyclerView.Adapter<NoteListRecyclerAdapter.NoteListItemViewHolder> {
    private View view;

    private Context context;
    private ArrayList<NoteModel> noteModels;

    public NoteListRecyclerAdapter(Context context, ArrayList<NoteModel> noteModels) {
        this.context = context;
        this.noteModels = noteModels;
    }

    @NonNull
    @Override
    public NoteListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.note_list_item_layout, parent, false);

        return new NoteListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListItemViewHolder holder, int position) {
        holder.textViewNoteTitle.setText(noteModels.get(position).getNoteTitle());
        holder.textViewNoteText.setText(noteModels.get(position).getNoteText());
        holder.textViewNoteCreationDate.setText(noteModels.get(position).getNoteCreatedDate());
        holder.imageViewNoteImage.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return noteModels.size();
    }

    class NoteListItemViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNoteTitle;
        TextView textViewNoteText;
        TextView textViewNoteCreationDate;
        ImageView imageViewNoteImage;

        public NoteListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNoteTitle = itemView.findViewById(R.id.note_list_item_title);
            textViewNoteText = itemView.findViewById(R.id.note_list_item_text);
            textViewNoteCreationDate = itemView.findViewById(R.id.note_list_item_date);
            imageViewNoteImage = itemView.findViewById(R.id.note_list_item_image);
        }
    }
}
