package com.example.feeling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class FeelingsMain extends Activity {

	
	private String[] quotes = {"So,you are feeling ","I'm hearing ", " I'm hearing that you're feeling ", "You're feeling ", "Ok, you're feeling ",
			"I see that you're feeling "
			};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initList();

		ListView lv = (ListView) findViewById(R.id.pageLayout);
		// design the listview with the adapter

		SimpleAdapter simpleAdpt = new SimpleAdapter(this, feelingsList,
				android.R.layout.simple_list_item_1,
				new String[] { "feeling" }, new int[] { android.R.id.text1 });

		lv.setAdapter(simpleAdpt);

		lv.setOnItemClickListener(viewNeeds);
		// raise a toast
		Toast toast = Toast.makeText(FeelingsMain.this, "How are you feeling?",
				Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();

	}

	List<Map<String, String>> feelingsList = new ArrayList<Map<String, String>>();

	private void initList() {
		// populate the feelingsList
		feelingsList.add(createFeeling("feeling", "wonderful"));
		feelingsList.add(createFeeling("feeling", "content"));
		feelingsList.add(createFeeling("feeling", "joyful"));
		feelingsList.add(createFeeling("feeling", "tired"));
		feelingsList.add(createFeeling("feeling", "mellow"));
		feelingsList.add(createFeeling("feeling", "sad"));
		feelingsList.add(createFeeling("feeling", "amazing"));
		feelingsList.add(createFeeling("feeling", "compassionate"));
		feelingsList.add(createFeeling("feeling", "friendly"));
		feelingsList.add(createFeeling("feeling", "loving"));
		feelingsList.add(createFeeling("feeling", "open-hearted"));
		
		//main negative
		
		feelingsList.add(createFeeling("feeling", "afraid"));
		feelingsList.add(createFeeling("feeling", "annoyed"));
		feelingsList.add(createFeeling("feeling", "discontent"));
		feelingsList.add(createFeeling("feeling", "fatigued"));
		feelingsList.add(createFeeling("feeling", "tense"));
		feelingsList.add(createFeeling("feeling", "anxious"));
		feelingsList.add(createFeeling("feeling", "pain"));
		feelingsList.add(createFeeling("feeling", "vulnerable"));
		
		// more positive
		feelingsList.add(createFeeling("feeling", "engaged"));
		feelingsList.add(createFeeling("feeling", "curious"));
		feelingsList.add(createFeeling("feeling", "alert"));
		feelingsList.add(createFeeling("feeling", "hopeful"));
		feelingsList.add(createFeeling("feeling", "encouraged"));
		feelingsList.add(createFeeling("feeling", "confident"));
		feelingsList.add(createFeeling("feeling", "open"));
		feelingsList.add(createFeeling("feeling", "proud"));
		feelingsList.add(createFeeling("feeling", "safe"));
		
		//more neg
		feelingsList.add(createFeeling("feeling", "terrified"));
		feelingsList.add(createFeeling("feeling", "suspicious"));
		feelingsList.add(createFeeling("feeling", "weary"));
		feelingsList.add(createFeeling("feeling", "aggravated"));
		feelingsList.add(createFeeling("feeling", "frustrated"));
		feelingsList.add(createFeeling("feeling", "enraged"));
		feelingsList.add(createFeeling("feeling", "outraged"));
		feelingsList.add(createFeeling("feeling", "resentful"));
		feelingsList.add(createFeeling("feeling", "jealous"));
		
		
		//more positive
		
		feelingsList.add(createFeeling("feeling", "aroused"));
		feelingsList.add(createFeeling("feeling", "giddy"));
		feelingsList.add(createFeeling("feeling", "passionate"));
		feelingsList.add(createFeeling("feeling", "energetic"));
		feelingsList.add(createFeeling("feeling", "enthusiastic"));
		feelingsList.add(createFeeling("feeling", "surprised"));
		
		// more negative
		feelingsList.add(createFeeling("feeling", "hate"));
		feelingsList.add(createFeeling("feeling", "contempt"));
		feelingsList.add(createFeeling("feeling", "dislike"));
		feelingsList.add(createFeeling("feeling", "ambivalent"));
		feelingsList.add(createFeeling("feeling", "lost"));
		feelingsList.add(createFeeling("feeling", "hesitant"));

	}

	private HashMap<String, String> createFeeling(String key, String name) {
		HashMap<String, String> feeling = new HashMap<String, String>();
		feeling.put(key, name);

		return feeling;

	}

	OnItemClickListener viewNeeds = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			// create variable for database input
			String feeling = feelingsList.get(arg2).get("feeling").toString();
			
			Random rand = new Random();
			int min = 0, max = 5;
			int randomNum = rand.nextInt(max - min + 1);
			
			Toast toast = Toast.makeText(FeelingsMain.this, quotes[randomNum]+ feeling ,
					Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();

			Intent gotoNeeds = new Intent(FeelingsMain.this, Needs.class);
			gotoNeeds.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); //testing
			gotoNeeds.putExtra("aFeeling", feeling);
			startActivity(gotoNeeds);
		}

	};

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
		// TODO Auto-generated method stub

		switch (item.getItemId()) {

		case R.id.history:
			Intent i = new Intent(FeelingsMain.this, SqlView.class);
			startActivity(i);
			break;

		

		}
		return false;

	}

}
