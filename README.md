# JSP_Lecture_9

<pre>

create table employees(
id varchar2(10) not null,
pass varchar2(10) not null,
name varchar2(24),
lev varchar2(1) default 'A',
enter date default sysdate,
gender char(1) default '1',
phone varchar2(30),
primary key(id)
);

select * from employees;

commit;



insert into employees values('h01','1111','hong1',default,default,default,'010-1111-2222');
insert into employees values('h02','2222','hong2','B',default,'2','010-2222-3333');
insert into employees values('h03','3333','hong3','B',default,default,'010-3333-4444');


select * from employees;

drop table Board;

create table Board(
id varchar2(10) not null,
pwd varchar2(10) not null,
name varchar2(24),
email varchar2(30),
phone varchar2(30),
gender char(10) default '1',
primary key(id)
);



commit;





</pre>
