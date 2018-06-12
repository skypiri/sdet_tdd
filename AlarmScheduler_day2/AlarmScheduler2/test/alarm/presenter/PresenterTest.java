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
		// View는 getDat(), getMinutes()의 일을 담당해야 함.
		when(view.getDay()).thenReturn(day);
		when(view.getMinutes()).thenReturn(minute);
		
		
	}

	private void whenUserClickButton() {

		presenter.registerSchedule();	// 실제 로직을 작성해야 하는 부분
		
	}
	
	@Test
	public void test_add_schedule_when_user_click_register_button() {
		givenUserInput(MONDAY, 10*60);	// View

		whenUserClickButton();	// presenter action
		
		verify(scheduler).addSchedule(MONDAY, 10*60);	//

	}
	
	@Test
	public void test_add_empty_schedule_when_user_click_register_button() {
		givenUserInput(null, -1);	// View에게 입력이 없는 경우 처리가 이렇게 되어야 한다고 약속되어야 하네.

		whenUserClickButton();	// presenter action
		
		verify(scheduler, never()).addSchedule((Day)anyObject(), anyInt());	//
		verify(view).displayErrorMessage("Invalid Input");
		
	}
	
	@Test
	public void test_fullSchedule_when_user_input_4schedule() {
		
		when(scheduler.addSchedule((Day)anyObject(), anyInt())).thenReturn(true, true, true, false);
		
		givenUserInput(MONDAY, 10*60);	// View
		whenUserClickButton();	// presenter action
		
		givenUserInput(MONDAY, 11*60);	// View
		whenUserClickButton();	// presenter action
		
		givenUserInput(MONDAY, 12*60);	// View
		whenUserClickButton();	// presenter action
		
		givenUserInput(MONDAY, 13*60);	// View
		whenUserClickButton();	// presenter action
		
		verify(scheduler).addSchedule(MONDAY, 10*60);	//
		verify(scheduler).addSchedule(MONDAY, 11*60);	//
		verify(scheduler).addSchedule(MONDAY, 12*60);	//
		verify(view).displayErrorMessage("Full Schedule");
	}

}
