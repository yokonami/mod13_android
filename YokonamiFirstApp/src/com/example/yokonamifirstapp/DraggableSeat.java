package com.example.yokonamifirstapp;
import android.widget.ImageView;
import android.app.Activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
public class DraggableSeat extends ImageView implements SeatColleague{
	private Activity mediator;
	private Tramp tramp;
	public DraggableSeat(Context c, Tramp t){
		super(c);
		changeTramp(t);		
	}
	@Override
	public void setMediator(Activity mediator) {
		this.mediator = mediator;
	}
	@Override
	public void changeTramp(Tramp tramp) {
		this.tramp = tramp;
		this.setImageResource(tramp.getImage());
	}
	@Override
	public Tramp getTramp(){
		return this.tramp;
	}
	public void reveal(){
		int i = 2;
		String imageName = "tramp" + i;
		int resID = getResources().getIdentifier(imageName, "drawable", "com.example.yokonamifirstapp");
		setImageResource(resID);
	}
}
