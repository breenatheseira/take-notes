package com.example.sqltest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqltest.models.Notes;
import com.example.sqltest.models.NotesDatabaseHelper;

public class MainActivity extends ActionBarActivity {

	EditText title_text, note_text;
	String doc_id, note;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		title_text = (EditText) findViewById(R.id.maTB_Title);
		note_text = (EditText) findViewById(R.id.maTA_Note);

	}

	private void save() {
		try {

			doc_id = title_text.getText().toString();
			note = note_text.getText().toString();
			Notes newNote = new Notes();

			newNote.setDocId(doc_id);
			newNote.setNote(note);
			
			NotesDatabaseHelper db = new NotesDatabaseHelper(MainActivity.this);
			int note_id = db.getLastNoteId(); 
			
			if (note_id > 0){
				newNote.setId(String.valueOf(note_id + 1));
			} else {
				newNote.setId(String.valueOf(1));
			}
			db.addNote(newNote);
			
			Toast.makeText(this, newNote.getDocId() + " saved!", Toast.LENGTH_SHORT).show();
			Log.d("NewNote", "NewNote: " + newNote.getId() + " " + newNote.getDocId() + " " + newNote.getNote());
			
			Intent intent = new Intent(MainActivity.this,ViewNotesActivity.class);
			startActivity(intent);
			finish();
			
		} catch (Exception e) {
			Log.d("Database insert", e.toString());
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.viewnote, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.save) {
			save();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
