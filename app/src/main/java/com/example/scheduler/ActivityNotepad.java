package com.example.scheduler;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class ActivityNotepad extends AppCompatActivity {

    static ArrayList<String> notes =new ArrayList<>();
   static ArrayAdapter arrayadapter;

   public void addNewNote(View view)
   {
       Intent intent =new Intent(getApplicationContext(),Noteselect.class);
       startActivity(intent);
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);


        ListView listview = (ListView) findViewById(R.id.listview);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.scheduler", Context.MODE_PRIVATE);

        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes",null);

        if(set==null)
        {
            notes.add("Example note");
        }
        else
            {
                notes= new ArrayList(set);
        }

         arrayadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,notes);

        listview.setAdapter(arrayadapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent =new Intent(getApplicationContext(),Noteselect.class);
                intent.putExtra("noteId",i);
                startActivity(intent);
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(ActivityNotepad.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("DELETE!")
                        .setMessage("Do ypu want to delete this note?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(position);
                                arrayadapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.scheduler", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet(ActivityNotepad.notes);
                                sharedPreferences.edit().putStringSet("notes",set).apply();

                            }
                        })
                        .setNegativeButton("NO",null)
                        .show();
                return true;
            }
        });

    }



}
