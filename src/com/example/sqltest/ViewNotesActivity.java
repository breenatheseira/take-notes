package com.example.sqltest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
		
		noteList = (ListView) findViewById(R.id.vnas_listview);
		emptyLayout = (LinearLayout) findViewById(R.id.vnasLL_emptyList);

		loadListView();
		noteList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(ViewNotesActivity.this, ViewNoteActivity.class);
				intent.putExtra("id", (int)id);
				startActivity(intent);
			}
		});
			
	}

	private void loadListView() {
		DatabaseHelper db = new DatabaseHelper(ViewNotesActivity.this);
		note_ids = db.getOneNoteColumn("id");
		titles = db.getOneNoteColumn("doc_id");
		notes = db.getOneNoteColumn("note");
		
		// Developers (n.d.) Layouts. [Online]. Available from: http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews [Accessed: 1 May 2015]. 		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewNotesActivity.this, android.R.layout.simple_list_item_1,titles);
		
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
