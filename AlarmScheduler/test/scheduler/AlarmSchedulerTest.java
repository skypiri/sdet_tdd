package scheduler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static scheduler.AlarmScheduler.*;

import org.junit.Before;
import org.junit.Test;

import device.AlarmAlert;
import device.TimeService;



public class AlarmSchedulerTest {
		
	private AlarmAlert spy;
	private AlarmScheduler scheduler;
	private TimeService fakeTimeService;
	
	@Before
	public void setUp() throws Exception {
		spy = mock(AlarmAlert.class);//new AlarmAlertSpy();
		fakeTimeService = mock(TimeService.class);//new FakeTimeService();
		scheduler = new AlarmScheduler(spy, fakeTimeService);

	}

	@Test
	public void no_schedule_no_alarm() throws Exception{
		// 알람이 추가되지 않은 상태에서는
		
		// IH가 scheduler의 wake-up()을 호출해도
		scheduler.wakeup();
		
		// AlarmAlert의 startAlarm()이 호출되지 않는다.
		thenNoAlarms();
	}


	
	@Test
	public void test_startAlarm() throws Exception {
		// 08:00시 알람이 추가된 상태에서
		givenScheduleAs(8 * 60);
		
		// 08:00시가 되면 (현재 시간이 08:00이면)
		whenTheTimsIs(8 * 60);
		
		// 알람이 울린다.
		thenItAlarms();
	}


	@Test
	public void test_startAlarm2() throws Exception {

		givenScheduleAs(9 * 60);
		whenTheTimsIs(9 * 60);
		thenItAlarms();
	}
	
	@Test
	public void test_startAlarm3() throws Exception {
		// 특정 요일에 반응하는 타이머
		givenScheduleAs(MONDAY, 9 * 60);
		whenTheTimsIs(MONDAY, 9 * 60);
		thenItAlarms();
	}
	
	@Test
	public void test_startAlarm_DayValid_TimeInValid() throws Exception {
		// 특정 요일에 반응하는 타이머
		givenScheduleAs(MONDAY, 9 * 60);
		whenTheTimsIs(MONDAY,8 * 60);
		thenNoAlarms();
	}
	
	@Test
	public void test_startAlarm_DayInValid_TimeValid() throws Exception {
		// 특정 요일에 반응하는 타이머
		givenScheduleAs(MONDAY, 9 * 60);
		whenTheTimsIs(WENDSDAY, 9 * 60);
		thenNoAlarms();
	}
	
	@Test
	public void test_startAlarm_weekdays_TimeValid() throws Exception {
		// 주중 요일에 반응하는 타이머
		givenScheduleAs(WEEKDAYS, 9 * 60);
		whenTheTimsIs(WENDSDAY, 9 * 60);
		thenItAlarms();
	}
	
	@Test
	public void test_startAlarm_weekdaysInvalid_TimeValid() throws Exception {
		// 주중 요일에 반응하는 타이머
		givenScheduleAs(WEEKDAYS, 9 * 60);
		whenTheTimsIs(SUNDAY, 9 * 60);
		thenNoAlarms();
	}
	
	@Test
	public void test_startAlarm_weekends_TimeValid() throws Exception {
		// 주중 요일에 반응하는 타이머
		givenScheduleAs(WEEKENDS, 9 * 60);
		whenTheTimsIs(SUNDAY, 9 * 60);
		thenItAlarms();
	}
	
	@Test
	public void test_startAlarm_weekendsInvalid_TimeValid() throws Exception {
		// 주중 요일에 반응하는 타이머
		givenScheduleAs(WEEKENDS, 9 * 60);
		whenTheTimsIs(MONDAY, 9 * 60);
		thenNoAlarms();
	}
	
	@Test
	public void test_startAlarm_weekends_TimeInValid() throws Exception {
		// 주중 요일에 반응하는 타이머
		givenScheduleAs(WEEKENDS, 9 * 60);
		whenTheTimsIs(SUNDAY, 8 * 60);
		thenNoAlarms();
	}
	


	private void givenScheduleAs(int minutes) {
		scheduler.addSchedule(EVERYDAY, minutes);
	}
	
	private void givenScheduleAs(int days, int minutes) {
		scheduler.addSchedule(days, minutes);
	}
	
	private void whenTheTimsIs(int minutes) {
//		fakeTimeService.setFakeTime(minutes);
		when(fakeTimeService.getTime()).thenReturn(minutes);
		when(fakeTimeService.getDay()).thenReturn(EVERYDAY);
		scheduler.wakeup();
	}
	
	private void whenTheTimsIs(int days, int minutes) {
//		fakeTimeService.setFakeTime(minutes);
		when(fakeTimeService.getTime()).thenReturn(minutes);
		when(fakeTimeService.getDay()).thenReturn(days);
		scheduler.wakeup();
	}

	
	private void thenItAlarms() {
//		assertTrue(spy.wasAlerted());
		
		verify(spy).startAlarm();
	}
	
	private void thenNoAlarms() {
		//assertFalse(spy.wasAlerted());
		verify(spy, never()).startAlarm();
	}

}
