package alarm.scheduler;

import alarm.driver.AlarmAlert;
import os.service.Day;
import os.service.TimeService;

import static os.service.Day.*;

public class AlarmScheduler {
	private static final int INVALID = -1;

	class Schedule {
		Day day;
		int minute;
		
		public Schedule(Day day, int minute) {
			this.day = day;
			this.minute = minute;
		}
	}
	
	private AlarmAlert alarm;
	private TimeService timeService;
	
	private Schedule savedSchedule = new Schedule(null, INVALID);


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
				alarm.startAlarm();
	}

	public void addSchedule(Day day, int minute) {
		this.savedSchedule = new Schedule(day, minute);
	}

}
