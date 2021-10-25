package com.uoit.noteme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter notesAdapter;
    private StaggeredGridLayoutManager staggeredLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        ImageView imageBack = findViewById(R.id.imageBack2);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                SearchResultActivity.super.onBackPressed();
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        List<NotesModel> notes = (ArrayList<NotesModel>) bundle.getSerializable("result");
        System.out.println(notes.toString());
        updateNotes(notes);
    }

    private void updateNotes(List<NotesModel> notes) {
        recyclerView = findViewById(R.id.notesRecyclerView);
        recyclerView.setHasFixedSize(false);

        staggeredLayoutManager = new StaggeredGridLayoutManager(2,1);
        staggeredLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        recyclerView.setLayoutManager(staggeredLayoutManager);

        recyclerView.setLayoutManager(staggeredLayoutManager);
        notesAdapter = new NotesAdapter(notes, SearchResultActivity.this);
        recyclerView.setAdapter(notesAdapter);
    }
}