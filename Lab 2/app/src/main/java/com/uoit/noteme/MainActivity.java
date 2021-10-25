package com.uoit.noteme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_NOTE = 1;
    NotesModel notesData;
    NotesDatabase notesDatabase;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter notesAdapter;
    private StaggeredGridLayoutManager staggeredLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesDatabase = new NotesDatabase(this);

        List<NotesModel> allNotes = notesDatabase.getAllNotes();

        updateNotes();

        ImageView imageAddNoteMain = findViewById(R.id.imageAddNoteMain);

        imageAddNoteMain.setOnClickListener((v) -> {
            startActivityForResult(new Intent(getApplicationContext(), CreateNoteActivity.class), REQUEST_CODE_ADD_NOTE);
        });

        ImageView searchButton = findViewById(R.id.search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputSearch = findViewById(R.id.inputSearch);
                String query = inputSearch.getText().toString();
                System.out.println(query);
                List<NotesModel> resultNotes = notesDatabase.searchNotes(query);
                System.out.println(resultNotes.toString());

                Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("result", (Serializable) resultNotes);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_NOTE) {
            if (resultCode == Activity.RESULT_OK) {
                notesData = (NotesModel) data.getExtras().getSerializable("note");
            }
        }
        if (notesData.getId() != -10) {
            notesDatabase.addNote(notesData);
        }
        updateNotes();
    }

    private void updateNotes() {
        recyclerView = findViewById(R.id.notesRecyclerView);
        recyclerView.setHasFixedSize(false);

        staggeredLayoutManager = new StaggeredGridLayoutManager(2,1);
        staggeredLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        recyclerView.setLayoutManager(staggeredLayoutManager);

        recyclerView.setLayoutManager(staggeredLayoutManager);
        notesAdapter = new NotesAdapter(notesDatabase.getAllNotes(), MainActivity.this);
        recyclerView.setAdapter(notesAdapter);
    }
}