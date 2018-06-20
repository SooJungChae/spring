# 4장 스프링 MVC 기초

**[4.1 스프링 MVC](#4.1-스프링-MVC)**<br/>
**[4.2 첫번째 스프링 MVC 만들기](#4.2-첫번째-스프링-MVC-만들기)**<br/>
**[4.3 스프링 MVC 아키텍처](#4.3-스프링-MVC-아키텍처)**<br/>

---

## 4.1 스프링 MVC

스프링 MVC란, 자바 웹 어플리케이션을 개발할 때 사용하는 프레임워크의 하나이다.
프레임워크 아키텍쳐로 MVC패턴을 채택했다.

MVC는 Controller, View, Model로 구성되어 있다.
- Controller : 모델과 뷰의 호출을 제어한다.
- View : 상태 데이터를 참조하고, **응답 데이터** 를 생성한다.
- Model : 상태나 비즈니스 로직을 제공한다.

### 스프링 MVC로 웹 어플리케이션을 개발했을 때의 특징

- POJO 구현 : 라이브러리 같은거 추가 안해도 사용 가능한 것. 단위테스트 하는게 수월해진다. 는 딱 그 메소드만! 테스트 하는 것.
- 애너테이션을 이용한 정의 정보 설정
- 유연한 메서드 시그니처 정의
- Servlet API 추상화
- 뷰 구현 기술의 추상화
- 스프링의 DI컨테이너와의 연계

### MVC 프레임워크의 특징

- 풍부한 확장 포인트 제공 : M, V, C처럼 각 역할을 나눌 수 있는 인터페이스를 제공한다.
- 엔터프라이즈 어플리케이션에 필요한 기능 제공
- 서드파티 라이브러리와의 연계 지원

### 스프링 MVC 어플리케이션 만들기

- [기본설정](#-기본설정)<br/>
- [최상위 페이지를 ViewResolver로 표시해보기](#-최상위-페이지를-ViewResolver로-표시해보기)<br/>
- [입력화면을 구현해보기](#-입력화면을-구현해보기)<br/>
- [입력값 전송 & 결과값 출력 구현](#-입력값-전송-&-결과값-출력-구현)<br/>
- [입력값 검사](#-입력값-검사)<br/>

### 기본설정

입력 화면에서 입력 값을 받아 > 출력 화면으로 표시하는 어플리케이션을 만들어보자.

**1) 프로젝트를 생성**

**2) 스프링 MVC 적용**
- 라이브러리 설정
  - spring-webmvc
  - hibernate-validator : 입력값의 유효성 검증
  - jcl-over-slf4j, logback-classic : 로그 출력

**3) ContextLoaderListener 설정**<br/>
어플리케이션 컨텍스트를 만들려면 여기에 클래스를 등록해야 한다.

**4) DispatcherServlet 설정**<br/>
스프링MVC의 프런트 컨트롤러를 이용하려면 DispatcherServlet클래스를
서블릿 컨테이너에 등록해야 한다. web.xml파일에서 등록한다.

*참고! (1)과는 다른 (2)를 따로 만들어줘야 한다. [차후에 설명](#DI-컨테이너와의-연계)<br/>
  (1) 웹 어플리케이션용 어플리케이션 컨텍스트
  (2) DispatcherServlet용 어플리케이션 컨텍스트*

**5) CharacterEncodingFilter 설정**<br/>
web.xml 파일에 인코딩을 등록한다.
  - encoding, forceEncoding, CharacterEncodingFilter

**6) ViewResolver 설정**<br/>
이 클래스는, 논리적인 뷰의 이름을 보고 물리적인 뷰가 무엇인지 판단하게 해준다.
```javascript
@Override
public void configureViewResolvers(ViewResolverRegistry registry) {
  registry.jsp();
}
```
이렇게 빈을 정의하면 /WEB-INF 디렉터리 바로 아래에 저장된 JSP 파일이 뷰로 취급된다.

**7) 태그 라이브러리 정의의 추가**

### 최상위 페이지를 ViewResolver로 표시해보기

**1) 컨트롤러를 구현한다.**
- @Controller 애너테이션 지정
- @RequestMapping("/") 요청을 이곳에 맵핑
- return "index"; /WEB-INF/index.jsp 가 호출된다.

**2) index.jsp 이동**<br/>
ViewResolver를 통해 index.jsp를 표시하려면 /WEB-INF/디렉터리 바로 아래로 가있어야 한다.

**3) 서버가 정상구동되면 최상위 페이지가 나타나는 걸 볼 수 있다.**

### 입력화면을 구현해보기

**1) 폼 클래스 구현**<br/>
`private String text;`처럼 프로퍼티를 정의한다.

**2) 컨트롤러 구현**<br/>
입력화면의 표시 요청을 처리하기 위한 메서드를 구현한다.
```javascript
@RequestMapping(method = RequestMethod.GET)
public String viewInput(Model model) {
  EchoForm form = new EchoForm();
  model.addAttribute(form);
  return "echo/input";
}
```

**3) 입력화면 JSP 구현**<br/>
`<form:form>`태그를 이용해서 폼을 작성한다.
```javascript
<form:form modelAttribute="echoForm"> ...
```
### 입력값 전송 & 결과값 출력 구현

**1) 컨트롤러 구현**
```javascript
@RequestMapping(method = RequestMethod.POST) {
  public String echo(EchoForm form) {
    return "echo/output";
  }
}
```

**2) 출력화면 JSP 구현**<br/>
`<c:out>` 요소를 사용해서 text 객체 가져온다.
```javascript
<c:out value="${echoForm.text}" />
```

### 입력값 검사

- 검사 규칙 지정 : 자바의 Bean Validation 메커니즘을 사용한다.
(@NotEmpty, @Size(max=100)...)

**1) 컨트롤러 구현**
```javascript
@RequestMapping(method = RequestMethod.POST) {
  public String echo(@Valid EchoForm form, BindingResult result) {
    if (result.hasErrors()) {
      return "echo/input";  
    }
    return "echo/output";
  }
}
```

**2) 입력화면 JSP 구현**<br/>
`modelAttribute` 에 객체의 프로퍼티명을 지정하면
`form:errors` 부분에서 path 로 지정한 부분의 값이 나타난다.
```javascript
<form:form modelAttribute="echoForm">
  ...
  <form:errors path="text" />
```

## 4.3 스프링 MVC 아키텍처

**[프론트 컨트롤러 아키텍처](#프론트-컨트롤러-아키텍처)**<br/>
**[ DI 컨테이너와의 연계](#DI-컨테이너와의-연계)**<br/>

### 프론트 컨트롤러 아키텍처
스프링MVC에서는 프론트 컨트롤러 아키텍처를 채택하고 있다.
프론트 컨트롤러는 클라이언트 요청을 가장 먼저 받아서,
요청 내용에 따라 수행 핸들러(컨트롤러)를 선택하는 아키텍처다.

- 공통적인 처리를 프론트 컨트롤러에서 통합할 수 있어 핸들러에서 처리하는 내용을 줄일 수 있다.
- DispatcherServlet 클래스로 구현되어 있다.

**프론트 컨트롤러의 일반적인 흐름**
  > 클라이언트 요청 <br />
  > -> DispatcherServlet <br />
  > -> HandlerMapping 인터페이스의 getHandler 호출해서 요청 처리하는 핸들러(컨트롤러) 가져옴 <br />
  > -> HandlerAdapter 인터페이스에서 특정 hadler 호출의뢰<br />
  > -> 특정 handler 에서 요청 처리하고 View 반환 <br />
  > -> ViewResolver 인터페이스의 resolveViewName 호출해서 반환된 View 객체 가져옴 <br />
  > -> View 인터페이스의 render 메서드를 호출해서 렌더링 요청, 데이터 생성 <br />
  > -> DispatcherServlet 가 최종적으로 클라이언트에 응답을 반환 <br />

- **DispatcherServlet**
  - 프런트 컨트롤러와 연동되는 진입점 역할
  - 기본적인 처리 흐름 제어
- **HandlerMapping**
  - 요청에 대응할 핸들러 선택
  - @RequestMapping("/")
- **HandlerAdapter**
  - 핸들러 메서드를 호출
  - RequestMappingHandlerAdapter 클래스에서 매개변수를 전달하고 메서드의 처리 결과를
  반환값으로 되돌려 보내는 역할 수행
- **ViewResolver**
  - 핸들러에서 반환한 뷰 이름을 보고 View 인터페이스의 구현 클래스를 선택하는 역할
- **View**
  - 응답데이터를 생성

### DI 컨테이너와의 연계
스프링 MVC는 DI에서 관리되는 객체를 사용해 클라이언트에서 받은 요청을 처리하는 구조다.<br/>
어떻게 둘이 연동이 될까?

스프링에서는 두 가지 어플리케이션 컨텍스트를 사용한다.<br/>
(1) 웹 어플리케이션용 어플리케이션 컨텍스트<br/>
(2) DispatcherServlet용 어플리케이션 컨텍스트<br/>

(1)은 전체에서 하나, (2)는 DispatcherServlet마다 인스턴스가 생성된다.<br/>
그래서 (1)에는 전체에서 사용하는 컴포넌트 빈을 등록하며(Service, Repository, Datasource, ORM)<br/>
(2)에선 MVC프런트 컨트롤러의 구성 컴포넌트와 컨트롤러의 빈을 등록한다.<br/>
이 둘은 부모, 자식관계로 되어 있어서 (1)에 등록되어 있는 빈을 사용할 수 있다.<br/>

---

* Servlet : 클라이언트로부터 받은 요청을 처리하고 그 결과를 다시 클라이언트에 전송하는
Servlet 클래스의 구현 규칙을 지킨 자바 프로그램

* 어플리케이션 컨텍스트 : 어플리케이션을 구성하는 것들. 폼, 이미지, 동영상 등등

---

### 6월 20일 스터디 내용

* http 통신을 하려면 get 을 사용해야 한다.
* http 포트는 80 https 포트는 443 을 사용한다.
* 스프링은 '인터페이스'를 사용하기 때문에 유연함이 가장 큰 장점이다.
* 날짜 시간 처리할 때 Joda-Time을 사용한다.
* servlet : WAS 위에서 동작하는 받아주는 도구
* DispatcherServlet = Front Controller 요청을 다 받아
* HandlerMapping 은 
* ViewResolver 어디다가 뿌릴꺼냐~
* XSS : 악성 스크립트를 화면에 넣어서 http request를 하면 개인정보 탈취, 해킹하는 것 (필터로 막아야 한다. 이건 잘 모르니까 나중에 구현하면서 물어보기!)
* 입력값 검사할 때 @NotNull 이 아니라 @NotEmpty를 써라.

* 스프링 프레임워크 아키텍쳐 외우자! 내가 직접 그려볼 수 있어야 해. p.207
* handlerMapping 에선 url 가 어딘지 알려주고
* handlerAdapter 는 핸들러가 어딘지 알려주고
* ViewResolver 말고 다른 resolver들도 많다. (p.210) MultipartResolver 는 파일사용할 때 사용
* web.xml 은 Servlet 에서 돌아가고
* servlet-context 파일은 dispatcher servlet 에서 돌아간다.
* p.217 보고 좀 
