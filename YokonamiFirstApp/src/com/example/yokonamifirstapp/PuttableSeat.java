package com.example.yokonamifirstapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.ImageView;
import android.app.Activity;
public class PuttableSeat extends SeatColleague{
	private State state;
	public PuttableSeat(Context c, Tramp t){
		super(c);
		setTopTramp(t);
	}
	public PuttableSeat(Context c){
		super(c);
	}
	@Override
	public Tramp getTramp(){
		return this.tramp;
	}
	public void changeState(State state){
		this.state = state;
		switch (state){
		case NEUTRAL:
			this.clearColorFilter();
			break;
		case PUTTABLE:
			this.setColorFilter(Color.BLUE,PorterDuff.Mode.DARKEN);
			break;
		case UNDER_DRAGGABLE:
			this.setColorFilter(Color.GREEN,PorterDuff.Mode.DARKEN);
		}
	}
	public void informDropped(DraggableSeat d){
		if(state.equals(State.UNDER_DRAGGABLE)){
			this.setTopTramp(d.getTopTramp());
			d.reveal();
		}
	}
	public State getState(){
		return this.state;
	}
}
