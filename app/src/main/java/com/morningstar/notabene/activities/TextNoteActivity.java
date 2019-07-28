/*
 * Created by Sujoy Datta. Copyright (c) 2019. All rights reserved.
 *
 * To the person who is reading this..
 * When you finally understand how this works, please do explain it to me too at sujoydatta26@gmail.com
 * P.S.: In case you are planning to use this without mentioning me, you will be met with mean judgemental looks and sarcastic comments.
 */

package com.morningstar.notabene.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.morningstar.notabene.R;
import com.morningstar.notabene.managers.ConstantManager;
import com.morningstar.notabene.managers.DateTimeManager;
import com.morningstar.notabene.models.eventbusmodels.TextNoteSavedModel;
import com.morningstar.notabene.models.realmmodels.NoteModel;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class TextNoteActivity extends AppCompatActivity {

    @BindView(R.id.textNoteToolbar)
    Toolbar toolbar;
    @BindView(R.id.textNote_editText_title)
    TextView textViewNoteTitle;
    @BindView(R.id.textNote_editText_text)
    TextView textViewNoteText;
    @BindView(R.id.textNote_animation_view)
    LottieAnimationView animationView;
    @BindView(R.id.textNote_images_recycler)
    RecyclerView imageRecycler;

    private long noteId;
    private String noteTitle;
    private String noteText;
    private NoteModel noteModel;
    private Realm realm;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        setContentView(R.layout.activity_text_note);
        ButterKnife.bind(this);

        animationView.setVisibility(View.GONE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        realm = Realm.getDefaultInstance();

        intent = getIntent();
        if (intent != null)
            getIntentData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.text_note_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_action_done) {
            noteTitle = textViewNoteTitle.getText().toString();
            noteText = textViewNoteText.getText().toString();
            if (!noteText.isEmpty() && !noteTitle.isEmpty()) {
                saveNote();
                animationView.setVisibility(View.VISIBLE);
                animationView.playAnimation();
                animationView.addAnimatorListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        animationView.setVisibility(View.GONE);
                        onBackPressed();
                    }
                });
            } else {
                Toast.makeText(this, "Cannot save empty note", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    private void getIntentData() {
        noteId = intent.getLongExtra(ConstantManager.noteId, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void saveNote() {
        if (noteId == 0)
            createNewNote();

        EventBus.getDefault().post(new TextNoteSavedModel());
    }

    private void createNewNote() {
        noteId = DateTimeManager.getTimeStamp();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                noteModel = realm.createObject(NoteModel.class, noteId);
                noteModel.setNoteCreatedDate(DateTimeManager.getCurrentDateAsString());
                noteModel.setNoteTitle(noteTitle);
                noteModel.setNoteText(noteText);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!realm.isClosed())
            realm.close();
    }
}
