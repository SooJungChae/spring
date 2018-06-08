# *작성중 입니다.* 

이전까지는 Spring 프레임워크에 mysql 을 사용하기 위해서 설정하는 부분이었다. 그런데 생각보다 진행되지 않았다. 내가 리눅스 언어를 잘 모르는 것도 있었고, 환경설정을 처음부터 하는게 매우 어려웠다.
그래서 내일 해야할 과제를 위해서 일단 mysql 의 workbench 를 다운로드해보겠다.

### Workbench

- [이 곳](https://dev.mysql.com/downloads/workbench/?utm_source=tuicool)에서 쉽게 다운로드 가능하다.
- mysql 에 database 를 만들고 table 을 하나 만들어보자.
> File > New Model > 

### JDBC (Java DataBase Connectivity)

이제 JAVA 에서 mysql 을 어떻게 사용할까를 고민해야한다. C#에서는 dbconnection 을 통해 연결하는 작업을 설정해주는데, 
이처럼 java 에도 database 에 연결하는 작업이 필요하다.
이걸 JDBC 가 해준다. 즉, **자바에서 데이터베이스에 접속할 수 있도록 하는 자바 API** 다.

Mysql 은 JDBC 에 연동되는 Connector 라이브러리를 제공한다. JAVA 말고도 C, C++, Net, Node.js 등 다양한 언어를 지원하니 
[홈페이지](https://dev.mysql.com/downloads/connector/)를 참고하면 되겠다.

필요한 Connector 를 다운받아 압축을 풀어보자. `mysql-connector-java-[version]-bin.jar` 라이브러리 파일이 보일 것이다. 이 파일을 STS 환경에 추가해주면 된다.
<img width="660" alt="mysql connector 를 Java build 환경에 추가해주기" src="https://user-images.githubusercontent.com/12723983/41159628-20152344-6b68-11e8-845c-f60bb798c1ec.png">

적용하는데 꽤 오래걸린다.
<img width="517" alt="적용중" src="https://user-images.githubusercontent.com/12723983/41160121-a5d48d02-6b69-11e8-8071-d23272e2f63e.png">

Spring 프로젝트를 켰는데
에러가 우르르 나있는 상황..!

1) 프로젝트 우클릭 > run as > maven test
2) 프로젝트 clean 후 빌드 (자동 컨텍스트 리로딩) 하니 에러 사라졌고

이젠 src 폴더에 에러나있는 상황.
오류 로그를 구글링 해보니 메이븐 버전과 JDK 버전이 맞지 않을 때 예외가 발생한 것 이었다.

대체 maven 과 JDK 는 무슨 사이인건지? 공부 좀 하고 가야겠다.

### Maven & JDK
여기의 설명을 가져왔습니다. 자세한 건 이 곳을 참고해주세요.
http://www.holaxprogramming.com/2011/12/24/devops-build-tool/

Maven 은 빌드(Build)도구다. Java 기반의 프로젝트를 효율적으로 Build 할 수 있도록 도와준다.
프로젝트를 개발자 PC 의 IDE 에서 빌드할 필요가 없다.
IDE 에서 개발하면
- 배포 Version 을 맞추는 게 까다롭고 
- 배포를 자동화하는 게 불가능하다.

Maven 은
- 플러그인들이 POM(Project Object Model)으로 정의된다. 
프로젝트에 의존되는 라이브러리들이 pom.xml 파일에서 정의되기 때문에 관리가 매우 쉽다는 느낌을 받았다.
- Maven 은 빌드에 대한 대부분의 책임을 각 플러그인에 위임한다. (버전 관리에 효과가 있다는 말인가?)
- 빌드 프로세스를 인터페이스로 제공해서 개발환경에 대한 빌드 프로세스 파악이 편리하다.

> 개발자는 프로젝트에 필요한 **다양한 플러그인을 효율적으로 관리**하고 개발자의 IDE 가 아닌
**별도의 빌드 머신에서 빌드**하고 결과물을 운영서버에 **배포하는 과정을 자동화**할 수 있다.

아니? 엄청나잖아!

STS 에서 Maven project 를 생성하면 갖게 되는 프로젝트 구조 (이것도 정리해주시네) 
/src/main/java	자바 클래스 파일
/src/main/resource	스프링 환경 설정에 필요한 리소스 파일들
/src/test/java	JUnit을 이용한 단위테스트를 위한 클래스는 이곳에
/src/test/resource	테스트 환경에서 필요한 리소스파일들
pom.xml	프로젝트정보, 프로젝트에 필요한 라이브러리, 플러그인을 정의 + 플러그인들의 의존 관계 정의

#### JDK (Java Development Kit)


다시 돌아가서... Maven 과 JDK 버전이 다르다는건, Maven 에서 지원되는 라이브러리 버전이 있는건가?
아니면. 명시되어 있는 버전이 다르다는 뜻인것 같다.

좀 더 찾아보니 **컴파일 때 사용된 JDK 버전과 실행시 사용되는 JDK 버전이 달라서 생기는 오류**였다.

에러 메세지
```
Exception in thread "main" java.lang.UnsupportedClassVersionError: org/apache/maven/cli/MavenCli : Unsupported major.minor version 51.0
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClassCond(ClassLoader.java:637)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:621)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:141)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:283)
	at java.net.URLClassLoader.access$000(URLClassLoader.java:58)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:197)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:190)
	at org.codehaus.plexus.classworlds.realm.ClassRealm.loadClassFromSelf(ClassRealm.java:401)
	at org.codehaus.plexus.classworlds.strategy.SelfFirstStrategy.loadClass(SelfFirstStrategy.java:42)
	at org.codehaus.plexus.classworlds.realm.ClassRealm.unsynchronizedLoadClass(ClassRealm.java:271)
	at org.codehaus.plexus.classworlds.realm.ClassRealm.loadClass(ClassRealm.java:254)
	at org.codehaus.plexus.classworlds.realm.ClassRealm.loadClass(ClassRealm.java:239)
	at org.codehaus.plexus.classworlds.launcher.Launcher.getMainClass(Launcher.java:144)
	at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced(Launcher.java:266)
	at org.codehaus.plexus.classworlds.launcher.Launcher.launch(Launcher.java:229)
	at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode(Launcher.java:415)
	at org.codehaus.plexus.classworlds.launcher.Launcher.main(Launcher.java:356)
```

터미널에서 자바 버전을 찾아보자.
```
$ java -version
java version "10.0.1" 2018-04-17
Java(TM) SE Runtime Environment 18.3 (build 10.0.1+10)
Java HotSpot(TM) 64-Bit Server VM 18.3 (build 10.0.1+10, mixed mode)
```

으잉! 10.0.1 을 쓰고 있었네.

# 원인
jar 는 JDK 1.7 에서 컴파일 됐는데 JDK 1.6 환경에서 실행하고 있기 때문에 발생한거다.

# 해결방법


그냥 무시하고 `프로젝트 우클릭 > run as > run on server > tomcat` 했더니 사이트는 보여지네.

일단 Spring 과 MySQL 을 연결해보자...
mybatis 도 설치해보자. 편리하다니까.

Spring에서 직접 JDBC를 사용하여 DB를 사용할 수 있지만, Mybatis를 사용하는 것이 사실상 표준화되어있다고 볼 수 있습니다.
출처: http://boxfoxs.tistory.com/335 [박스여우 - BoxFox]

라고는 하는데... 난 기초부터 하는거니 JDBC 로 해보는 게 좋지않을까.

Spring + mybatis + MySql 은 이 블로그가 좋은 것 같다.
https://tonyne.jeju.onl/2016/02/25/스프링-db-연결하기-mysql/

1) mybatis 설치
2) mybatis-spring 설치




