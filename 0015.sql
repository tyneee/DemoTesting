create database LabCar
use LabCar

create table tblRegistration(
email varchar(50) primary key,
phone varchar(20),
fullName nvarchar(20),
address nvarchar(50),
dateOfCreate datetime,
password varchar(300),
role nvarchar(20),
status varchar(15)
)



create table tblCategory(
categoryID int identity(1,1) primary key,
categoryName nvarchar(50)
)

create table tblCar(
carID int identity(1,1) primary key,
carName nvarchar(50),
color varchar(20),
yearOfCreate int,
categoryID  int FOREIGN KEY REFERENCES tblCategory(categoryID),
price float,
quantityOfRemain int,
image varchar(500),
description varchar(100),
status varchar(20)

)


create table tblDiscount(
discountID varchar(10) primary key,
percentOfDiscount int,
dateFrom date,
dateTo date
)

create table tblOder(
billID int identity(1,1) PRIMARY KEY,
totalPrice float,
email varchar(50) FOREIGN KEY REFERENCES tblRegistration(email),
discountID varchar(10) FOREIGN KEY REFERENCES tblDiscount(discountID),
oderDate DATETIME,
customerAdress nvarchar(150),
customerPhone varchar(20),
status varchar(10)
)



create table tblOrderDetail(
billID int  FOREIGN KEY REFERENCES tblOder(billID),
carID int FOREIGN KEY REFERENCES tblCar(carID),
quantity int,
price float,
pickUpDate date,
returnDate date,
PRIMARY KEY(billID,carID,pickUpDate,returnDate)
)

create table tblFeedBack(
id int identity(1,1) primary key,
email varchar(50) foreign key references tblRegistration(email),
carID int foreign key references tblCar(carID),
billID int foreign key references tblOder(billID),
rate int
)

-----------------Insert to car--------------------------
Insert into tblCategory values('Mess')
Insert into tblCategory values('BWC')

-----------------Insert to car--------------------------
insert into tblCar values ('Hyundai Accent', 'White', 2017, 1, 5.5,20,'https://specials-images.forbesimg.com/imageserve/5d3703e2f1176b00089761a6/960x0.jpg?cropX1=836&cropX2=5396&cropY1=799&cropY2=3364', 'qqwqwqqe', 'Active')
go
insert into tblCar values ('Kia Rio', 'Black', 2016, 1, 5.5,20,'https://stat.overdrive.in/wp-content/odgallery/2020/06/57263_2020_Mercedes_Benz_GLS.jpg', 'qqwqwqqe', 'Active')
go
insert into tblCar values ('Mitsubishi Mirage', 'Yellow', 2017,1, 5.5,20,'https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/gallery_slide/public/images/car-reviews/first-drives/legacy/98_-_fastest_accelerating_road_cars_-_ferrari_f8_tributo.jpg?itok=cK2YYgq2', 'qqwqwqqe', 'Active')
go
insert into tblCar values ('Hyundai Sonata', 'Blue', 2017, 1, 5.5,20,'https://i.ytimg.com/vi/7PXLPzcIydw/maxresdefault.jpg', 'qqwqwqqe', 'Active')
go
insert into tblCar values ('Chevrolet Malibu', 'White', 2016, 1, 5.5,20,'https://imgd.aeplcdn.com/0x0/n/cw/ec/20865/amg-gt-exterior-right-front-three-quarter-60800.jpeg', 'qqwqwqqe', 'Active')
go
insert into tblCar values ('Kia Optima', 'White', 2011, 1, 5.5,20,'https://specials-images.forbesimg.com/imageserve/5d3703e2f1176b00089761a6/960x0.jpg?cropX1=836&cropX2=5396&cropY1=799&cropY2=3364', 'qqwqwqqe', 'Active')
go
insert into tblCar values ('Toyata 4Runner', 'White', 2017, 1, 5.5,20,'https://cars.usnews.com/pics/size/640x420/static/images/article/202006/128503/216702_New_Volvo_XC40_-_exterior_640x420.jpg', 'qqwqwqqe', 'Active')
go
insert into tblCar values ('Buick Enclave', 'Black', 2016, 1, 5.5,20,'https://specials-images.forbesimg.com/imageserve/5d3703e2f1176b00089761a6/960x0.jpg?cropX1=836&cropX2=5396&cropY1=799&cropY2=3364', 'qqwqwqqe', 'Active')
go
insert into tblCar values ('Chevrolet Traverse', 'Blue', 2016, 1, 5.5,20,'https://www.bugatti.com/fileadmin/_processed_/sei/p1/se-image-85e0e9ab23134961c88e4ecea2bff53f.jpg', 'qqwqwqqe', 'Active')




  