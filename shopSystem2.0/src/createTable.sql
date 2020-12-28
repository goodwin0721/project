#新建数据库shopSystem
#CREATE DATABASE IF NOT EXISTS db_shopSystem DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
#DROP DATABASE db_shopsystem;
#use  db_shopsystem;
#建表
#CREATE TABLE IF NOT EXISTS name(col_name,col_type)DEFAULT CHARSET=utf8mb4;
#用户表
DROP TABLE
IF
	EXISTS t_user;
CREATE TABLE
IF
	NOT EXISTS t_user (
	id INT NOT NULL PRIMARY KEY auto_increment,
	username VARCHAR ( 18 ) NOT NULL,
	PASSWORD VARCHAR ( 18 ) NOT NULL,
	birthdate VARCHAR ( 10 ) NOT NULL,
	gender INT #(0男，1女)
	
	) DEFAULT CHARSET = utf8mb4;
#管理员表
DROP TABLE
IF
	EXISTS t_manager;
CREATE TABLE
IF
	NOT EXISTS t_manager ( id INT NOT NULL PRIMARY KEY auto_increment, userId INT NOT NULL, #将管理员信息存入用户表，并把管理员ID存入管理员表
		FOREIGN KEY ( userId ) REFERENCES t_user ( id ) #外键
	) DEFAULT CHARSET = utf8mb4;
#商品表
DROP TABLE
IF
	EXISTS t_goods;
CREATE TABLE
IF
	NOT EXISTS t_goods (
		id INT NOT NULL PRIMARY KEY,
		NAME VARCHAR ( 10 ) NOT NULL,
		described VARCHAR ( 50 ),
		price INT NOT NULL,
		stock INT NOT NULL,
		pictureUrl VARCHAR ( 30 ) NOT NULL #商品图片地址
		
	) DEFAULT CHARSET = utf8mb4;
#加一列已售
ALTER TABLE t_goods ADD sold INT NOT NULL;
#商品分类
DROP TABLE
IF
	EXISTS t_goodsClassify;
CREATE TABLE
IF
	NOT EXISTS t_goodsClassify ( id INT NOT NULL PRIMARY KEY auto_increment, classify VARCHAR ( 10 ) NOT NULL, #商品分类名称
		sum INT NOT NULL #该分类下的商品总数
	) DEFAULT CHARSET = utf8mb4;
#购物车
DROP TABLE
IF
	EXISTS t_cart;
CREATE TABLE
IF
	NOT EXISTS t_cart (
		id INT NOT NULL PRIMARY KEY auto_increment,
		userId INT NOT NULL,
#用户ID
		goodsId INT NOT NULL,
#商品ID
		amount INT NOT NULL,
#加入购物车商品数量
		INDEX index_userId ( userId ),
#索引
		FOREIGN KEY ( userId ) REFERENCES t_user ( id ),
#外键
		FOREIGN KEY ( goodsId ) REFERENCES t_goods ( id ) #外键
		
	) DEFAULT CHARSET = utf8mb4;
#订单表
DROP TABLE
IF
	EXISTS t_indent;
CREATE TABLE
IF
	NOT EXISTS t_indent (
		id INT NOT NULL PRIMARY KEY auto_increment,
		userId INT NOT NULL,
#用户ID
		goodsId INT NOT NULL,
#商品ID
		price INT NOT NULL,
#商品价格，由于商品更新价格有可能会改变，所以单独存储
		amount INT NOT NULL,
#购买商品数量
		tradeTime VARCHAR ( 10 ) NOT NULL,
#交易时间
		INDEX index_userId ( userId ),
#索引
		FOREIGN KEY ( userId ) REFERENCES t_user ( id ),
#外键,
		FOREIGN KEY ( goodsId ) REFERENCES t_goods ( id ) #外键
	
) DEFAULT CHARSET = utf8mb4;