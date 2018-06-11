//package scheduler;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import device.AlarmAlert;
//
//public class AlarmAlertSpyTest {
//
//	private AlarmAlert alarmAlert;
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@Test
//	public void test_startAlarm() {
//		AlarmAlertSpy alarmAlertSpy = new AlarmAlertSpy();
//		alarmAlert = alarmAlertSpy;
//		callStartAlarm();
//		
//		assertTrue(alarmAlertSpy.wasAlerted());
//	}
//	
//	@Test
//	public void test_no_startAlarm() throws Exception {
//		AlarmAlertSpy alarmAlertSpy = new AlarmAlertSpy();
//		alarmAlert = alarmAlertSpy;
////		callStartAlarm();
//		
//		assertFalse(alarmAlertSpy.wasAlerted());
//	}
//
//	private void callStartAlarm() {
//		alarmAlert.startAlarm();
//	}
//
//}
