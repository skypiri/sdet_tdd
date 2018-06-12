package alarm.presenter;

import alarm.scheduler.AlarmScheduler;
import alarm.scheduler.view.ScheduleView;
import os.service.Day;

public class Presenter {
	private AlarmScheduler scheduler;
	private ScheduleView view;
	
	
	public Presenter(AlarmScheduler scheduler, ScheduleView view) {
		// TODO Auto-generated constructor stub
		this.scheduler = scheduler;
		this.view = view;
	}


	public void registerSchedule() {
		// TODO Auto-generated method stub
		
		// ....
		
		
		// Scheduler에게 정보를 전달한다.
		int userInputMinute = view.getMinutes();
		Day userInputDays = view.getDay();
		System.out.println("InputMinute : " + userInputMinute);
		System.out.println("InputDays : " + userInputDays);
		
		if(userInputMinute > 0 || userInputDays != null) {
			boolean result = scheduler.addSchedule(userInputDays, userInputMinute);
			if(!result) {
				view.displayErrorMessage("Full Schedule");
				System.out.println("Full Schedule");
			}
		} else {
			view.displayErrorMessage("Invalid Input");
		}
	}

}
