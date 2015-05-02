package com.example.sqltest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqltest.models.Notes;
import com.example.sqltest.models.NotesDatabaseHelper;

public class ViewNoteActivity extends ActionBarActivity {

	String oldTitle;
	Notes noteViewed;
	EditText docId, note;
	NotesDatabaseHelper db;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_view_note);
		
		docId = (EditText) findViewById(R.id.vna_title);
		note = (EditText) findViewById(R.id.vna_note);
		db = new NotesDatabaseHelper(ViewNoteActivity.this);
		
		setNotesContents();
	}
	
	private void setNotesContents(){
		// Rafael T. (2011) Load data to android list view with record id and pass the exact record id onListItemClick. [Online]. Available from: http://stackoverflow.com/questions/7406123/load-data-to-android-list-view-with-record-id-and-pass-the-exact-record-id-onlis [Accessed: 2 May 2015].
		String id = String.valueOf((getIntent().getStringExtra("id")));
		
		noteViewed = new Notes(id, db.getOneNoteRow("doc_id", id), db.getOneNoteRow("note", id));	
		docId.setText(noteViewed.getDocId());
		note.setText(noteViewed.getNote());
		oldTitle = noteViewed.getDocId();
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.savenote, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.save) {
        	noteViewed.setDocId(docId.getEditableText().toString());
        	noteViewed.setNote(note.getEditableText().toString());
        	
            if (db.updateNote(noteViewed) == 1){
            	Toast.makeText(this, oldTitle + " updated", Toast.LENGTH_SHORT).show();
            	finish();
            } else {
            	Toast.makeText(this, "Error: " + noteViewed.getDocId() + " could not be updated", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
