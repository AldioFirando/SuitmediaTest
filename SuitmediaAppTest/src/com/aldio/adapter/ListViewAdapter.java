package com.aldio.adapter;

import java.util.ArrayList;

import com.aldio.models.EventItem;
import com.aldio.suitmediaapptest.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	
	Context context;
	LayoutInflater inflater;
	ArrayList<EventItem> listItem;
	EventItem ev;
	
	public ListViewAdapter(Context context, ArrayList<EventItem> listItem) {
		this.context = context;
		this.listItem = listItem;
	}

	@Override
	public int getCount() {
		return listItem.size();
	}

	@Override
	public Object getItem(int position) {
		return listItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		TextView nama;
		TextView tanggal;
		ImageView gambar;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
		View itemView = inflater.inflate(R.layout.list_item, parent, false);
		
		ev = listItem.get(position);
		
		nama = (TextView) itemView.findViewById(R.id.nama);
		tanggal = (TextView) itemView.findViewById(R.id.tanggal);
		gambar = (ImageView) itemView.findViewById(R.id.img);

		
		nama.setText(ev.getNama());
		tanggal.setText(ev.getTanggal());
		
		gambar.setImageResource(ev.getImage());

		return itemView;
	}
}
