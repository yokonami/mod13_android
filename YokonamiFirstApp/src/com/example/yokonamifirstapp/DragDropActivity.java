package com.example.yokonamifirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View;
import android.content.ClipData;
import android.content.ClipDescription;
import java.util.*;

public class DragDropActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drag_drop);
		// Show the Up button in the action bar.
		setupActionBar();

		// get layout
		LinearLayout layout = (LinearLayout)findViewById(R.id.layout_activity_drag_drop);
		
		// create draggableSeat
		DraggableSeat dSeat = new DraggableSeat(this, new Tramp(1));
		//dSeat.setMediator(mediator);
		dSeat.setMediator(this);
		dSeat.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				ClipData.Item item = new ClipData.Item(String.valueOf(v.getTag()));
				ClipData dragData = new ClipData(String.valueOf(v.getTag()), new String[] {ClipDescription.MIMETYPE_TEXT_PLAIN},item);
				dragData.addItem(item);
				View.DragShadowBuilder myShadow = new MyDragShadowBuilder(v);
				 v.startDrag(dragData,  // the data to be dragged
	                        myShadow,  // the drag shadow builder
	                        (Object)v,      // no need to use local data
	                        0          // flags (not currently used, set to 0)
	            );
				return true;
			}
		});
		layout.addView(dSeat);
		
		PuttableSeat pSeat = new PuttableSeat(this, new Tramp(2));
		//pSeat.setMediator(mediator);
		pSeat.setMediator(this);
		pSeat.setOnDragListener(new myDragEventListener());
		layout.addView(pSeat);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.drag_drop, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
