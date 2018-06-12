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
		presenter = new Presenter(scheduler, view);
	}



	private void givenUserInput(Day day, int minute) {
		// TODO Auto-generated method stub
		// View�� getDat(), getMinutes()�� ���� ����ؾ� ��.
		when(view.getDay()).thenReturn(day);
		when(view.getMinutes()).thenReturn(minute);
		
		
	}

	private void whenUserClickButton() {

		presenter.registerSchedule();	// ���� ������ �ۼ��ؾ� �ϴ� �κ�
		
	}
	
	@Test
	public void test_add_schedule_when_user_click_register_button() {
		givenUserInput(MONDAY, 10*60);	// View

		whenUserClickButton();	// presenter action
		
		verify(scheduler).addSchedule(MONDAY, 10*60);	//

	}
	
	@Test
	public void test_add_empty_schedule_when_user_click_register_button() {
		givenUserInput(null, -1);	// View���� �Է��� ���� ��� ó���� �̷��� �Ǿ�� �Ѵٰ� ��ӵǾ�� �ϳ�.

		whenUserClickButton();	// presenter action
		
		verify(scheduler, never()).addSchedule((Day)anyObject(), anyInt());	//
		verify(view).displayErrorMessage("Invalid Input");
		
		

	}

}
