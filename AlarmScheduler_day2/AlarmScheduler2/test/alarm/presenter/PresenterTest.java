package alarm.presenter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alarm.scheduler.AlarmScheduler;
import alarm.scheduler.view.ScheduleView;
import os.service.Day;

import static org.mockito.Mockito.*;
import static os.service.Day.*;

public class PresenterTest {

	
	private Presenter presenter;
	private AlarmScheduler scheduler;
	private ScheduleView view;
	
	@Before
	public void setUp() throws Exception {
		
		scheduler = mock(AlarmScheduler.class);
		view = mock(ScheduleView.class);
		presenter = new Presenter(scheduler);
	}

	@Test
	public void test_add_schedule_when_user_click_register_button() {
		givenUserInput(MONDAY, 10*60);	// View

		whenUserClickButton();	// presenter action
		
		verify(scheduler).addSchedule(MONDAY, 10*60);	//

	}

	private void givenUserInput(Day day, int minute) {
		// TODO Auto-generated method stub
		// View는 getDat(), getMinutes()의 일을 담당해야 함.
		when(view.getDay()).thenReturn(day);
		when(view.getMinutes()).thenReturn(minute);
		
		
	}

	private void whenUserClickButton() {

		presenter.registerSchedule();	// 실제 로직을 작성해야 하는 부분
		
	}

}
