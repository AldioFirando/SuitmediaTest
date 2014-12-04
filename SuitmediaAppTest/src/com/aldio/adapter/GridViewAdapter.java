package com.aldio.adapter;

import com.aldio.suitmediaapptest.R;
import com.aldio.util.TempGuestItem;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {

	private Context mContext;
	int layoutResourceId;
	private TextView name, date;

	public GridViewAdapter(Context c, int layoutResourceId) {
		this.mContext = c;
		this.layoutResourceId = layoutResourceId;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return TempGuestItem.listGuest.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return TempGuestItem.listGuest.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			name = (TextView) row.findViewById(R.id.itemName);
			date = (TextView) row.findViewById(R.id.itemDate);
		}
		
		row.setBackgroundColor(Color.parseColor(color[position]));
		name.setText(TempGuestItem.listGuest.get(position).getName().toString());
		date.setText(TempGuestItem.listGuest.get(position).getBirthdate().toString());
		return row;
	}
	
	public String[] color = {"#FF0000","#0000FF","#00FF00","#FFFF00","#00FFFF","#FFA8A8"};

}
