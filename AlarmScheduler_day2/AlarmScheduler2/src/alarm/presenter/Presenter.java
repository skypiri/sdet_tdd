package alarm.presenter;

import alarm.scheduler.AlarmScheduler;
import static os.service.Day.*;

public class Presenter {
	private AlarmScheduler scheduler;
	
	
	public Presenter(AlarmScheduler scheduler) {
		// TODO Auto-generated constructor stub
		this.scheduler = scheduler;
	}


	public void registerSchedule() {
		// TODO Auto-generated method stub
		
		// ....
		
		
		// Scheduler���� ������ �����Ѵ�.
		scheduler.addSchedule(MONDAY, 10 * 60);
		
	}

}
