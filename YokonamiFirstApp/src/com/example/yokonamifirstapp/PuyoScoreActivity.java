package com.example.yokonamifirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;
import android.widget.*;
import android.view.*;
import android.widget.LinearLayout.LayoutParams;

public class PuyoScoreActivity extends Activity {
	public static final int NUMBER_OF_ROW = 14;
	public static final int NUMBER_OF_COLUMN = 6;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_puyo_score);
		// Show the Up button in the action bar.
		setupActionBar();
		
		/*Button[] view = new Button[3];
		view[0] = (Button)findViewById(R.id.button_test0);
		view[1] = (Button)findViewById(R.id.button_test1);
		view[2] = (Button)findViewById(R.id.button_test2);
		for(Button v : view){
			v.setOnTouchListener(new FlickTouchListener());
			v.setText("asdf");
		}*/
		
		Button[][] puyoSpace = new Button[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
		TableLayout table = (TableLayout)findViewById(R.id.table_layout_for_puyo_score_activity);
		TableRow row;
		
		for(int iRow=0; iRow<NUMBER_OF_ROW; iRow++){
			row = new TableRow(this);
			for(int iColumn=0; iColumn<NUMBER_OF_COLUMN; iColumn++){
				puyoSpace[iRow][iColumn] = new Button(this);
				puyoSpace[iRow][iColumn].setText(" ");
				if(iRow <=1){
					puyoSpace[iRow][iColumn].setText("-");
				}
				//puyoSpace[iRow][iColumn].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,1.0f));
				puyoSpace[iRow][iColumn].setOnTouchListener(new FlickTouchListener());
				
				row.addView(puyoSpace[iRow][iColumn]);
			}
			table.addView(row);
		}
		
		
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
		getMenuInflater().inflate(R.menu.puyo_score, menu);
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
