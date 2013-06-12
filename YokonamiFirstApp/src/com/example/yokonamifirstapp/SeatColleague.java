package com.example.yokonamifirstapp;
import android.app.Activity;
import android.widget.ImageView;
import android.content.Context;
import java.util.*;
public abstract class SeatColleague extends ImageView {
	protected Mediator mediator;
	protected Tramp tramp;
	protected ArrayList<Tramp> trampList;
	public SeatColleague(Context c){
		super(c);
		trampList = new ArrayList<Tramp>();
	}
	public Tramp getTopTramp(){
		int size = trampList.size();
		if(size>0){
			return trampList.get(size-1);
		}else{
			return null;
		}
	}
	public boolean removeTopTramp(){		
		int size = trampList.size();
		if(size>0){
			trampList.remove(size-1);
			this.updateImage();
			return true;
		}else{
			return false;
		}
	}
	public void setTrampList(ArrayList<Tramp> list){
		this.trampList.addAll(list);
		this.updateImage();
	}
	public void setTopTramp(Tramp tramp){
		trampList.add(tramp);
		this.updateImage();
	}
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}
	public boolean updateImage(){
		Tramp t = this.getTopTramp();
		if(t != null){
			this.setImageResource(this.getTopTramp().getImage());
			return true;
		}else{
			int resId = getResources().getIdentifier("z01", "drawable", "com.example.yokonamifirstapp");
			this.setImageResource(resId);
			return false;
		}
	}
	public void changeTramp(Tramp tramp) {
		this.tramp = tramp;
		this.setImageResource(tramp.getImage());
	}
	public abstract Tramp getTramp();
}
