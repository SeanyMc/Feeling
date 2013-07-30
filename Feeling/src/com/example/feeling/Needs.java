package com.example.feeling;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Needs extends Activity {

	private String gotTheFeeling;
	private DatabaseConnectToActivities database;

	private String date;
	
	private String[] quotes = {"So,you are feeling ","I'm hearing ", " I'm hearing that you're feeling ", "You're feeling ", "Ok, you're feeling ",
			"I see that you're feeling "
			};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.needs);

		// Receive string from feelingsmain

		Bundle gotFeeling = getIntent().getExtras();
		gotTheFeeling = gotFeeling.getString("aFeeling");

		// new database object
		database = new DatabaseConnectToActivities(this);
		database.open();
		initList();

		ListView lv = (ListView) findViewById(R.id.needsLayout);

		SimpleAdapter simpleAdpt = new SimpleAdapter(this, needsList,
				android.R.layout.simple_list_item_1, new String[] { "need" },
				new int[] { android.R.id.text1 });

		lv.setAdapter(simpleAdpt);

		lv.setOnItemClickListener(backToFeelings);
		
		
		//get the date
		Calendar c = Calendar.getInstance(); 
		int date1 = c.get(Calendar.DATE);
		int month = c.get(Calendar.MONTH);
		date = Integer.toString(date1) + " /" + Integer.toString(month);
		
		
		//toast time!
		
		Toast toast = Toast.makeText(Needs.this, "What are you needing?",
				Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();

	}

	List<Map<String, String>> needsList = new ArrayList<Map<String, String>>();
	protected String feelingForDb;

	public void initList() {

		needsList.add(createNeed("need", "space"));
		needsList.add(createNeed("need", "peace"));
		needsList.add(createNeed("need", "calm"));
		needsList.add(createNeed("need", "understanding"));
		needsList.add(createNeed("need", "to be heard"));
		needsList.add(createNeed("need", "to be seen"));
		needsList.add(createNeed("need", "love"));
		needsList.add(createNeed("need", "rest"));
		needsList.add(createNeed("need", "connection"));
		needsList.add(createNeed("need", "acceptance"));
		needsList.add(createNeed("need", "affection"));
		needsList.add(createNeed("need", "closeness"));
		needsList.add(createNeed("need", "compassion"));
		needsList.add(createNeed("need", "empathy"));
		needsList.add(createNeed("need", "intimacy"));
		needsList.add(createNeed("need", "respect"));
		needsList.add(createNeed("need", "trust"));
		needsList.add(createNeed("need", "warmth"));
		needsList.add(createNeed("need", "movement"));
		needsList.add(createNeed("need", "sexual expression"));
		needsList.add(createNeed("need", "honesty"));
		needsList.add(createNeed("need", "play"));
		needsList.add(createNeed("need", "joy"));
		needsList.add(createNeed("need", "humour"));
		needsList.add(createNeed("need", "beauty"));
		needsList.add(createNeed("need", "harmony"));
		needsList.add(createNeed("need", "order"));
		needsList.add(createNeed("need", "inspiration"));
		needsList.add(createNeed("need", "choice"));
		needsList.add(createNeed("need", "freedom"));
		needsList.add(createNeed("need", "spontaneity"));
		needsList.add(createNeed("need", "challenge"));
		needsList.add(createNeed("need", "contribution"));
		needsList.add(createNeed("need", "creativity"));
		needsList.add(createNeed("need", "growth"));
		needsList.add(createNeed("need", "hope"));
		needsList.add(createNeed("need", "purpose"));
		needsList.add(createNeed("need", "stimulation"));
		needsList.add(createNeed("need", "mourning"));
		needsList.add(createNeed("need", "clarity"));
		needsList.add(createNeed("need", "to matter"));
		needsList.add(createNeed("need", "self-expression"));
		

	}

	private HashMap<String, String> createNeed(String key, String name) {
		HashMap<String, String> need = new HashMap<String, String>();
		need.put(key, name);

		return need;

	}

	OnItemClickListener backToFeelings = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String need = needsList.get(arg2).get("need").toString();
		
			database.insertEmotions(gotTheFeeling, need, date);
			database.close();
			
			Random rand = new Random();
			int min = 0, max = 5;
			int randomNum = rand.nextInt(max - min + 1);
			
			Toast toast = Toast.makeText(Needs.this, quotes[randomNum] + gotTheFeeling + " and you need " +
					 need ,
					Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			
			
			Intent toSqlView = new Intent(Needs.this, SqlView.class);
			startActivity(toSqlView);

		}

	};

}
