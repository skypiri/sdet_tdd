package scheduler;

import device.AlarmAlert;
import device.TimeService;

public class AlarmScheduler {

	private AlarmAlert alarmAlert;
	private TimeService timeService;
	
	private int alarmTime = -1;
	private int alarmDays = -1;
	
	public static final int MONDAY = 1;
	public static final int TUESDAY = 2;
	public static final int WENDSDAY = 3;
	public static final int THURSDAY = 4;
	public static final int FRIDAY = 5;
	public static final int SATURDAY = 6;
	public static final int SUNDAY = 7;
	
	public static final int EVERYDAY = 10;
	public static final int WEEKDAYS = 20;
	public static final int WEEKENDS = 30;

	public AlarmScheduler(AlarmAlert alarmAlert, TimeService timeService) {
		this.alarmAlert = alarmAlert;
		this.timeService = timeService;
	}

	public void wakeup() {
		int currentDay = timeService.getDay();
		int currentTime = timeService.getTime();
		
		switch(this.alarmDays) {
			
		case WEEKDAYS :
			if(currentDay != SATURDAY && currentDay != SUNDAY) {
				if(currentTime == this.alarmTime) {
					alarmAlert.startAlarm();
				}
			}
			break;
		
		case WEEKENDS :

			if(currentDay == SATURDAY || currentDay == SUNDAY) {
				if(currentTime == this.alarmTime) {
					alarmAlert.startAlarm();
				}
			}
			break;

		case EVERYDAY :
			if(currentTime == this.alarmTime) {
				alarmAlert.startAlarm();
			}
			break;
			
		case MONDAY : 
		case TUESDAY :
		case WENDSDAY :
		case THURSDAY :
		case FRIDAY :
		case SATURDAY :
		case SUNDAY :
			if(currentDay == this.alarmDays && currentTime == this.alarmTime) {
				alarmAlert.startAlarm();
			}
			break;
			

		default : break;
		}
	}

	public void addSchedule(int days, int times) {
		System.out.println("Days : " + days + ", time : " + times);
		this.alarmDays = days;
		this.alarmTime = times;
	}
}
