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

***

## 2일차
* 목표
	+ 코드 스멜 개선, 리팩터링 연습
	+ 테스트를 통해 SRP, OCP, DIP를 만족시키도록 설계를 개선
	+ TDD로 실무 프로젝트 코드를 작성할 수 있다.
	+ Mock Object를 능숙하게 사용할 수 있다.
	+ GUI 구현을 TDD로 수행할 수 있다.
	
### 리팩터링이나 기능 확장도 기존의 빌드 상태와 동작상태를 유지한 상태로 되어야 한다.

### 코드를 변경하기 쉽게 리팩터링 후 시도
- 새로운 테스트를 해결하기 전에 설계를 확장하려 한다면 새로운 테스트를 잠시 멈추고 기존의 테스트로 리팩터링 후 진행한다.
- 기능을 추가/변경할 때는 변화를 격리시키고 격리된 곳만 변경하는 것이 좋다.

### AlarmScheduler2
* 1일차에 이어서 진행된 프로젝트

### AlarmScheduler3
* 지역별 주말 처리를 위한 프로젝트

	
