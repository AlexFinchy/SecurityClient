package org.alexfinch;

import java.util.Timer;
import java.util.TimerTask;

public class AlarmMonitor extends Timer {
	
	public AlarmMonitor() {
		
		schedule(new TimerTask() {
			
			@Override
			public void run() {
				if(Client.AlarmTriggered) {
					if(!Client.topBar.AlarmStarted) {
						Client.topBar.StartAlarm();
					}

					
				} else {
					if(Client.topBar.AlarmStarted) {
						Client.topBar.StopAlarm();
					}
				}
				
			}
		}, 1000,1000);
		
	}

}
