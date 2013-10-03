package com.monster.yapp;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		System.gc();
		GCTest1.main();
		
		Garbage.main(null);
		
		Map<Object, Object> map = new TreeMap<Object, Object>();
		map.put("1", 1);
		map.put(1, "1");
		System.out.println(map.size());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
