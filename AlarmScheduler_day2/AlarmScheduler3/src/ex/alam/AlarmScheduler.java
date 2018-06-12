package ex.alam;

import java.util.ArrayList;
import java.util.List;

import ex.alarm.driver.AlarmAlert;
import ex.os.service.TimeService;

public class AlarmScheduler {

	class Schedule {
		public Schedule(int dayOfWeek, int minuteOfDay) {
			day = dayOfWeek;
			minute = minuteOfDay;
		}
		int day;
		int minute;
	}
	
	private AlarmAlert alarmAlert;
	private TimeService timeService;
	private List<Schedule> savedSchedules = new ArrayList<>();
	
	
	public AlarmScheduler(AlarmAlert alarm, TimeService timeService) {
		alarmAlert = alarm;
		this.timeService = timeService;
	}

	public void wakeup() {
		int min = timeService.getCurrentMinute();
		int td = timeService.getCurrentDay();
		int locale = timeService.getTimeLocale();
		
		for (int i = 0; i < savedSchedules.size(); ++i) {
			int d = savedSchedules.get(i).day;
			
			if ( (d == -3) || (d == td) || 
					(d == -1 && checkWeekend(td, locale)) ||
                    (d == -2 && checkWeekday(td, locale)))
            {
				/* it's the right day */
				if (min == savedSchedules.get(i).minute)
				{
					alarmAlert.startAlarm();
				}
            }
		}
	}

	private boolean checkWeekend(int td, int locale) {

		if(locale == 1) {
			return (td == 6 || td == 7);
		} else if(locale == 2) {
			return (td == 5 || td == 6);
		} else {// default 
			return (td == 7 || td == 1);
		}
	}
	
	private boolean checkWeekday(int td, int locale) {

		if(locale == 1) {
			return (td >= 1 && td <= 5);
		} else if(locale == 2) {
			return (td >= 1 && td <= 7 && td != 5 && td != 6);
		} else {// default 
			return (td >= 2 && td <= 6);
		}
	}

	public void register(int dayOfWeek, int minuteOfDay) {
		Schedule newSchedule = new Schedule(dayOfWeek, minuteOfDay);
		savedSchedules.add(newSchedule);
	}

}
