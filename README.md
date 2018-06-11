# sdet_tdd

## Alarm Scheduler
* TDD로 객체 지향 프로그래밍하기 위한 프로젝트
* 다음과 같은 요구사항을 만족하기 위한 기능을 구현하기 위한 TDD 방법을 습득해본다.
	* 매일 정해진 시간에 알람이 울리게 할 수 있다.
	* 특정 요일마다 정해진 시간에 알람이 울리게 할 수 있다.
	* 주중 또는 주말마다 정해진 시간에 알람이 울리게 할 수 있다.
	* 알람스케줄러에는 알람을 15개까지 등록할 수 있다.
	* 알람을 삭제할 수 있다.

* Spy : 행위가 발생되는지 확인하는 객체, Mehtod가 호출되면 Flag를 설정한다.
* Test double : dummy object, stub, spy, fake object를 사용할 수 있지만, 여전히 불편하다
	+ Test double을 지원하기 위하여 Mock을 사용한다.
	+ Java에서는 Mockito, jMock이 있다. 본 프로젝트에서는 Mockito를 활용한다.
	
