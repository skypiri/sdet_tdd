package alarm.scheduler;

import alarm.driver.AlarmAlert;
import os.service.Day;
import os.service.TimeService;
import os.service.TimerHandler;

import static os.service.Day.*;

public class AlarmScheduler implements TimerHandler{
	private static final int INVALID = -1;

	class Schedule {
		Day day;
		int minute;
		AlarmAlert deviceType;
		
		public Schedule(AlarmAlert device, Day day, int minute) {
			this.day = day;
			this.minute = minute;
			this.deviceType = device;
		}

	}
	
	private AlarmAlert alarm;
	private TimeService timeService;
	
	private Schedule savedSchedule = new Schedule(alarm, null, INVALID);


	public AlarmScheduler(AlarmAlert alarm, TimeService timeService) {
		this.alarm = alarm;
		this.timeService = timeService;
	}

	public void wakeup() {
		int now = timeService.getCurrentMinute();
		Day today = timeService.getCurrentDay();
		
		if (savedSchedule.day == EVERYDAY || 
			(savedSchedule.day == WEEKEND && today == SUNDAY) ||
			savedSchedule.day == today)
			if (savedSchedule.minute == now)
				savedSchedule.deviceType.startAlarm();
	}

	public boolean addSchedule(Day day, int minute) {
//		this.savedSchedule = new Schedule(this.alarm, day, minute);
		return addSchedule(this.alarm, day, minute);
	}

	public boolean addSchedule(AlarmAlert device, Day day, int minute) {
		this.savedSchedule = new Schedule(device, day, minute);
		if(this.savedSchedule != null) {
			return false;
		} else {
			return true;
		}
	}

}
