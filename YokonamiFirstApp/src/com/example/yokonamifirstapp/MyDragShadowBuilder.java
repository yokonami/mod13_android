package com.example.yokonamifirstapp;
import android.view.View.DragShadowBuilder;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Canvas;

public class MyDragShadowBuilder extends DragShadowBuilder {
	private static Drawable shadow;
	
	public MyDragShadowBuilder(View v){
		super(v);
		shadow = new ColorDrawable(Color.LTGRAY);
		
	}
	
	@Override
    public void onProvideShadowMetrics (Point size, Point touch){
		int width;
		int height;
		width = getView().getWidth();
		height = getView().getHeight();
		shadow.setBounds(0,0,width,height);
		size.set(width, height);
		touch.set(width/2, height/2);
	}
	
    @Override
    public void onDrawShadow(Canvas canvas) {

        // Draws the ColorDrawable in the Canvas passed in from the system.
        shadow.draw(canvas);
    }

}
