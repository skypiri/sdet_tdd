package alarm.scheduler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import alarm.driver.AlarmAlert;
import os.service.Day;
import os.service.TimeService;

import static os.service.Day.*;


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
		
		// when Interrupt Handler�� �и��� wakeup()�� ȣ���� �־
		scheduler.wakeup();
		
		// then no alarm
		thenItDoesNotAlarms();
	}
	
	@Test
	public void test_does_alarm_if_scheduled_and_its_time() throws Exception {
		givenThatScheduleIsAddedAs(EVERYDAY, 10 * 60);  // ���� 10�� �˶� ������ ���¿���
		
		whenItBecomesTheTime(MONDAY, 10 * 60);  // ������ 10�ð� �Ǹ�
		
		thenItAlarms();  // �˶��� ����� �Ѵ�
	}
	
	@Test
	public void test_does_NOT_alarm_if_scheduled_but_its_not_the_time() throws Exception {
		givenThatScheduleIsAddedAs(EVERYDAY, 10 * 60);  // ���� 10�� �˶� ������ ���¿���
		
		whenItBecomesTheTime(MONDAY, 9 * 60);  // ������ 9�ð� �Ǹ�
		
		thenItDoesNotAlarms();  // �˶��� ���� �︮�� �ʾƾ� �Ѵ�
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
}
