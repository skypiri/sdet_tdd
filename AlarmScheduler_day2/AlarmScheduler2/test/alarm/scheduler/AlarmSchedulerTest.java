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

	class TV {
		public void turnOn() {
			
		}
		
		public void turnOff() {
			
		}
		
		public void setChannel(int ch) {
			
		}
	}
	
	class MP3 {
		public void play() {
			
		}
		
		public void stop() {
			
		}
	}
	
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
	
	private void thenItAlarms(AlarmAlert device) {
		verify(device).startAlarm();
	}
	
	private void thenItAlarms() {
		thenItAlarms(alarmSpy);
//		verify(alarmSpy).startAlarm();
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
	
	@Test
	public void test_support_LED_alarm() throws Exception {
		AlarmAlert LED = mock(AlarmAlert.class);
		givenThatScheduleIsAddedAs(LED, EVERYDAY, theTime);
		whenItBecomesTheTime(SUNDAY, theTime);
		thenItAlarms(LED);
	}

	private void givenThatScheduleIsAddedAs(AlarmAlert device, Day day, int minute) {
		// TODO Auto-generated method stub
		scheduler.addSchedule(device, day, minute);
		
	}
	
	@Test
	public void test_support_TV_control() throws Exception {
		final TV tv = mock(TV.class);
		AlarmAlert tvOnCommand = new AlarmAlert() {

			@Override
			public void startAlarm() {
				// TODO Auto-generated method stub
				tv.turnOn();
			}			
		};
		
		AlarmAlert tvOffCommand = new AlarmAlert() {

			@Override
			public void startAlarm() {
				// TODO Auto-generated method stub
				tv.turnOff();
			}
		};
		
		givenThatScheduleIsAddedAs(tvOnCommand, EVERYDAY, theTime);
		whenItBecomesTheTime(MONDAY, theTime);
		verify(tv).turnOn();
		
		givenThatScheduleIsAddedAs(tvOffCommand, EVERYDAY, theTime + 60);
		whenItBecomesTheTime(MONDAY, theTime + 60);
		verify(tv).turnOff();
	}
	
	@Test
	public void test_support_MP3_control() throws Exception {
		final MP3 mp3Player = mock(MP3.class);
		
		AlarmAlert mp3PlayCommand = new AlarmAlert() {

			@Override
			public void startAlarm() {
				// TODO Auto-generated method stub
				mp3Player.play();
				System.out.println("mp3 play");
				
			}			
		};
		
		givenThatScheduleIsAddedAs(mp3PlayCommand, EVERYDAY, theTime + 60);
		whenItBecomesTheTime(MONDAY, theTime + 60);
		verify(mp3Player).play();
		
	}
	
}
