package com.syh;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class StockQuoteService extends Service {
	private NotificationManager notificationMgr;

	public class StockQuoteServiceImpl extends IStockQuoteService.Stub {
		@Override
		public double getQuote(String ticker) throws RemoteException {
			return 20.0;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		notificationMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		displayNotificationMessage("onCreate() called in StockQuoteService");
	}

	@Override
	public void onDestroy() {
		displayNotificationMessage("onDestroy() called in StockQuoteService");
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		displayNotificationMessage("onBind() called in StockQuoteService");
		return new StockQuoteServiceImpl();
	}

	private void displayNotificationMessage(String message) {
		Notification notification = new Notification(R.drawable.note, message,
				System.currentTimeMillis());
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);
		notification.setLatestEventInfo(this, "StockQuoteService", message,
				contentIntent);
		notificationMgr.notify(R.string.app_notification_id, notification);
	}
}