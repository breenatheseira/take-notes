package com.example.sqltest;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ViewNotesActivity extends ActionBarActivity {

	ListView noteList;
	String[] note_ids = {}, titles = {}, notes = {};
	LinearLayout emptyLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_notes);
		
		noteList = (ListView) findViewById(R.id.vna_listview);
		emptyLayout = (LinearLayout) findViewById(R.id.vnaLL_emptyList);

		loadListView();

	}

	private void loadListView() {
		DatabaseHelper db = new DatabaseHelper(ViewNotesActivity.this);
		note_ids = db.getOneNoteColumn("id");
		titles = db.getOneNoteColumn("doc_id");
		notes = db.getOneNoteColumn("note");
		
		String[] me = {"hi", "what's up"};
		Log.d("VNA", me[0].toString());
		
		ArrayList<String> bla = new ArrayList<String>();
		bla.addAll(Arrays.asList(me));
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewNotesActivity.this, android.R.layout.simple_list_item_1,bla);
		
		noteList.setAdapter(adapter);
		noteList.setEmptyView(emptyLayout);
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
