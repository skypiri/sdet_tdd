# SDET_TDD

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
* STB Alarm 등의 기능을 실습해본 프로젝트 

### AlarmScheduler3
* 지역별 주말 처리를 위한 프로젝트

***
## 좋은 설계
* SOLID 설계 원칙
  - Single Responsibility Principle : 한가지 기능만 수행해라. 이것 저것 다 하려고 하지 마라.
  - Open Closed Principle
  - Liskov’s Substitution Principle
  - Interface Segregation Principle
  - Dependency Inversion Principle
  
***
## 실습 : STB Alarm UI (알람을 등록하는 control console을 제공해야 한다)
* 개발상황
	- MVP를 적용하여 Presenter/View의 역할을 분리해야한다. 
	- 알람을 등록하는 GUI만 만들면 된다.
	- 화면의 구성은 미리 협의된 초안이 있으나 향후 바뀔 가능성이 크다.
	- 일단은 미려한 GUI보다는 동작하는 시나리오를 먼저 보여주기를 원한다.
	- 향후 여러 GUI 플랫폼을 지원하게 될 수도 있다.
	
* 추가 테스트
-알람 등록 시 발생할 수 있는 예외 사항은?
	- 입력이 X
 	- 입력 값이 이상한 경우
- 사용자가 일정을 입력하지 않고
- 등록버튼을 누르면
 	- 일정이 입력되지 않아야 한다.
 	- 입력이 잘못되었다고 사용자에게 메시지
- 알람 종류를 선택하도록 UI가 확장된다면?
- 삭제 기능이 추가된다면?




	
