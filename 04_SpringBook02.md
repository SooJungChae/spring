#스프링 철저 입문 2장 공부하기

Inderface 다중상속이 된다.

## 2.1 DI (Dependency Injection)

여러가지의 클래스가 조합되어 있는 클래스를 만들려면 구현 클래스가 이미 개발되어 있어야 한다.
하지만 실제상황에선 수정작업을 하게 될 수도 있고 이미 완성된 클래스를 만들어 두는 경우가 흔하지 않다.
그래서 컴포넌트가 완성될 때까지 임시의 Dummy로 클래스를 만들어 대체하는 방법을 사용한다.

이렇게 어떤 클래스가 필요로 하는 컴포넌트를 외부에서 생성한 후, 내부에서 사용 가능하게 만들어 주는
과정을 '의존성을 주입한다' 또는 '인젝션한다' 라고 한다. 그리고 이러한 주입을 자동으로 처리하는 기반을 'DI 컨테이너'라 한다.
그리고 **DI에 등록하는 컴포넌트를 Bean이라 한다.**

DI컨테이너에 인터페이스와 구현 클래스를 알려주고 의존 관계를 정의해주면 자동으로 생성되어 주입된다.
인스턴스의 생성과 의존 관계의 연결 처리를 소스 코드에서 하지 않고 DI컨테이너가 대신하기 때문에 **제어가 역전되었다**라고 한다.

DI 컨테이너의 장점
- 컴포넌트간의 의존성 해결
- 어떤 컴포넌트는 반드시 단 하나의 인스턴스만 만들어 재사용되도록 싱글턴 객체로 만들 수 있음
- 어떤 컴포넌트는 필요할 때마다 새로운 인스턴스를 사용하도록 프로토타입 객체로 만들 수 있음
- 인스턴스의 스코프 관리
- AOP작업

사용방법
1) ApplicationContext 로 DI 컨테이너를 만든다.
```
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
```
2) getBean으로 DI컨테이너에서 인스턴스를 꺼낸다.
```
UserService userService = context.getBean(UserService.class);
```

### AppConfig 클래스
- AppConfig 클래스는 DI컨테이너에서 설정 파일 역할을 한다.
- @Configuration어노테이션을 붙여 설정 클래스를 선언한다. (설정 클래스는 여러개 정의 가능)
- @Bean어노테이션으로 빈을 정의한다. 메서드명이 빈의 이름. 빈의 인스턴스가 반환값이 된다.

### 빈 설정

1) 자바기반
- java configuration class 에서 @Configuration 설정클래스 선언
- @Bean 빈 정의

2) xml기반
- <beans>안에 빈 정의 여러개
- id="빈이름" class="해당빈의클래스"
- <sonctructor-arg> 의 ref 속성에 주입할 빈의 이름 기재

### 의존성 주입



### 오토와이어링

자바 설정 기반 방식에서 @Bean메서드를 사용하거나 xml기반 방식에서 <bean>요소를 사용하는 것처럼
명시적으로 빈을 정의하지 않고도 DI컨테이너에 빈을 자동으로 주입하는 방식이다.
타입으로 오토와이어링 하는 방법과 이름으로 오토와이어링 하는 방법이 있다.

1) 타입으로 오토와이어링

- @Autowired어노테이션을 넣는다.
- 필수 조건을 완화하려면 @Autowired(required = false)를 설정한다.


2) 이름으로 오토와이어링


## 2.2 AOP (Aspect Oriented Programming) 관점 지향 프로그래밍

비즈니스 로직과는 관계없는 로깅이나 캐시같은 작업들은 프로젝트의 이곳저곳 사용되고 있다.
이건 DRY원칙에 미약하고 향후 변경에도 취약하다. 이렇게 **비즈니스 로직과는 거리가 있지만
여러 모듈에 걸쳐 공통적이고 반복적으로 필요한 내용을 횡단 관심사라고 부른다.**

`보안, 로깅, 트랜잭션 관리, 모니터링, 캐시 처리, 예외 처리`

이런 횡단 관심사들을 분리해서 한 곳으로 모으는 것을 횡단 관심사의 분리라 부르고, **관점 지향 프로그래밍(AOP)**이라고 한다.

- 애스펙트 : 횡단 관심사의 단위. (로그를 출력한다, 예외를 처리한다. 트랜잭션을 관리한다.)
- 조인 포인트 : 횡단 관심사가 실행될 지점이나 시점. 메서드 단위로 조인 포인트를 잡는다.
- 어드바이스 : 횡단 관심사를 실제로 구현해서 처리하는 부분. (Around, Before, After ...)
- 포인트컷 : 수많은 조인 포인트 중에서 실제로 어드바이스를 적용할 곳을 선별하기 위한 표현식. (~ServiceImple 이름이 붙은 곳에서 어드바이스를 실행)
- 위빙 : 애스펙트가 적용되는 시점. (컴파일 시점, 클래스 로딩 시점, 실행 시점...)
- 타깃 : AOP처리에 의해 처리 흐름에 변화가 생긴 객체

### 스프링 AOP

스프링 프레임워크 안에는 AOP를 지원하는 모듈로 스프링AOP가 포함되어 있다.
그리고 스프링AOP에는 AspectJ라는 AOP프레임워크가 포함되어 있다.

사용방법
1) pom.xml 파일에 다음과 같이 의존 관계를 정의한다.
```
<artifactId>spring-context</artifactId>
...
<artifactId>spring-aop</artifactId>
...
<artifactId>aspectjweaver</artifactId>
```

2) 애스팩트를 구현한다. @Aspect
3) DI 컨테이너에서 관리되도록 @Component 어노테이션을 붙인다.
4) 어디바이스와 포인트컷 표현식을 붙인다. @Before("execution(* *..*ServiceImpl.*(..))")
5) AOP를 활성화한다. @EnableAspectJAutoProxy / <aop:aspectj-autoproxy>


---

## 2.3 데이터 바인딩과 형 변환

post한 데이터는 String 형식인데 get하는 입장에서는 Int만 받을 수 있다고 해보자.
그럴 땐 C#에선 (Int32) 처럼 형변환을 해줘야 하는데, 데이터의 수가 많거나
실수로 한 곳에서 형변환을 사용하지 않았다면 에러 잡는 데 매우 불편할꺼다.
Spring 에서는 효율적인 형변환 인터페이스를 제공한다.

1) String 값에 대한 데이터 바인딩
DataBinder클래스에 데이터 바인딩 기능이 있다. Http 를 사용하는 환경에서는 서블릿 api에 맞게 커스터마이징 된
ServletRequestDataBinder클래스를 사용한다.
```
Employee form = new EmployeeForm();
ServletRequestDataBinder dataBinder = new ServletRequestDataBinder(form);
dataBinder.bind(request);
```

2) Spring의 형변환 방법
- BeanWrapper, BeanFactory, Converter, Formatter

3) PropertyEditor 활용
4) ConversionService 활용
- 빈의 ID가 `conversionService`가 되도록 정의해야 나중에 Conversion Service를 활용할 컴포넌트가
빈 이름을 틀리지 않고 자동으로 주입받을 수 있다.
5) 기타 형변환은 책에 더 많으니 참고하자.

---

## 2.4 프로퍼티 관리

데이터베이스에 접속하는 연결 정보처럼 여러 곳에서 사용되지만 잘 변경되지 않는 값이 있다.
그런데 만약 설정 파일이 바뀌게 된다면 여기저기서 사용 된 코드를 하나씩 바꿔주는 게 번거로울 것이다.
Spring 은 프로퍼티 관리를 효과적으로 할 수 있게 해준다.

1) Bean 정의 할 때 프로퍼티 활용
@Value 어노테이션을 활용해서 별도의 프로퍼티 파일에 정의된 값을 소스에 주입할 수 있다.
```


---

의존성이 뭘까?

@Value("${datasource.url}") String url, ...
```
- `${프로퍼티키}`부분은 나중에 다른 값으로 치환될 자리라는 뜻이다. 플레이스 홀더 라고도 한다.
- 프로퍼티 파일의 위치는 <context:property-placeholder> 요소에서 location 에 지정한다.
```
<context:property-placeholder location="classpath:application.properties"/>
```
- 같은 이름의 프로퍼티가 중복 설정되어 있다면 `JVM 시스템 프로퍼티 > 환경 변수 > 프로퍼티 파일` 우선순위에 따라 프로퍼티가 적용된다.
- 플레이스 홀더에 기본 값을 줄 수 있다. 
```
${프로퍼티 키:기본값}
${datasource.username:demo}
```

---

## 2.5 스프링 표현 언어

### SpEL (Spring Expression Language)

---

## 2.6 리소스 추상화

프로젝트를 배포했을 때 서버의 경로와 로컬의 경로가 달라서 에러가 발생하는 경우가 있다.
이걸 방지할 수 있는 방법인데, 절대 경로를 사용해서 리소스를 추상화 시키는 방법이다.

### ResourceLoader

Resource 객체를 가져오려면 `getResource`메서드의 매개변수로 리소스의 위치를 지정하면 된다.
리소스의 위치를 지정할 땐 일반적으로 파일시스템 상의 경로나 url 상의 경로를 사용하는데,
클래스상의 리소스를 지정해야 한다면 `classpath:` 접두어를 붙이면 된다.

- 프로퍼티 기능을 사용해서 `@Value("${resource.greeting:classpath:greeting.json}")`처럼 변경하면 하드코딩하지 않고 손쉽게 경로에 접근할 수 있다.
- classpath: 와 비슷하지만, classpath*: 는 jar 파일과 같은 다른 모듈 안에 포함된 파일도 읽을 수 있다.  
* 사용하면 파일을 못찾아도 예외를 발생시키지 않는다.

---

## 2.7 메세지 관리

프로젝트를 진행하면서 오류나 알림창 같은 문자열 형태의 메세지를 일일이 작성하곤 했다.
Spring 에서는 이런 메세지를 쉽게 입력할 수도 있고 미리 메세지를 입력해두는 인터페이스가 있다.

### MessageSource

MessageSource 인터페이스를 들여다보면 3가지가 있는데,
사용자가 호출할 경우 차례대로 전부 대입해봐서 메세지에 해당하는 인수와 일치하는 코드를 사용하여 메세지를 만들어낸다.

### MessageSource 사용법

1) MessageSource의 Bean 정의
2) 프로퍼티 파일에 메세지 정의 --> 이곳에다가 나중에 필요한 메세지들을 입력해두고 사용하면 된다.
3) DI 에서 `MessageSource` 를 주입받고
4) `getMessage` 메서드를 호출해서 사용한다.
5) 문자열을 하드코딩 하기 싫다면 `MessageSourceResolvable`를 활용한다.

