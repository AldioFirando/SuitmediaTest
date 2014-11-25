package com.aldio.suitmediaapptest;

import com.aldio.adapter.ListViewAdapter;
import com.aldio.models.EventDummy;
import com.aldio.models.EventItem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Event extends Activity {

	ListView list;
	int i;
	ListViewAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		
		EventDummy ed = new EventDummy();

		list = (ListView) findViewById(R.id.listView);

		adapter = new ListViewAdapter(this, ed.getEvent());
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				EventItem ev = (EventItem) adapter.getItem(position);
				Intent returnIntent = new Intent();
				returnIntent.putExtra("result", ev.getNama());
				setResult(RESULT_OK,returnIntent);
				finish();
			}

		});
	}
}
