package com.example.yokonamifirstapp;
import android.content.Context;
interface Mediator {
	public abstract void createColleagues(Context context);
	public abstract void startGame();
	public abstract boolean offerServe(SeatColleague s);
	public boolean canPut(int strategy, Tramp willPut);
}
