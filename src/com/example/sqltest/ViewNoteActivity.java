package com.example.sqltest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.EditText;

import com.example.sqltest.models.Notes;
import com.example.sqltest.models.NotesDatabaseHelper;

public class ViewNoteActivity extends ActionBarActivity {

	Notes noteViewed;
	EditText docId, note;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_view_note);
		
		docId = (EditText) findViewById(R.id.vna_title);
		note = (EditText) findViewById(R.id.vna_note);
		
		setNotesContents();
	}
	
	private void setNotesContents(){
		NotesDatabaseHelper db = new NotesDatabaseHelper(ViewNoteActivity.this);
		
		// Rafael T. (2011) Load data to android list view with record id and pass the exact record id onListItemClick. [Online]. Available from: http://stackoverflow.com/questions/7406123/load-data-to-android-list-view-with-record-id-and-pass-the-exact-record-id-onlis [Accessed: 2 May 2015].
		String id = String.valueOf((getIntent().getStringExtra("id")));
		
		noteViewed = new Notes(id, db.getOneNoteRow("doc_id", id), db.getOneNoteRow("note", id));	
		docId.setText(noteViewed.getDocId());
		note.setText(noteViewed.getNote());
	}
}
