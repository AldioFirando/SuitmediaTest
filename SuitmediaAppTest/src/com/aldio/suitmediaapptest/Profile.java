package com.aldio.suitmediaapptest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.aldio.controller.AppController;
import com.aldio.models.GuestItem;
import com.aldio.util.TempGuestItem;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends Activity implements OnClickListener{

	private static String link_data = "http://dry-sierra-6832.herokuapp.com/api/people";
	private static String TAG = Guest.class.getSimpleName();
	private TextView tv;
	private Button btnEvent, btnGuest1, btnGuest2;
	private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		tv = (TextView)findViewById(R.id.textViewNama);
		btnEvent = (Button)findViewById(R.id.btnPilihEvent);
		btnGuest1 = (Button)findViewById(R.id.btnPilihGuest1);
		btnGuest2 = (Button)findViewById(R.id.btnPilihGuest2);

		btnEvent.setOnClickListener(this);
		btnGuest1.setOnClickListener(this);
		btnGuest2.setOnClickListener(this);


		Intent data = getIntent(); 
		Bundle paket = data.getExtras();
		String nama = paket.getString("name");
		String[] sWord = nama.split(" ");
		if(sWord.length == 2){
			String s = sWord[1];
			String r = "";
			int length = s.length();
			for( int i = length - 1 ; i >= 0 ; i-- ) {
				r = r + s.charAt(i);
			}
			if(sWord[0].equals(r)){
				tv.setText("Mirror Words");
			}else{
				tv.setText(nama);
			}
		}else{
			tv.setText(nama);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Button btn = (Button)v;
		switch (btn.getId()) {
		case R.id.btnPilihEvent:
			Intent iEvent = new Intent(Profile.this, Event.class);
			startActivityForResult(iEvent, 1);
			break;
		case R.id.btnPilihGuest1:
			makeJsonArrayRequest(2);
			break;
		case R.id.btnPilihGuest2:
			makeJsonArrayRequest(3);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == 1) {
			if(resultCode == RESULT_OK){
				String result=data.getStringExtra("result");
				btnEvent.setText(result);
			}
		} else if(requestCode == 2) {
			if(resultCode == RESULT_OK){
				int result = data.getIntExtra("result", 0);
				String longdate = TempGuestItem.listGuest.get(result).getBirthdate().toString();
				int date = Integer.parseInt(longdate.substring(6, 7));
				if(date % 2 == 0){
					btnGuest1.setText("Genap");
				}else{
					btnGuest1.setText("Ganjil");
				}
			}
		}else if(requestCode == 3) {
			if(resultCode == RESULT_OK){
				int result = data.getIntExtra("result", 0);
				String longdate = TempGuestItem.listGuest.get(result).getBirthdate().toString();
				int date = Integer.parseInt(longdate.substring(6, 7));
				int faktor=0;
				for(int i = 1; i <= date; i++ ){
					if(date % i == 0){
						faktor = faktor+1;
					}
				}

				if(faktor == 2){
					btnGuest2.setText("Prima");
				}else{
					btnGuest2.setText("Bukan Prima");
				}
			}
		}
	}

	private void makeJsonArrayRequest(final int resultcode) {
		TempGuestItem.listGuest.clear();
		pDialog = new ProgressDialog(Profile.this);
		pDialog.setMessage("Please wait..");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.show();
		JsonArrayRequest req = new JsonArrayRequest(link_data,
				new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {

				Log.d("sebelum try", response.toString());
				try {

					for (int i = 0; i < response.length(); i++) {
						JSONObject data = (JSONObject) response
								.get(i);

						String name = data.getString("name");
						String birthdate = data.getString("birthdate");
						Log.d(TAG, name + " - " + birthdate);
						GuestItem gi = new GuestItem();
						gi.setName(name);
						gi.setBirthdate(birthdate);

						TempGuestItem.listGuest.add(gi);
						Log.d("Data list", TempGuestItem.listGuest.get(i).getName().toString());
					}
					Log.d("Start intent", "intent");
					Intent iGuest = new Intent(Profile.this, Guest.class);
					startActivityForResult(iGuest, resultcode);

				} catch (JSONException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(),
							"Error: " + e.getMessage(),
							Toast.LENGTH_LONG).show();
				}
				pDialog.dismiss();
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				Log.d("Di error response", error.getMessage());
				Toast.makeText(getApplicationContext(),
						error.getMessage(), Toast.LENGTH_SHORT).show();
				pDialog.dismiss();
			}
		});
		AppController.getInstance().addToRequestQueue(req);
	}
}
