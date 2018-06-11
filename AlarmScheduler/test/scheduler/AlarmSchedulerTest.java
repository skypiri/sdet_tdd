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
		// �˶��� �߰����� ���� ���¿�����
		
		// IH�� scheduler�� wake-up()�� ȣ���ص�
		scheduler.wakeup();
		
		// AlarmAlert�� startAlarm()�� ȣ����� �ʴ´�.
		thenNoAlarms();
	}


	
	@Test
	public void test_startAlarm() throws Exception {
		// 08:00�� �˶��� �߰��� ���¿���
		givenScheduleAs(8 * 60);
		
		// 08:00�ð� �Ǹ� (���� �ð��� 08:00�̸�)
		whenTheTimsIs(8 * 60);
		
		// �˶��� �︰��.
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
		// Ư�� ���Ͽ� �����ϴ� Ÿ�̸�
		givenScheduleAs(MONDAY, 9 * 60);
		whenTheTimsIs(MONDAY, 9 * 60);
		thenItAlarms();
	}
	
	@Test
	public void test_startAlarm_DayValid_TimeInValid() throws Exception {
		// Ư�� ���Ͽ� �����ϴ� Ÿ�̸�
		givenScheduleAs(MONDAY, 9 * 60);
		whenTheTimsIs(MONDAY,8 * 60);
		thenNoAlarms();
	}
	
	@Test
	public void test_startAlarm_DayInValid_TimeValid() throws Exception {
		// Ư�� ���Ͽ� �����ϴ� Ÿ�̸�
		givenScheduleAs(MONDAY, 9 * 60);
		whenTheTimsIs(WENDSDAY, 9 * 60);
		thenNoAlarms();
	}
	
	@Test
	public void test_startAlarm_weekdays_TimeValid() throws Exception {
		// ���� ���Ͽ� �����ϴ� Ÿ�̸�
		givenScheduleAs(WEEKDAYS, 9 * 60);
		whenTheTimsIs(WENDSDAY, 9 * 60);
		thenItAlarms();
	}
	
	@Test
	public void test_startAlarm_weekdaysInvalid_TimeValid() throws Exception {
		// ���� ���Ͽ� �����ϴ� Ÿ�̸�
		givenScheduleAs(WEEKDAYS, 9 * 60);
		whenTheTimsIs(SUNDAY, 9 * 60);
		thenNoAlarms();
	}
	
	@Test
	public void test_startAlarm_weekends_TimeValid() throws Exception {
		// ���� ���Ͽ� �����ϴ� Ÿ�̸�
		givenScheduleAs(WEEKENDS, 9 * 60);
		whenTheTimsIs(SUNDAY, 9 * 60);
		thenItAlarms();
	}
	
	@Test
	public void test_startAlarm_weekendsInvalid_TimeValid() throws Exception {
		// ���� ���Ͽ� �����ϴ� Ÿ�̸�
		givenScheduleAs(WEEKENDS, 9 * 60);
		whenTheTimsIs(MONDAY, 9 * 60);
		thenNoAlarms();
	}
	
	@Test
	public void test_startAlarm_weekends_TimeInValid() throws Exception {
		// ���� ���Ͽ� �����ϴ� Ÿ�̸�
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
