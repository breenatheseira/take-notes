package com.example.sqltest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;

public class ViewNoteActivity extends ActionBarActivity {

	String title, noteContent, note_id;
	EditText docId, note;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_view_note);
		
		docId = (EditText) findViewById(R.id.vna_title);
		note = (EditText) findViewById(R.id.vna_note);
		setNotesContents();

		docId.setText(title);
		note.setText(noteContent);
	}
	
	private void setNotesContents(){
		DatabaseHelper db = new DatabaseHelper(ViewNoteActivity.this);
		
		// Rafael T. (2011) Load data to android list view with record id and pass the exact record id onListItemClick. [Online]. Available from: http://stackoverflow.com/questions/7406123/load-data-to-android-list-view-with-record-id-and-pass-the-exact-record-id-onlis [Accessed: 2 May 2015].

		String id = String.valueOf((getIntent().getIntExtra("id", 0)));
		title = db.getOneNoteRow("doc_id", id);
		noteContent  = db.getOneNoteRow("note", id);
	}
}
