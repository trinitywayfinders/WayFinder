CREATE DATABASE IF NOT EXISTS wayfinder;
CREATE USER 'root'@'%' IDENTIFIED BY 'wayfinders';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
