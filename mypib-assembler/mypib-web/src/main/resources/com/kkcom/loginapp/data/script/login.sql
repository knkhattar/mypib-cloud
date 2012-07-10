
CREATE TABLE LOGINDETAILS(USERID VARCHAR(255) PRIMARY KEY,   PASSWORD VARCHAR(255));
INSERT INTO LOGINDETAILS (USERID, PASSWORD) VALUES('testid', 'testpass');

-- DB: mypibdb, tables: USER_CREDENTIALS(userid int pk, password), ACCOUNT(userid int , accountno, accounttype, accountbal), CONTACT_DETAILS(userid int, 
-- mobile no, email, address)


CREATE TABLE `users` (
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
)

CREATE TABLE `user_roles` (
  `USER_ROLE_ID` INT(10) UNSIGNED NOT NULL,
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `AUTHORITY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
) 


INSERT INTO users (USER_ID, USERNAME,PASSWORD, ENABLED)
VALUES (100, 'testid', 'testpass', TRUE);
 
INSERT INTO user_roles (USER_ROLE_ID, USER_ID,AUTHORITY)
VALUES (1, 100, 'ROLE_USER');