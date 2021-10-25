package com.uoit.noteme;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    List<NotesModel> allNotes;
    Context context;

    public NotesAdapter(List<NotesModel> allNotes, Context context) {
        this.allNotes = allNotes;
        this.context = context;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        holder.title.setText(allNotes.get(position).getTitle());
        holder.subtitle.setText(allNotes.get(position).getSubtitle());
        holder.body.setText(allNotes.get(position).getBody());
        holder.setBackgroundColor(allNotes.get(position).getColour());
    }


    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subtitle;
        TextView body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_title);
            subtitle = itemView.findViewById(R.id.card_subtitle);
            body = itemView.findViewById(R.id.card_body);
        }
        public void setBackgroundColor(String colour) {
            itemView.setBackgroundColor(Color.parseColor(colour));
        }
    }
}
