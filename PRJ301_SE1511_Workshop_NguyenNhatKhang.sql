create database PRJ301_SE1511_Workshop_NguyenNhatKhang
use PRJ301_SE1511_Workshop_NguyenNhatKhang
create table tbl_Mobile
(
mobileId varchar(10) primary key,
descriptions varchar(250) not null,
price float,
mobileName varchar(20) not null,
yearOfProduction int,
quantity int,
notSale bit
)
create table tbl_User
(
userId varchar(20) primary key,
password int not null,
fullName varchar(50) not null,
role int 
)
create table tbl_CheckOut
(
mobileId varchar(10) foreign key references tbl_Mobile,
mobileName varchar(20) not null,
price float,
quantity int 
)


insert into tbl_User values ('khang','2582000','Nguyen Nhat Khang',0)
insert into tbl_User  values ('dung','20092000','Le Quoc Dung',1)
insert into tbl_User  values ('Dat','2462000','Le Quoc Dat',2)
insert into tbl_User  values ('Hoang','2482000','Nguyen The Hoang',0)
insert into tbl_User  values ('Chau','123456','Nguyen Minh Chau',1)


insert into tbl_Mobile values ('01','Pin trau',2000000,'Samsung S7',2015,1,0)
insert into tbl_Mobile values ('02','Do ben cao',3000000,'Samsung A50',2016,2,1)
insert into tbl_Mobile values ('03','Hieu nang cao',4000000,'Oppo Reno 5',2017,3,0)
insert into tbl_Mobile values ('04','Ram nhieu',5000000,'Oppo A93',2018,1,1)
insert into tbl_Mobile values ('05','Bo nho trong nhieu',6000000,'ViVo Y51',2019,4,0)
insert into tbl_Mobile values ('06','Camera sac net',2000000,'ViVo V21 5G',2020,1,1)
insert into tbl_Mobile values ('07','Thiet ke dep',3000000,'Xiaomi Mi 11 5G',2014,2,0)
insert into tbl_Mobile values ('08','Man hinh rong',4000000,'Xiaomi Redmi 9T',2016,3,1)
insert into tbl_Mobile values ('09','Card do hoa cao',5000000,'Vsmart Aris Pro',2017,4,0)
insert into tbl_Mobile values ('10','Gia re',6000000,'Vsmart Aris',2018,5,1)
insert into tbl_Mobile values ('11','Thiet ke dep',7000000,'Iphone X',2019,6,0)
insert into tbl_Mobile values ('12','Mau ma dep',8000000,'Iphone Xr',2020,2,1)
insert into tbl_Mobile values ('13','Nhe gon',9000000,'Iphone 11',2020,3,1)
