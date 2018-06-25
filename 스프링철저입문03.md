# 3장 데이터접근(JDBC, Tx)

## 3.1 스프링 프레임워크와 데이터 소스

### 데이터 소스
애플리케이션이 데이터베이스에 접근하기 위한 커넥션을 제공하는 역할을 한다.

### 데이터 소스의 종류
- 애플리케이션 모듈이 제공하는 데이터 소스
  - 스프링 프레임워크가 테스트 용도로 제공하는 데이터 소스다.
  - 데이터 베이스의 접속 정보와 설정파일들은 별도의 jdbc.properties 에 기재한다.
- 애플리케이션 서버가 제공하는 데이터 소스
  - JNDI(Java Naming and Directory Interface)를 통해 데이터 소스를 가져온다.
  - 데이터 베이스의 각종 정보를 애플리케이션 서버가 갖고 있기 때문에
    정보를 분리할 수 있다는 장점이 있다.
- 내장형 데이터베이스를 사용하는 데이터 소스
  - 사전에 데이터베이스를 사용하지 않는 프로토타입을 만들 때 많이 활용된다.
  - 애플리케이션을 기동할 때마다 새로 데이터베이스가 구축된다.
  - DDL(Data Definition Language)와 DML(Data Manipulation Language)를 준비해야 한다.

### 데이터 소스 설정
- pom.xml파일에 spring-jdbc 를 정의한다.
```java
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-jdbc</artifactId>
</dependency>
```

## 3.2 스프링 JDBC

### JDBC
- 스프링 프레임워크에서 제공하는 데이터 접근 방법
- 데이터 접근에 관한 반복 처리를 개발자가 직접 구현하지 않고 프레임워크가 대행한다.
  (커넥션의 연결과 종료, SQL문의 실행, 실행 결과 반복 처리, 예외 처리)
- 이외의 것들은 개발자가 구현한다.
  (SQL문 정의, 파라미터 설정, 실행 결과 각 레코드별로 필요한 처리)

### JdbcTemplete클래스를 활용한 CRUD
- JDBC에는 JdbcTemplate 클래스와 NamedParameterJdbcTemplate 클래스를 제공한다.
- JdbcTemplate 바인딩 문자의 플레이스 홀더 : '?'
- NamedParameterJdbcTemplate 바인딩 문자의 플레이스 홀더 : ':바인드 변수명'
```java
// JdbcTemplate
String sql = "SELECT user_name FROM user WHERE user_id = ?";
// NamedParameterJdbcTemplate
String sql = "SELECT user_name FROM user WHERE user_id = :roomId";
```

### 주요 메서드
- queryForObject : 하나의 결과 레코드 중에서 하나의 컬럼 값을 가져올 때 사용한다.
- queryForMap : 하나의 결과 레코드 정보를 Map 형태로 맵핑할 수 있다.
- queryForList : 여러개의 레코드를 다룰 수 있다.
- query
- update : 테이블 내용을 변경할 때 사용한다. (등록, 수정, 삭제에 관계없음)

### DAO (Data Access Object)
- 데이터베이스에 접근해서 데이터를 다루는 클래스

### 조회 결과 형변환
- JdbcTemplate 으로 결과값을 담아올 땐 Map 형태로 받게 된다.
  그래서 값을 꺼낼 땐 형변환이 필요하다.
- 조회 결과 값이 1인 경우 : Map<T, T>
- 조회 결과 값이 여러개인 경우 : List<Map<T, T>>
- 조회 결과 값이 0인 경우 : EmptyResultdataAccessException 예외가 발생한다.
- 테이블 내용을 변경하는 경우 : update메서드를 사용한다. 변경된 처리 건수가 반환된다.

### SQL 질의 결과를 POJO로 변환
- POJO (Plain Old Java Object) 안좋은 의미 아닌가..?

### 반환된 결과 형태 바꿔주는 인터페이스
- RowMapper
  - 결과값을 순차적으로 읽고 첫번째 행에 원하는 POJO형태로 설정해서 바꿔주면
    다음 행부터는 스프링 프레임워크가 대신 바꿔준다.
- ResultSetExtractor
  - 여러 행에서 필요한 값을 꺼내서 하나의 POJO 객체에 채워넣을 수 있다...? (잘모르겠음)
- RowCallbackHandler
  - 반환값이 없다.
  - 처리 결과를 반환하지 않고, 다른 처리를 할 때 사용한다.
  - 읽은 데이터를 파일 형태로 출력하거나 조회된 데이터를 검증할 때)

## 3.3 트랜잭션 관리

트랜잭션? 쿼리를 실수로 수정하거나 삭제했을 때를 대비해 보험으로 걸어두는 것.
Spring 에서는 rollback, commit 을 알아서 해주는

- PlatformTransactionManager : 스프링 트랜잭션 처리의 중심이 되는 관리자
- 위의 빈을 정의하고, 트랜잭션을 관리해야 하는 메서드를 정의하면 사용할 수 있다.

### 글로벌 트랜잭션
- 여러 데이터 베이스를 사용하지만, 각각의 조작을 수행하고 하나의 트랜잭션으로 묶어
  한번에 성공하거나 실패하는 것으로 처리해야 하는 경우엔 글로벌 트랜잭션을 이용한다.
- 다음을 지정하면 JtaTransactionManager 를 빈 형태로 사용할 수 있다.
```java
<tx:jta-transaction-manager />
```

### 선언적 트랜잭션
- 미리 선언된 룰에 따라 트랜잭션을 제어한다.
- 트랜잭션의 시작과 커밋, 콜백 등 처리를 비지니스 로직에 기술할 필요가 없다.
- @Transactional 애너테이션을 사용한다. 클래스와 메서드에 부여할 수 있다.
- xml 에서 선언적 트랜잭션을 이용하려면 `tx`로 시작하는 트랜잭션 전용 xml 스키마를 사용한다.

### 명시적 트랜잭션
- 트랜잭션 처리를 직접 소스코드에 기술하는 방법이다.

### 데이터 접근 시의 예외 처리
- 스프링 프레임워크에서는 예외 처리를 쉽게 하기 위해 데이터 접근과 관련한 모든 예외가
  DataAccessException 을 부모 클래스로 하는 계층 구조로 만들어져 있다.
- 원하는 에러 형식을 try ~ cath 문에 적어서 처리해주면 된다. (p.174)
- 데이터베이스 오류 코드와 데이터 접근 예외에 관련된 내용은 spring-jdbc-xxx.jar 에 포함된
  sql-error-codes.xml을 사용한다. 클래스 바로 아래에 sql-error-codes.xml 을 두고
  내용을 재정의하면 기본 설정된 내용을 커스터마이징 할 수 있다.
