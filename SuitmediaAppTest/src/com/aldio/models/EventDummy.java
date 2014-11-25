package com.aldio.models;

import java.util.ArrayList;

import com.aldio.suitmediaapptest.R;

public class EventDummy {

	public ArrayList<EventItem> getEvent(){
		ArrayList<EventItem> ListDummy = new ArrayList<EventItem>();
		
		EventItem ev1,ev2,ev3,ev4;
		ev1 = new EventItem();
		ev1.setNama("Aldio");
		ev1.setTanggal("21-05-92");
		ev1.setImage(R.drawable.ic_launcher);
		
		ev2 = new EventItem();
		ev2.setNama("Mona");
		ev2.setTanggal("24-05-92");
		ev2.setImage(R.drawable.ic_launcher);
		
		ev3 = new EventItem();
		ev3.setNama("Nadia");
		ev3.setTanggal("20-09-93");
		ev3.setImage(R.drawable.ic_launcher);
		
		ev4 = new EventItem();
		ev4.setNama("Putri");
		ev4.setTanggal("15-02-93");
		ev4.setImage(R.drawable.ic_launcher);
		
		ListDummy.add(ev1);
		ListDummy.add(ev2);
		ListDummy.add(ev3);
		ListDummy.add(ev4);
		
		return ListDummy;
	}
}
