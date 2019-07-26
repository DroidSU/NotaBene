/*
 * Created by Sujoy Datta. Copyright (c) 2019. All rights reserved.
 *
 * To the person who is reading this..
 * When you finally understand how this works, please do explain it to me too at sujoydatta26@gmail.com
 * P.S.: In case you are planning to use this without mentioning me, you will be met with mean judgemental looks and sarcastic comments.
 */

package com.morningstar.notabene.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.michaldrabik.tapbarmenulib.TapBarMenu;
import com.morningstar.notabene.R;
import com.morningstar.notabene.adapters.NoteListRecyclerAdapter;
import com.morningstar.notabene.models.NoteModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainActivityToolbar)
    Toolbar toolbar;
    @BindView(R.id.mainActivityNoteRecycler)
    RecyclerView recyclerView;
    @BindView(R.id.tapBarMenu)
    TapBarMenu tapBarMenu;
    @BindView(R.id.mainActivityNoNotesTextView)
    TextView textViewNoNotes;
    @BindView(R.id.bottom_menu_text_note)
    CircleImageView circleImageViewTextNote;
    @BindView(R.id.bottom_menu_voice_note)
    CircleImageView circleImageViewVoiceNote;

    private NoteListRecyclerAdapter noteListRecyclerAdapter;
    private Realm realm;
    private ArrayList<NoteModel> noteModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        fetchNotes();

        setupRecycler();
    }

    private void setupRecycler() {
        noteListRecyclerAdapter = new NoteListRecyclerAdapter(this, noteModelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        if (noteModelArrayList.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            textViewNoNotes.setVisibility(View.GONE);
            recyclerView.setAdapter(noteListRecyclerAdapter);
        }
        else{
            recyclerView.setVisibility(View.GONE);
            textViewNoNotes.setVisibility(View.VISIBLE);
        }
    }

    private void fetchNotes() {
        RealmResults<NoteModel> realmResults = realm.where(NoteModel.class).findAll();
        noteModelArrayList = new ArrayList<>();
        noteModelArrayList.addAll(realmResults);

    }

    @OnClick(R.id.tapBarMenu)
    public void toggleMenu(){
        tapBarMenu.toggle();
    }

    @OnClick(R.id.bottom_menu_text_note)
    public void takeTextNote(){
        tapBarMenu.close();
        Intent intent = new Intent(MainActivity.this, TextNoteActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
    }

    @OnClick(R.id.bottom_menu_voice_note)
    public void takeVoiceNote(){
        Toast.makeText(this, "Nothing yet", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!realm.isClosed())
            realm.close();
    }
}
