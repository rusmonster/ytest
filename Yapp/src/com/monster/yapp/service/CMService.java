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
	public int onStartCommand(final Intent intent, int flags, int startId) {
		if (intent!=null) {
			final int tim = intent.getIntExtra(ThreeActivity.PARAM_TIM, 0);
			final PendingIntent pi = intent.getParcelableExtra(ThreeActivity.PARAM_PI);
			
			Runnable r;
			
			if (pi!=null)
				r = new Runnable() {
					
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
				};
			else
				r = new Runnable() {
				
				@Override
				public void run() {
					try {
						Intent i = new Intent(ThreeActivity.BC_ACTION);
						i.putExtra(ThreeActivity.PARAM_TASK, intent.getIntExtra(ThreeActivity.PARAM_TASK, 0));
						i.putExtra(ThreeActivity.PARAM_EVENT, ThreeActivity.EVENT_START);
						sendBroadcast(i);
						
						Thread.sleep(tim);
						
						i = new Intent(ThreeActivity.BC_ACTION);
						i.putExtra(ThreeActivity.PARAM_TASK, intent.getIntExtra(ThreeActivity.PARAM_TASK, 0));
						i.putExtra(ThreeActivity.PARAM_EVENT, ThreeActivity.EVENT_STOP);
						i.putExtra(ThreeActivity.PARAM_RESULT, "OK");
						sendBroadcast(i);
						
						Log.i("!!!", "Task done: "+tim);
					} catch (Exception e) {
						Log.e("!!!", "Error in mEs.execute: "+e);
					}
						
				}
			};				
 
			mEs.execute(r);
		}

		return START_STICKY;
	}

	
}
