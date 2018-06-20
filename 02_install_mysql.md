# *Not finished!*
## mysql 설정하기

mysql과 연결할 수 있도록 jdbc 를 설정해두어야 한다.
myBatis 는?!

데이터 베이스 연동하기
1. DB라이브러리 가져오기
myBatis 라이브러리 mybatis
Spring 과 myBatis 를 연결하는 라이브러리 mybatis-spring
Spring 의 jdbc 라이브러리 spring-jdbc
Spring과 myBatis의 접속을 확인하기 위한 Spring junit test spring-test

pom.xml 에 DB라이브러리를 설정하면 
자동으로 Maven dependencies 에 해당 라이브러리가 설치된다.

2. aop, context, jdbc, mybatis-spring 네임스페이스 추가

pom.xml 에 라이브러리를 추가하면 네임스페이스가 root-context.xml 의 Namespaces 항목에
자동으로 보이게 되며, 간단히 클릭 후 파일을 저장하면 된다.

그럼 root-context.xml 에 자동으로 네임스페이스가 추가된 걸 볼 수 있다.
### root-context.xml 위치
src/main/webapp/WEB-INF/spring/appServlet/root-context.xml 에 있다.

Preference > Spring > Beans Support > Namespaces > root-context.xml

database 를 연결하는 
dataSource 는 보통 root-context.xml 에 작성한다.

https://blog.hanumoka.net/2018/04/29/spring-20180429-spring-mybatis-mysql-setting/



mac 에 mysql 을 설치하는 법이 여기 아주 자세~하게 나와있다. http://palpit.tistory.com/871
아직 임시 비밀번호를 변경하진 않은 상태임.

http://wjheo.tistory.com/entry/Spring-MySQL-연동-테스트-매뉴얼
mysql sts 에 연동하는 중인데 여기 링크에서 이걸 알게 됐다.
- src/main/java : 개발되는 java 코드의 경로
- src/main/resources : 서버가 실행될 때 필요한 파일들의 경로
- src/test/java : 테스트 전용 경로( 각 테스트 코드 작성 경로)
- src/test/resources : 테스트 시에만 사용되는 파일들 경로
- spring : Spring 설정 파일의 경로
- views : JSP 파일의 경로
- pom.xml :Maven의 설정 파일


출처: http://wjheo.tistory.com/entry/Spring-MySQL-연동-테스트-매뉴얼 [IT world]

터미널 열고
설치된 폴더에 접근한다.
1) 
$ cd /usr/local/mysql/bin

2) mysql 실행한다
$ sudo ./mysql

3) 처음엔 비밀번호를 넣으라고 하는데, 해당 비밀번호는 mysql 임시비밀번호가 아닌, 나의 맥북의 패스워드를 입력하면 됩니다.

http://aspdotnet.tistory.com/1848
https://m.blog.naver.com/PostView.nhn?blogId=madplay&logNo=220679456479&proxyReferer=https%3A%2F%2Fwww.google.com%2F
http://emfls.tistory.com/entry/MySQL-root-비밀번호초기암호-분실-재설정-for-Mac

sudo /usr/local/mysql/bin/mysqld_safe --skip-grant-tables

구버전
UPDATE user SET password=password('1234') WHERE user='root';

에러 나면
update user set authentication_string=password('1234') where user='root';
update user set authentication_string=PASSWORD('Asdfghjkl123!') where user='root';

password() 메소드가 사라졌다....
ALTER USER 'root'@'localhost' IDENTIFIED BY '123456';

리스타트
sudo /usr/local/mysql/support-files/mysql.server restart

에러 첨으로 돌아옴 ㅠ.ㅠ

- $ sudo ./mysql -u root

**Error**
ERROR 1045 (28000): Access denied for user 'root'@'localhost' (using password: NO)

**Cuase**
패스워드 안쓰고 접근하려해서 거부당함

**Solution**
패스워드를 써주자.
$ sudo ./mysql -pP@pwd123456

---

**Error**
ERROR 1045 (28000): Access denied for user 'root'@'localhost' (using password: YES)

**Cuase**
패스워드 틀려서 거부당함

**Solution**
패스워드 까먹은 상황 -> admin 인 root user 에 새로운 패스워드를 설정 한다.
1) mysqld 중지
service mysqld stop --> 안됨
$ sudo /usr/local/mysql/support-files/mysql.server stop

2) 인증 생략 옵션 + 안전 모드로 데몬 실행 (패스워드 없이 mysql에 접속할 수 있다.)
$ sudo /usr/local/mysql/bin/mysqld_safe --skip-grant &
[2] 14348
[1]   Done                    /usr/local/mysql/bin/mysqld_safe --skip-grant

$ sudo /usr/local/mysql/bin/mysqld_safe --skip-grant-tables &
[3] 14396
[2]+  Stopped                 sudo /usr/local/mysql/bin/mysqld_safe --skip-grant

3) mysql 콘솔로 들어가서 (mysql 접속이 안됨 ㅠㅠㅠ)
$ /usr/bin/mysql -u root mysql --> 안됨
$ sudo /usr/local/mysql/bin/mysql -u root mysql

4) 새 패스워드 지정


--- 
https://stackoverflow.com/questions/2995054/access-denied-for-user-rootlocalhost-using-passwordno

Stop the service/daemon of mysql running
[root ~]# service mysql stop   
sudo /usr/local/mysql/support-files/mysql.server stop

- mysql stop/waiting
Start mysql without any privileges using the following option; 
This option is used to boot up and do not use the privilege system of MySQL.
[root ~]# mysqld_safe --skip-grant-tables &
sudo /usr/local/mysql/bin/mysqld_safe --skip-grant-tables &

> 2018-06-06T04:25:59.6NZ mysqld_safe Logging to '/usr/local/mysql/data/ChaeSoojungui-MacBook-Pro.local.err'.
> 2018-06-06T04:26:00.6NZ mysqld_safe Starting mysqld daemon with databases from /usr/local/mysql/data


- 새로운 터미널을 열고 다음을 입력
[root ~]# mysql -u root
sudo ./mysql -u root
mysql> 

root 사용자에 대해 permission 을 수정하자.
mysql> use mysql;
Database changed
mysql> select * from  user;
Empty set (0.00 sec)
mysql> truncate table user;
Query OK, 0 rows affected (0.00 sec)
mysql> flush privileges;
Query OK, 0 rows affected (0.01 sec)
mysql> grant all privileges on *.* to root@localhost identified by 'YourNewPassword' with grant option;
grant all privileges on *.* to root@localhost identified with 'Asdfghjkl123!' with grant option;
grant all privileges on *.* to root@localhost identified with sha256_password by 'Asdfghjkl123!' with grant option;

Query OK, 0 rows affected (0.01 sec)
*if you don`t want any password or rather an empty password

    mysql> grant all privileges on *.* to root@localhost identified by '' with grant option;
    Query OK, 0 rows affected (0.01 sec)*
    mysql> flush privileges;
    Query OK, 0 rows affected (0.00 sec)
Confirm the results:

    mysql> select host, user from user;
+-----------+------+
| host      | user |
+-----------+------+
| localhost | root |
+-----------+------+
1 row in set (0.00 sec)
Exit the shell and restart mysql in normal mode.
mysql> quit;
[root ~]# kill -KILL [PID of mysqld_safe]
[root ~]# kill -KILL [PID of mysqld]
[root ~]# service mysql start
Now you can successfully login as root user with the password you set
 [root ~]# mysql -u root -pYourNewPassword 
 mysql> 

select authentication_string, user from user 


You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near '('password') WHERE user='root'' at line 1


UPDATE mysql.user SET password=password("elephant7") where user="root"

1) 최상위 경로로 이동
cd ../..

2) mysql 설치 폴더 이동


-- 과제

[o] 워크밴치 설치해오기~
[x] 메이븐 리파지토리 연동해서 mysql연동
[x] 스프링에 텍스트만 찍는거 해오기 (웹화면)

