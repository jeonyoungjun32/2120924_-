drop TABLE dog;
drop SEQUENCE dog_seq;

/*
(예) 비교를 위해 "임초롱초롱빛나리"를 name1, name2에 저장하면
1. name1 VARCHAR2(20) : 길이의 의미가 바이트 개수 
  ▶ 한글8자리*3=24=24Bytes이므로 길이 초과로 저장할 수 없다.
2. name2 NVARCHAR2(20)  : 길이의 의미가 문자 개수
  ▶ 한글 8자리를 저장하고 추가로 12자리를 더 저장할 수 있다.
*/

CREATE TABLE dog(
	id NUMBER PRIMARY KEY,--상품아이디(Mysql에서는 auto_increment로 자동 1증가)
	kind NVARCHAR2(20) not null,--개품종
	country NVARCHAR2(20) not null,--원산지
	price NUMBER not null,--개 가격	
	height NUMBER,--평균 개 신장
	weight NUMBER,--평균 개 체중
	content NVARCHAR2(400),--개 설명
	image NVARCHAR2(40) not null,--개 이미지
	readcount NUMBER--조회수
);

CREATE SEQUENCE dog_seq;--시작 1, 증가 1

INSERT INTO dog values(dog_seq.nextval,'푸들','프랑스',1000,1,20,'털많다.','pu.jpg',0);
INSERT INTO dog values(dog_seq.nextval,'불독','독일',2000,1,20,'못생겼다.','bul.jpg',0);
INSERT INTO dog values(dog_seq.nextval,'진도개','대한민국',3000,1,20,'최고다.','jin.jpg',0);
INSERT INTO dog values(dog_seq.nextval,'허스키','북극',4000,1,20,'멋지다.','h.jpg',0);

commit --주의 ;넣으면 오류

select * from dog;
------------------------------------------------------------------
--Mysql
/*
새로 데이터를 insert할때마다 max(num)으로 기존에 추가되어있는 
num의 최대값을 알아야 p.k값이 겹치지 않게 추가를 할 수있다. 
하지만 insert마다 이렇게 num의 최대값을 받아오는 sql문을 작성하는것은 
비효율적이므로 num에 Auto_Increment 속성으로 
insert문을 보낼때 마다 자동으로 num값이 증가되게 저장할 수 있다.*/

/*
CREATE TABLE dog(
	id int auto_increment PRIMARY KEY,--상품아이디(Mysql에서는 auto_increment로 자동 1증가)
	kind NVARCHAR(20) not null,--개품종
	country NVARCHAR(20) not null,--원산지
	price int not null,--개 가격	
	height int,--평균 개 신장
	weight int,--평균 개 체중
	content NVARCHAR(400),--개 설명
	image NVARCHAR(40) not null,--개 이미지
	readcount int--조회수
);

INSERT INTO dog(kind,country,price,height,weight,content,image,readcount) 
values('푸들','프랑스',1000,1,20,'털많다.','pu.jpg',0);

INSERT INTO dog(kind,country,price,height,weight,content,image,readcount) 
values('불독','독일',2000,1,20,'못생겼다.','bul.jpg',0);

INSERT INTO dog(kind,country,price,height,weight,content,image,readcount) 
values('진도개','대한민국',3000,1,20,'최고다.','jin.jpg',0);

INSERT INTO dog(kind,country,price,height,weight,content,image,readcount) 
values('허스키','북극',4000,1,20,'멋지다.','h.jpg',0);

--이미 생성한 테이블 칼럼에 자동증가 옵션을 추가하고 싶을 때
ALTER TABLE TEST MODIFY 'num' int NOT NULL auto_increment;
--자동 속성값을 특정값으로 초기화할 때
ALTER TABLE TEST auto_increment=2;--2부터 시작

*/