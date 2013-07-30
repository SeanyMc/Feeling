package com.example.feeling;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseConnectToActivities {

	private SQLiteDatabase database;
	private DatabaseHelp dbHelp; // instance of databasehelp

	// public constructor for database connection
	public DatabaseConnectToActivities(Context context) {
		// create a new database open helper
		dbHelp = new DatabaseHelp(context);

	}

	public void open() throws SQLException {

		database = dbHelp.getWritableDatabase();
	}

	public void close() {
		dbHelp.close();
	}

	// inserts feeling and needs into database
	public long insertEmotions(String feeling, String need, String date) {

		ContentValues emotion = new ContentValues();
		emotion.put(DatabaseHelp.FEELING_ID, feeling);
		emotion.put(DatabaseHelp.NEED_ID, need);
		emotion.put(DatabaseHelp.TIMESTAMP, date);

		// open(); //open the database
		return database.insert(DatabaseHelp.DATABASE_TABLE, null, emotion);

	}

	public String getData() {
		// create columns and reference the rows with a cursor object
		String[] columns = new String[] { DatabaseHelp.ROW_ID,
				DatabaseHelp.FEELING_ID, DatabaseHelp.NEED_ID,
				DatabaseHelp.TIMESTAMP };
		Cursor c = database.query(DatabaseHelp.DATABASE_TABLE, columns, null,
				null, null, null, null);

		String result = "";

		int iRow = c.getColumnIndex(DatabaseHelp.ROW_ID);
		int iFeeling = c.getColumnIndex(DatabaseHelp.FEELING_ID);
		int iNeed = c.getColumnIndex(DatabaseHelp.NEED_ID);
		int iData = c.getColumnIndex(DatabaseHelp.TIMESTAMP);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getString(iRow) + " " + c.getString(iFeeling)
					+ " " + c.getString(iNeed) + " " + c.getString(iData)
					+ "\n";
		}
		;

		c.close();
		return result;

	}

	public String getDataForFeelings() {

		String[] columns = new String[] { DatabaseHelp.FEELING_ID };
		Cursor c = database.query(DatabaseHelp.DATABASE_TABLE, columns, null,
				null, null, null, null);
		String result = "";
		if (c != null) {

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

				result = result
						+ c.getString(c.getColumnIndex(DatabaseHelp.FEELING_ID))
						+ "\n";

			}
			c.close();
			return result;

		}
		return null;
	}

	public String getDataForNeeds() {

		String[] columns = new String[] { DatabaseHelp.NEED_ID };
		Cursor c = database.query(DatabaseHelp.DATABASE_TABLE, columns, null,
				null, null, null, null);
		String result = "";
		if (c != null) {

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

				result = result
						+ c.getString(c.getColumnIndex(DatabaseHelp.NEED_ID))
						+ "\n";

			}
			c.close();
			return result;

		}
		return null;
	}

	public String getDataForDates() {

		String[] columns = new String[] { DatabaseHelp.TIMESTAMP };
		Cursor c = database.query(DatabaseHelp.DATABASE_TABLE, columns, null,
				null, null, null, null);
		String result = "";
		if (c != null) {

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

				result = result
						+ c.getString(c.getColumnIndex(DatabaseHelp.TIMESTAMP))
						+ "\n";

			}
			c.close();
			return result;

		}
		return null;
		
		
		
	}
	
	public void deleteAllData(){
		
		database.delete(DatabaseHelp.DATABASE_TABLE, null, null);
	}
}
