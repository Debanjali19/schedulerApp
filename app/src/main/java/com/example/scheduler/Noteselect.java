package com.example.scheduler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

import static android.media.CamcorderProfile.get;

public class Noteselect extends AppCompatActivity {
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteselect);

        EditText editText = (EditText) findViewById(R.id.editText);

        Intent intent = getIntent();
         noteId = intent.getIntExtra("noteId", -1);

        if (noteId != -1) {
            editText.setText(ActivityNotepad.notes.get(noteId));
            ActivityNotepad.arrayadapter.notifyDataSetChanged();
        }
        else
        {
            ActivityNotepad.notes.add("");
            noteId=ActivityNotepad.notes.size()-1;
        }

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ActivityNotepad.notes.set(noteId,s.toString());
                ActivityNotepad.arrayadapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.scheduler", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(ActivityNotepad.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

}
