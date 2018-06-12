package alarm.scheduler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static os.service.Day.EVERYDAY;
import static os.service.Day.MONDAY;
import static os.service.Day.SUNDAY;
import static os.service.Day.TUESDAY;
import static os.service.Day.WEEKEND;

import org.junit.Before;
import org.junit.Test;

import alarm.driver.AlarmAlert;
import os.service.Day;
import os.service.TimeService;


public class AlarmSchedulerTest {

	static final int theTime = 10 * 60;
	
	private AlarmAlert alarmSpy;
	private TimeService fakeTimeService;
	private AlarmScheduler scheduler;
	
	@Before
	public void setUp() {
		alarmSpy = mock(AlarmAlert.class);
		fakeTimeService = mock(TimeService.class);
		scheduler = new AlarmScheduler(alarmSpy, fakeTimeService);
	}

	private void givenThatScheduleIsAddedAs(Day day, int minute) {
		scheduler.addSchedule(day, minute);
	}
	
	private void whenItBecomesTheTime(Day day, int minute) {
		when(fakeTimeService.getCurrentMinute()).thenReturn(minute);
		when(fakeTimeService.getCurrentDay()).thenReturn(day);
		
		scheduler.wakeup();
	}
	
	private void thenItAlarms() {
		verify(alarmSpy).startAlarm();
	}
	
	private void thenItDoesNotAlarms() {
		verify(alarmSpy, never()).startAlarm();
	}
	
	@Test
	public void no_alarm_no_schedule() throws Exception {
		// given no schedules
		
		// when Interrupt Handler가 분마다 wakeup()을 호출해 주어도
		scheduler.wakeup();
		
		// then no alarm
		thenItDoesNotAlarms();
	}
	
	@Test
	public void test_does_alarm_if_scheduled_and_its_time() throws Exception {
		givenThatScheduleIsAddedAs(EVERYDAY, 10 * 60);  // 매일 10시 알람 설정된 상태에서
		
		whenItBecomesTheTime(MONDAY, 10 * 60);  // 월요일 10시가 되면
		
		thenItAlarms();  // 알람이 울려야 한다
	}
	
	@Test
	public void test_does_NOT_alarm_if_scheduled_but_its_not_the_time() throws Exception {
		givenThatScheduleIsAddedAs(EVERYDAY, 10 * 60);  // 매일 10시 알람 설정된 상태에서
		
		whenItBecomesTheTime(MONDAY, 9 * 60);  // 월요일 9시가 되면
		
		thenItDoesNotAlarms();  // 알람이 아직 울리지 않아야 한다
	}
	
	@Test
	public void test_does_NOT_alarm_if_scheduled_and_its_the_time_but_NOT_the_day() throws Exception {
		int theTime = 10 * 60;
		givenThatScheduleIsAddedAs(MONDAY, theTime);
		
		whenItBecomesTheTime(TUESDAY, theTime);
		
		thenItDoesNotAlarms();
	}
	
	@Test
	public void test_does_alarm_if_scheduled_and_its_the_time_and_the_day() throws Exception {
		givenThatScheduleIsAddedAs(MONDAY, theTime);
		
		whenItBecomesTheTime(MONDAY, theTime);
		
		thenItAlarms();
	}
	
	@Test
	public void test_does_alarm_if_given_schedule_is_WEEKEND_and_its_SUNDAY() throws Exception {
		givenThatScheduleIsAddedAs(WEEKEND, theTime);
		
		whenItBecomesTheTime(SUNDAY, theTime);
		
		thenItAlarms();
	}
	
	@Test
	public void test_support_LED_alarm() throws Exception {
//		givenThatScheduleIsAddedAs(WEEKEND, theTime);
		AlarmAlert LED = mock(AlarmAlert.class);
		givenThatScheduleIsAddedAs(LED, EVERYDAY, theTime);
		whenItBecomesTheTime(SUNDAY, theTime);
		
//		thenItAlarms();
		verify(LED).startAlarm();
	}

	private void givenThatScheduleIsAddedAs(AlarmAlert device, Day day, int minute) {
		// TODO Auto-generated method stub
		scheduler.addSchedule(device, day, minute);
		
	}
	
}
