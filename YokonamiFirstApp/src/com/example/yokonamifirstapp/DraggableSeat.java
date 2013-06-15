package com.example.yokonamifirstapp;
import android.widget.ImageView;
import android.app.Activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
public class DraggableSeat extends SeatColleague {
	private String owner;
	public DraggableSeat(Context c, Tramp t, String owner){
		super(c);
		setTopTramp(t);
		this.owner = owner;
	}
	public String getOwner(){
		return owner;
	}
	public DraggableSeat(Context c, String owner){
		super(c);
		this.owner = owner;
	}
	@Override
	public Tramp getTramp(){
		return this.tramp;
	}
	public boolean reveal(){
		return this.mediator.offerServe((SeatColleague)this);
	}
}
