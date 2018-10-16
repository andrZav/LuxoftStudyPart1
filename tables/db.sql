CREATE DATABASE IF NOT EXISTS file_statistics DEFAULT CHARACTER SET utf8;

USE file_statistics;

CREATE TABLE IF NOT EXISTS files (
	file_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	dateTime Timestamp default '2018-01-01 00:00:00',
    fileName VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS statistics (
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	longest_word VARCHAR(250),
	shortest_word VARCHAR(250),
	line_length INT,
	aver_words_length INT,
    file_id INT, FOREIGN KEY (file_id) REFERENCES files (file_id)
);