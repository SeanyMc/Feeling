package com.example.feeling;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelp extends SQLiteOpenHelper {

	
	
	//set up database variables
	public static final int DATABASE_VERSION = 2;
	
	public static final String DATABASE_NAME = "feelings_needs";
	public static final String DATABASE_TABLE = "feelings_needs_table";
	public static final String ROW_ID = "_id";
	public static final String FEELING_ID = "feelings";
	public static final String NEED_ID = "needs";
	public static final String TIMESTAMP = "date";
	
	
	
	
	
	//creates the helper
	public DatabaseHelp(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}



	//set-up the table
	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
				
		String CREATE_TABLES = "CREATE TABLE " + DATABASE_TABLE
	            + "("
	            + ROW_ID + " integer primary key autoincrement, "
	            + FEELING_ID + " text, "
	            + NEED_ID + " text, "
	            + TIMESTAMP + " text"
	            +");";
		
		
		database.execSQL(CREATE_TABLES);
	}



	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		 database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
		    onCreate(database);
	}

}
