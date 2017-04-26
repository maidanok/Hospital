CREATE USER 'Admin'@'localhost' IDENTIFIED BY 'Admin';
GRANT SELECT,INSERT,UPDATE,DELETE,EXECUTE ON hospital.* TO 'hospitalAdmin'@'localhost';
FLUSH PRIVILEGES;