# 1주차) 스프링 환경설정 해오기

**Spring Starter Project** : 일반적인 Spring Framework 프로젝트 <br/>
**Spring Legacy Project** : 빠른 개발 프레임워크인 Spring Boot 라는 응용 프로그램을 사용하는 프로젝트

기초부터 배우는 게 목적이니 **Legacy Project**를 만들기로 한다.<br/>
여기서 고민했음

**Simple Spring Maven** <br/>
**Simple Spring Web Maven** <br/>
**MVC** --> 선택

일반적으로 SpringMVC 로 만든다고 하니 mvc 프로젝트로 해보자. (정확한 환경셋팅은 스터디 모이면 다시 얘기해볼 것)
<img width="596" alt="프로젝트 설정방법" src="https://user-images.githubusercontent.com/12723983/40985414-0746eaa0-691f-11e8-94da-2e3c0678ccb7.png">

Maven : 빌드 도구
jar 파일들과 프로젝트를 구성한다.
프로젝트 최초 구성 시 필요한 라이브러리들을 다운받는다.

---

.... tomcat 이 자꾸 에러가 나서 4시간이나 헤맸다. 자꾸 maven 밑에 있는 놈이 없다고 나와... <br/> 
결론은 .m2 디렉토리 안에 있는 파일들을 지우고, 다시 설치해주면 된다.
> 1) .m2 디렉토리 찾아서 .m2 폴더 밑의 파일들을 지운다. (맥에서 찾는 방법: `open ~/.m2`)<br/>
> 2) Project 오른쪽 클릭 > Maven > Download Source <br/>
> 3) 이래도 아무일 없음 바로 밑에있는 Update Project 를 해주면 된다.
<img width="590" alt="maven라이브러리 다시 받기" src="https://user-images.githubusercontent.com/12723983/40992584-300f6882-6932-11e8-8e47-617f9ca7f955.png">
아 행복하다... 자자 이제

---

참고 
- https://jinseong0928.blogspot.com/2012/10/maven-dependency-missing-artifact.html

## 영민오빠 환경셋팅
1) 환경설정을 먼저 한다.
java
맥이 아닌 경우엔 encode 치고 workspace 탭에서 Other 가서 UTF-8 를 설정해줘야 한다.

web 탭에서 utf-8 로 설정하기 (맥엔 되어있음)

font - text font 에서 원하는거 바꿈.
2) 톰캣 설정을 바꾼다.
server.xml 8080 으로 설정되어있는데 충돌나지 않게 9999로 바꿔준다.
urlencoding dㅡㄹ utf-8 로 설정해둠
- 톰캣 자동으로 업데이트 해주는 방법. content.xml Cotent reloadable="true" 

3) STS 에서 빈 프로젝트 화면에서 서버를 
4) 톰캣 서버 생기면 설정되면server lands... 에 두번째 옵션을 선택해준다.

5) 웹 aps 설정....??

...




