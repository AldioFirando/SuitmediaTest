package com.aldio.suitmediaapptest;

import com.aldio.adapter.GridViewAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class Guest extends Activity {

	private GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guest);

		gridView = (GridView) findViewById(R.id.gridView1);

		GridViewAdapter adapter = new GridViewAdapter(this, R.layout.grid_item);
		
		gridView.setAdapter(adapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Intent returnIntent = new Intent();
				returnIntent.putExtra("result", position);
				setResult(RESULT_OK,returnIntent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.guest, menu);
		return true;
	}

}
