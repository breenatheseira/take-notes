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
		title = db.getOneNoteRow("doc_id", getIntent().getExtras().toString());
		noteContent  = db.getOneNoteRow("note", getIntent().getExtras().toString());
	}
}
