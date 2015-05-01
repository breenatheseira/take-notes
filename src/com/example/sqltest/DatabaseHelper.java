package com.example.sqltest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "orientMe";

	// Notes table name
	private static final String TABLE_NOTES = "notes";

	// Notes Column name
	private static final String KEY_NOTE_ID = "id";
	private static final String KEY_DOC_ID = "doc_id";
	private static final String KEY_NOTE = "note";	

	// Table Create Statements
	// Note table create statement
	private static final String CREATE_TABLE_NOTES = "CREATE TABLE "
			+ TABLE_NOTES + "(" + KEY_NOTE_ID + " INTEGER PRIMARY KEY,"
			+ KEY_DOC_ID + " TEXT," + KEY_NOTE + " TEXT" + ")";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// creating required tables
		db.execSQL(CREATE_TABLE_NOTES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);

		// create new tables
		onCreate(db);
	}

	public void addNote(int id, String doc_id, String note) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NOTE_ID, id);
		values.put(KEY_DOC_ID, doc_id);
		values.put(KEY_NOTE, note);

		// insert row
		db.insert(TABLE_NOTES, null, values);
		db.close();
	}

//	public void getNote(long todo_id) {
//		SQLiteDatabase db = this.getReadableDatabase();
//
//		String selectQuery = "SELECT  * FROM " + TABLE_TODO + " WHERE "
//				+ KEY_ID + " = " + todo_id;
//
//		Log.e(LOG, selectQuery);
//
//		Cursor c = db.rawQuery(selectQuery, null);
//
//		if (c != null)
//			c.moveToFirst();
//
//		Todo td = new Todo();
//		td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
//		td.setNote((c.getString(c.getColumnIndex(KEY_TODO))));
//		td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
//
//		return td;
//	}
//
//	public List<Todo> getAllToDos() {
//		List<Todo> todos = new ArrayList<Todo>();
//		String selectQuery = "SELECT  * FROM " + TABLE_TODO;
//
//		Log.e(LOG, selectQuery);
//
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor c = db.rawQuery(selectQuery, null);
//
//		// looping through all rows and adding to list
//		if (c.moveToFirst()) {
//			do {
//				Todo td = new Todo();
//				td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
//				td.setNote((c.getString(c.getColumnIndex(KEY_TODO))));
//				td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
//
//				// adding to todo list
//				todos.add(td);
//			} while (c.moveToNext());
//		}
//
//		return todos;
//	}
//
//	public int updateToDo(Todo todo) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_TODO, todo.getNote());
//		values.put(KEY_STATUS, todo.getStatus());
//
//		// updating row
//		return db.update(TABLE_TODO, values, KEY_ID + " = ?",
//				new String[] { String.valueOf(todo.getId()) });
//	}
//
//	public void deleteToDo(long tado_id) {
//		SQLiteDatabase db = this.getWritableDatabase();
//		db.delete(TABLE_TODO, KEY_ID + " = ?",
//				new String[] { String.valueOf(tado_id) });
//	}
//
//	public int getToDoCount() {
//		String countQuery = "SELECT  * FROM " + TABLE_TODO;
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(countQuery, null);
//
//		int count = cursor.getCount();
//		cursor.close();
//
//		// return count
//		return count;
//	}
//
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}
//
}
