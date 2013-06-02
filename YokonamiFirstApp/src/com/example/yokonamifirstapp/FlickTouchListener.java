package com.example.yokonamifirstapp;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.*;

public class FlickTouchListener implements OnTouchListener {
	private float lastTouchX;
	private float currentX;
	private float lastTouchY;
	private float currentY;
	private int threshould = 75;
    @Override
    public boolean onTouch(View view, MotionEvent event) {
    	Button button = (Button)view;
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            lastTouchX = event.getX();
            lastTouchY = event.getY();
            break;

        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_CANCEL:
            currentX = event.getX();
            currentY = event.getY();
            if (lastTouchX + threshould < currentX) {
               	button.setText("◯");
            }
            if (lastTouchY > currentY + threshould) {
             	button.setText("×");
            }
            if (lastTouchX > currentX + threshould) {
             	button.setText("△");
            }
            if (lastTouchY + threshould < currentY) {
             	button.setText("☆");
            }
            if (Math.abs(lastTouchY-currentY)<threshould && Math.abs(lastTouchX-currentX) < threshould ){
            	button.setText(" ");
            }
            break;
        }
        return true;
    }
}
