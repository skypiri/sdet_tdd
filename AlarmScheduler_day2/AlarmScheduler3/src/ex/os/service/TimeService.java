package ex.os.service;

public interface TimeService {
	static final int  EVERYDAY = -3;
	static final int  WEEKDAY = -2;
	static final int  WEEKEND = -1;
	
	static final int  SUNDAY = 1;
	static final int  MONDAY = 2;
	static final int  TUESDAY = 3;
	static final int  WEDNESDAY = 4;
	static final int  THURSDAY = 5;
	static final int  FRIDAY = 6;
	static final int  SATURDAY = 7;
	
	static final int DEFAULT = 0;
	static final int HEBREW = 1;
	static final int ARABFARSI = 2;
	
	int  getCurrentMinute();  // 시간 (분)  ex: 1200 20시 : 오후 8시
	int  getCurrentDay();  // 요일
	
	int getTimeLocale();
}
