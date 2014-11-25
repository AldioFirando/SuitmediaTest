package com.aldio.suitmediaapptest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.aldio.controller.AppController;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class Guest extends Activity {

	private static String link_data = "http://dry-sierra-6832.herokuapp.com/api/people";
	private static String TAG = Guest.class.getSimpleName();

	private String[] guest = new String[]{};

	private GridView gridView;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guest);

		makeJsonArrayRequest();

		gridView = (GridView) findViewById(R.id.gridView1);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, guest);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent returnIntent = new Intent();
				returnIntent.putExtra("result", ((TextView) view).getText());
				setResult(RESULT_OK,returnIntent);
				finish();
			}
		});
	}

	private void bindingData(){
		adapter.notifyDataSetChanged();
		gridView.invalidateViews();     
		gridView.setAdapter(adapter);
	}

	private void makeJsonArrayRequest() {

		JsonArrayRequest req = new JsonArrayRequest(link_data,
				new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				Log.d(TAG, response.toString());
				try {
					for (int i = 0; i < response.length(); i++) {
						guest = new String [response.length()];
						JSONObject data = (JSONObject) response
								.get(i);

						String name = data.getString("name");

						guest[i] = name;
						Log.d(TAG, guest[i].toString());
					}
					bindingData();
				} catch (JSONException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(),
							"Error: " + e.getMessage(),
							Toast.LENGTH_LONG).show();
				}

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				Toast.makeText(getApplicationContext(),
						error.getMessage(), Toast.LENGTH_SHORT).show();
			}
		});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.guest, menu);
		return true;
	}

}
