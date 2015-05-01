package com.example.sqltest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	Button save_button;
	EditText title_text, note_text;
	String doc_id, note;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		save_button = (Button) findViewById(R.id.maB_Save);
		title_text = (EditText) findViewById(R.id.maTB_Title);
		note_text = (EditText) findViewById(R.id.maTA_Note);

	}

	public void save(View button) {
		try {
			doc_id = title_text.getText().toString();
			note = note_text.getText().toString();

			DatabaseHelper db = new DatabaseHelper(MainActivity.this);

			db.addNote(1, doc_id, note);

			Log.d("Database insert", "Successful");
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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
