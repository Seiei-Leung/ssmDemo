SET NAMES utf8mb4;

DROP TABLE IF EXISTS studentmsg;

CREATE TABLE studentmsg (
	id INTEGER NOT NULL AUTO_INCREMENT,
	name VARCHAR(10), -- 无论英文，中文都只能十个
	age INTEGER,
	sex VARCHAR(10),
	testInt int(10), -- int(4)，即有4个字节，1个字节是8个二进制串，所以4个字节就是32个二进制串，即可表示的最大数是 2^32-1 = 2147483647
	testTinyint tinyint(1), -- -128~127
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO studentmsg (name, age, sex) VALUES ('seiei', 18, 'boy');
INSERT INTO studentmsg (name, age, sex) VALUES ('taka', 25, 'boy');