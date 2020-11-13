Spring boot oracle project 

1. Go to directory 
λ C:\Users\Admin\.m2\repository\com\oracle\ojdbc8\12.2.0.1

2. excute command 
λ mvn install:install-file -Dfile=ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar

3. add dependency
<dependency>
	<groupId>com.oracle</groupId>
	<artifactId>ojdbc8</artifactId>
	<version>12.2.0.1</version>
</dependency>

4. Create Table : Book
CREATE TABLE BOOK
(
     ID NUMBER(5) PRIMARY KEY,
     TITLE VARCHAR(50),
     PAGES NUMBER(5),
     INSTOCK CHAR(1)
);
INSERT INTO BOOK VALUES(1,'Game of Thrones',200);
INSERT INTO BOOK VALUES(2,'Facebook Developers Track',178);
INSERT INTO BOOK VALUES(3,'Cracking the Coding Interview',540);
INSERT INTO BOOK VALUES(4,'Google Map',1059);
INSERT INTO BOOK VALUES(5,'Google Interview Tips',435);
INSERT INTO BOOK VALUES(6,'Spring Boot & Security Projects',758);
INSERT INTO BOOK VALUES(7,'Developer Road Map',452);
INSERT INTO BOOK VALUES(8,'UPSC Exam Tips',178);
INSERT INTO BOOK VALUES(9,'Current Affairs',49);
INSERT INTO BOOK VALUES(10,'Secret of Life',245);
INSERT INTO BOOK VALUES(11,'Motivational Gurus',245);
INSERT INTO BOOK VALUES(12,'Love of life',143);
INSERT INTO BOOK VALUES(13,'Covid-19',2020);
INSERT INTO BOOK VALUES(14,'End of Life',1745);
INSERT INTO BOOK VALUES(15,'Full Stack Developer',245);

SELECT * FROM BOOK;

