package com.example.sqltest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	// Database Version
	protected static final int DATABASE_VERSION = 1;

	// Database Name
	protected static final String DATABASE_NAME = "orientMe";
	
	// Table Names
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

	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}
}
