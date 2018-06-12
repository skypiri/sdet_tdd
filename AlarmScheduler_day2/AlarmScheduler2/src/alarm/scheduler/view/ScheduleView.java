package alarm.scheduler.view;

import os.service.Day;

public interface ScheduleView {

	Day getDay();

	int getMinutes();
	
	void displayErrorMessage(String str);

}
