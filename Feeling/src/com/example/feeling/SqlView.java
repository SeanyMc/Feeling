package com.example.feeling;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class SqlView extends Activity {

	private DatabaseConnectToActivities info = new DatabaseConnectToActivities(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlite_data);

		TextView tv = (TextView) findViewById(R.id.tv_sql_feelings);
		TextView tv1 = (TextView) findViewById(R.id.tv_sql_needs);
		TextView tv2 = (TextView) findViewById(R.id.tv_sql_dates);
		

		info.open();

		String feelings = info.getDataForFeelings();
		String needs = info.getDataForNeeds();
		String dates = info.getDataForDates();

		tv.setText(feelings);
		tv1.setText(needs);
		tv2.setText(dates);
		info.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowup = getMenuInflater();
		blowup.inflate(R.menu.my_menu, menu);

		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 

		switch (item.getItemId()) {

		case R.id.delete_history:
			
			info.open();
			info.deleteAllData();
			TextView tv = (TextView) findViewById(R.id.tv_sql_feelings);
			TextView tv1 = (TextView) findViewById(R.id.tv_sql_needs);
			TextView tv2 = (TextView) findViewById(R.id.tv_sql_dates);
			String feelings = info.getDataForFeelings();
			String needs = info.getDataForNeeds();
			String dates = info.getDataForDates();

			tv.setText(feelings);
			tv1.setText(needs);
			tv2.setText(dates);
			
			info.close();
			break;

		}
		return false;
	}
}