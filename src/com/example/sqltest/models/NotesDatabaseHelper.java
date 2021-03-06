package com.example.sqltest.models;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sqltest.DatabaseHelper;

public class NotesDatabaseHelper extends DatabaseHelper {

	// Notes table name
	private static final String TABLE_NOTES = "notes";

	// Notes Column name
	private static final String KEY_NOTE_ID = "id";
	private static final String KEY_DOC_ID = "doc_id";
	private static final String KEY_NOTE = "note";

	private SQLiteDatabase rdb = this.getReadableDatabase();
	private SQLiteDatabase wdb = this.getWritableDatabase();

	public NotesDatabaseHelper(Context context) {
		super(context);
	}

	public void addNote(Notes note) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NOTE_ID, note.getId());
		values.put(KEY_DOC_ID, note.getDocId());
		values.put(KEY_NOTE, note.getNote());

		// insert row
		db.insert(TABLE_NOTES, null, values);
		db.close();
	}

	public int getLastNoteId() {
		String sql = "SELECT " + KEY_NOTE_ID + " FROM " + TABLE_NOTES;
		int id = -1;
		try {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor c = db.rawQuery(sql, null);

			if (c.moveToLast()) {
				id = Integer.parseInt(c.getString(0));
				return id;
			}
			Log.d("Get Last ID", "Success");
		} catch (Exception e) {
			Log.d("Get Last ID", e.toString());
		}
		return id;
	}

	// Tamada, R. (2013) Android SQLite Database with Multiple Tables. [Online].
	// Available from:
	// http://www.androidhive.info/2013/09/android-sqlite-database-with-multiple-tables/
	// [Accessed: 1 May 2015].
	public List<Notes> getAllNotes() {
		List<Notes> notes = new ArrayList<Notes>();
		String sql = "SELECT * FROM " + TABLE_NOTES;

		Cursor c = rdb.rawQuery(sql, null);

		if (c.moveToFirst()) {
			do {
				Notes note = new Notes();
				note.setId(c.getString(c.getColumnIndex(KEY_NOTE_ID)));
				note.setDocId(c.getString(c.getColumnIndex(KEY_DOC_ID)));
				note.setNote(c.getString(c.getColumnIndex(KEY_NOTE)));

				notes.add(note);
			} while (c.moveToNext());
		}
		return notes;
	}

	public String getOneNoteRow(String key, String id) {
		String value = null;
		String sql = "Select " + key + " FROM " + TABLE_NOTES + " WHERE id = "
				+ id;
		Cursor c = rdb.rawQuery(sql, null);

		if (c.moveToNext()) {
			value = c.getString(0);
		}
		return value;
	}

	public int updateNote(Notes note) {
		ContentValues values = new ContentValues();
		values.put(KEY_DOC_ID, note.getDocId());
		values.put(KEY_NOTE, note.getNote());

		// updating row
		int i = wdb.update(TABLE_NOTES, values, KEY_NOTE_ID + " = ?",
				new String[] { String.valueOf(note.getId()) });
		Log.d("update values", i + " > doc id: " + note.getDocId() + ", note: "
				+ note.getNote());
		return i;
	}

	public int deleteNote(String id) {
		return wdb.delete(TABLE_NOTES, KEY_NOTE_ID + " = ?",new String[] { String.valueOf(id) });
	}
	//
	// public int getToDoCount() {
	// String countQuery = "SELECT  * FROM " + TABLE_TODO;
	// SQLiteDatabase db = this.getReadableDatabase();
	// Cursor cursor = db.rawQuery(countQuery, null);
	//
	// int count = cursor.getCount();
	// cursor.close();
	//
	// // return count
	// return count;
	// }
	//
}
