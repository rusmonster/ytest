package com.monster.yapp.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.monster.yapp.ThreeActivity;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class CMService extends Service {

	private ExecutorService mEs;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void onCreate() {
		super.onCreate();
		mEs = Executors.newFixedThreadPool(3);
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent!=null) {
			final int tim = intent.getIntExtra(ThreeActivity.PARAM_TIM, 0);
			final PendingIntent pi = intent.getParcelableExtra(ThreeActivity.PARAM_PI);
			
			mEs.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						pi.send(ThreeActivity.EVENT_START);
						
						Thread.sleep(tim);
						
						Intent i = new Intent().putExtra(ThreeActivity.PARAM_RESULT, "OK");
						
						pi.send(CMService.this, ThreeActivity.EVENT_STOP, i);
						
						Log.i("!!!", "Task done: "+tim);
					} catch (Exception e) {
						Log.e("!!!", "Error in mEs.execute: "+e);
					}
						
				}
			});
		}
		return START_STICKY;
	}

	
}
