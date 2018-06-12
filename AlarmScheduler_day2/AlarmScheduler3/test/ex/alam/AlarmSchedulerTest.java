package ex.alam;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ex.alarm.driver.AlarmAlert;
import ex.os.service.TimeService;
import static ex.os.service.TimeService.*;

public class AlarmSchedulerTest {

	
	private static final int theTime = 10*60;
	private AlarmScheduler scheduler;
	private AlarmAlert alarmSpy;
	private TimeService timeService;

	@Before
	public void setUp() throws Exception {
		timeService = mock(TimeService.class);
		alarmSpy = mock(AlarmAlert.class);
		scheduler = new AlarmScheduler(alarmSpy, timeService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoesNotAlarmIfNoSchedule() throws Exception {
		scheduler.wakeup();
		thenItDoesNotAlarms();
	}
	
	@Test
	public void testDoesAlarmIfScheduledAndItsTime() throws Exception {
		givenThatScheduleIsAddedAs(MONDAY, theTime);
		whenItBecomesTheTime(MONDAY, theTime);
		thenItAlarms();
	}

	@Test
	public void testShouldNotAlarmIfTheDayButNotMinute() throws Exception {
		givenThatScheduleIsAddedAs(MONDAY, theTime);
		whenItBecomesTheTime(MONDAY, theTime - 100);
		thenItDoesNotAlarms();
	}
	
	@Test
	public void testShouldAlarmIfEveryDayAndItsMinute() throws Exception {
		givenThatScheduleIsAddedAs(EVERYDAY, theTime);
		whenItBecomesTheTime(MONDAY, theTime);
		thenItAlarms();
	}
	
	@Ignore
	public void testShouldAlarmIfWeekendAndItsSaturdayAndItsMinute() throws Exception {
		givenThatScheduleIsAddedAs(WEEKEND, theTime);
		whenItBecomesTheTime(SATURDAY, theTime);
		thenItAlarms();
	}
	
	@Test
	public void testShouldAlarmIfWeekendAndItsWednesdayAndItsMinute() throws Exception {
		givenThatScheduleIsAddedAs(WEEKEND, theTime);
		whenItBecomesTheTime(WEDNESDAY, theTime);
		thenItDoesNotAlarms();
	}
	
	@Ignore
	public void testShouldAlarmIfWeekDayAndItsMondayAndItsMinute() throws Exception {
		givenThatScheduleIsAddedAs(WEEKDAY, theTime);
		whenItBecomesTheTime(MONDAY, theTime);
		thenItAlarms();
	}
	
	@Test
	public void testShouldAlarmIfWeekDayAndItsSaturdayAndItsMinute() throws Exception {
		givenThatScheduleIsAddedAs(WEEKDAY, theTime);
		whenItBecomesTheTime(SATURDAY, theTime);
		thenItDoesNotAlarms();
	}
	
	@Test
	public void testShouldAlarmIfWeekendAndItsSaturdayAndItsMinute_InHEBREW() throws Exception {
		givenLocaleIs(HEBREW);
		givenThatScheduleIsAddedAs(WEEKEND, theTime);
		whenItBecomesTheTime(SATURDAY, theTime);
		thenItAlarms();
	}
	
	@Test
	public void testShouldAlarmIfWeekendAndItsFridayAndItsMinute_InHEBREW() throws Exception {
		givenLocaleIs(HEBREW);
		givenThatScheduleIsAddedAs(WEEKEND, theTime);
		whenItBecomesTheTime(FRIDAY, theTime);
		thenItAlarms();
	}
	
	@Test
	public void testShouldAlarmIfWeekdayAndItsSundayAndItsMinute_InHEBREW() throws Exception {
		givenLocaleIs(HEBREW);
		givenThatScheduleIsAddedAs(WEEKDAY, theTime);
		whenItBecomesTheTime(SUNDAY, theTime);
		thenItAlarms();
	}
	
	@Test
	public void testShouldAlarmIfWeekendAndItsFridayAndItsMinute_InARABFARSI() throws Exception {
		givenLocaleIs(ARABFARSI);
		givenThatScheduleIsAddedAs(WEEKEND, theTime);
		whenItBecomesTheTime(FRIDAY, theTime);
		thenItAlarms();
	}
	
	@Test
	public void testShouldAlarmIfWeekendAndItsThursdayAndItsMinute_InARABFARSI() throws Exception {
		givenLocaleIs(ARABFARSI);
		givenThatScheduleIsAddedAs(WEEKEND, theTime);
		whenItBecomesTheTime(THURSDAY, theTime);
		thenItAlarms();
	}
	
	@Test
	public void testShouldAlarmIfWeekendAndItsSundayAndItsMinute_InARABFARSI() throws Exception {
		givenLocaleIs(ARABFARSI);
		givenThatScheduleIsAddedAs(WEEKEND, theTime);
		whenItBecomesTheTime(SUNDAY, theTime);
		thenItDoesNotAlarms();
	}
	
	@Test
	public void testShouldAlarmIfWeekdayAndItsSundayAndItsMinute_InARABFARSI() throws Exception {
		givenLocaleIs(ARABFARSI);
		givenThatScheduleIsAddedAs(WEEKDAY, theTime);
		whenItBecomesTheTime(SUNDAY, theTime);
		thenItAlarms();
	}
	
	@Test
	public void alarmAlertIfThereAreMultipleAlarmRegistered() {
		givenThatScheduleIsAddedAs(FRIDAY, theTime);
		givenThatScheduleIsAddedAs(THURSDAY, theTime);
		whenItBecomesTheTime(FRIDAY, theTime);
		thenItAlarms();
	}
	
	private void thenItDoesNotAlarms() {
		verify(alarmSpy, never()).startAlarm();
	}

	private void thenItAlarms() {
		verify(alarmSpy).startAlarm();
	}

	private void whenItBecomesTheTime(int day, int minute) {
		when(timeService.getCurrentDay()).thenReturn(day);
		when(timeService.getCurrentMinute()).thenReturn(minute);
		scheduler.wakeup();
	}


	private void givenLocaleIs(int locale) {
		when(timeService.getTimeLocale()).thenReturn(locale);
	}
	
	private void givenThatScheduleIsAddedAs(int day, int minute) {
		scheduler.register(day, minute);
	}

}
