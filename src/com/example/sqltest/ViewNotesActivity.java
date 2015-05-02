package com.example.sqltest;

import java.util.ArrayList;
import java.util.List;

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

import com.example.sqltest.models.Notes;
import com.example.sqltest.models.NotesDatabaseHelper;

public class ViewNotesActivity extends ActionBarActivity {

	ListView noteList;
	LinearLayout emptyLayout;
	List<Notes> notes;

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
					int position, long l) {
				
				// victor (2010) Android - Getting Database ID from ListView Selection. [Online]. Available from: http://stackoverflow.com/questions/12268721/android-getting-database-id-from-listview-selection [Accessed: 2 May 2015].
				String id = notes.get(position).getId(); 
				
				Intent intent = new Intent(ViewNotesActivity.this, ViewNoteActivity.class);
				intent.putExtra("id", id);
				startActivity(intent);
			}
		});
			
	}

	private void loadListView() {
		NotesDatabaseHelper db = new NotesDatabaseHelper(ViewNotesActivity.this);
		
		ArrayList<String> titles = new ArrayList<String>(); 
		
		//Tamada, R. (2013) Android SQLite Database with Multiple Tables. [Online]. Available from: http://www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/ [Accessed: 1 May 2015].
		notes = db.getAllNotes();
		for (Notes eachNote : notes){
			titles.add(eachNote.getDocId());
		}
		
		// Developers (n.d.) Layouts. [Online]. Available from: http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews [Accessed: 1 May 2015]. 		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewNotesActivity.this, android.R.layout.simple_list_item_1,titles);
		
		noteList.setAdapter(adapter);
		noteList.setEmptyView(emptyLayout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.viewnotelist, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.add) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
