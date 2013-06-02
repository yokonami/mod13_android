package com.example.yokonamifirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.content.Intent;
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void moveToActivitySendText(View view){
		Intent intent = new Intent(this, SendTextActivity.class);
		startActivity(intent);
	}
	public void moveToActivityHelloWorld(View view){
		Intent intent = new Intent(this, HelloWorldActivity.class);
		startActivity(intent);
	}
	public void moveToActivityPuyoScore(View view){
		Intent intent = new Intent(this, PuyoScoreActivity.class);
		startActivity(intent);
	}
	public void moveToActivityDragDrop(View view){
		Intent intent = new Intent(this, DragDropActivity.class);
		startActivity(intent);
	}
	public void finishActivity(){
		System.out.println("---finish---");
		finish();
		System.exit(0);
	}
}
