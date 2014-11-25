package com.aldio.suitmediaapptest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends Activity implements OnClickListener{

	private TextView tv;
	private Button btnEvent, btnGuest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		tv = (TextView)findViewById(R.id.textViewNama);
		btnEvent = (Button)findViewById(R.id.btnPilihEvent);
		btnGuest = (Button)findViewById(R.id.btnPilihGuest);

		btnEvent.setOnClickListener(this);
		btnGuest.setOnClickListener(this);

		Intent data = getIntent(); 
		Bundle paket = data.getExtras();
		String nama = paket.getString("name");
		tv.setText(nama);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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
		case R.id.btnPilihGuest:
			Intent iGuest = new Intent(Profile.this, Guest.class);
			startActivityForResult(iGuest, 2);
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
				String result=data.getStringExtra("result");
				btnGuest.setText(result);
			}
		}
	}

}
