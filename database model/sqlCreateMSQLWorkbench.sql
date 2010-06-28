SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `api`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `api` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `api` (
  `intClientid` INT(11) NOT NULL ,
  `intClientkey` INT(11) NOT NULL ,
  PRIMARY KEY (`intClientid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `bookmark`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookmark` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `bookmark` (
  `intBookmarkid` INT(11) NOT NULL ,
  `intPachumeid` INT(11) NOT NULL ,
  `intUserid` INT(11) NOT NULL ,
  PRIMARY KEY (`intBookmarkid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `channel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `channel` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `channel` (
  `intChannelid` INT(11) NOT NULL ,
  `intUserid` INT(11) NOT NULL ,
  `boolIsprivate` TINYINT(1) NOT NULL ,
  `intSecuritycode` INT(11) NOT NULL ,
  `strChannelname` TEXT NOT NULL ,
  `strChanneldescription` TEXT NOT NULL ,
  `strAvatarlocation` TEXT NOT NULL ,
  `boolBackgroundimagerepeat` TINYINT(1) NOT NULL ,
  `strBackgroundimagecolor` TEXT NOT NULL ,
  `strBackgroundimagelocation` TEXT NOT NULL ,
  PRIMARY KEY (`intChannelid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `comment` (
  `commentId` INT(11) NOT NULL ,
  `pachumeId` INT(11) ZEROFILL NOT NULL ,
  `userId` INT(11) NOT NULL ,
  `dateSecond` INT(11) NOT NULL ,
  `dateHour` INT(11) NOT NULL ,
  `dateDay` INT(11) NOT NULL ,
  `dateMonth` INT(11) NOT NULL ,
  `dateYear` INT(11) NOT NULL ,
  `dateYearDay` INT(11) NOT NULL ,
  `channelId` INT(11) NOT NULL ,
  `groupId` INT NOT NULL ,
  PRIMARY KEY (`commentId`) ,
  CONSTRAINT `fk_{58B87E3B-144A-4CAC-8C88-9740E15D69B1}`
    FOREIGN KEY (`pachumeId` )
    REFERENCES `mydb`.`pachume` (`pachumeId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contact` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `contact` (
  `contactId` INT(11) ZEROFILL NOT NULL AUTO_INCREMENT ,
  `userId` INT(11) NOT NULL ,
  `friendId` INT(11) NOT NULL ,
  PRIMARY KEY (`contactId`) ,
  CONSTRAINT `fk_{76A847BD-D7E8-45C4-BC26-A71E1EDB5F18}`
    FOREIGN KEY (`userId` )
    REFERENCES `mydb`.`user` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `member` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `member` (
  `intMemberid` INT(11) NOT NULL ,
  `intChannelid` INT(11) NOT NULL ,
  `intUserid` INT(11) NOT NULL ,
  `intSecuritycode` INT(11) NOT NULL ,
  `boolIsmember` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`intMemberid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pachume`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pachume` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `pachume` (
  `pachumeId` INT(11) ZEROFILL NOT NULL AUTO_INCREMENT ,
  `userId` INT(11) NOT NULL ,
  `pachume` TEXT NOT NULL ,
  `viewCount` INT(11) NOT NULL DEFAULT -1 ,
  `dateSecond` INT(11) NOT NULL DEFAULT -1 ,
  `dateMinute` INT(11) NOT NULL DEFAULT -1 ,
  `dateHour` INT(11) NOT NULL DEFAULT -1 ,
  `dateDay` INT(11) NOT NULL DEFAULT -1 ,
  `dateMonth` INT(11) NOT NULL DEFAULT -1 ,
  `dateYear` INT(11) NOT NULL DEFAULT -1 ,
  `dateYearDay` INT(11) NOT NULL DEFAULT -1 ,
  `tags` TEXT NULL ,
  `channelId` INT(11) NOT NULL DEFAULT -1 ,
  `privatized` BOOLEAN NOT NULL DEFAULT false ,
  `role` TEXT NULL ,
  `location` TEXT NULL ,
  `spamCount` INT NOT NULL DEFAULT 0 ,
  `groupId` INT UNSIGNED NULL ,
  PRIMARY KEY (`pachumeId`) ,
  CONSTRAINT `fk_{1EF061C9-46F8-4835-8631-1241465F8DE0}`
    FOREIGN KEY (`userId` )
    REFERENCES `mydb`.`user` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `spam`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spam` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `spam` (
  `spamId` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `pachumeId` INT(11) ZEROFILL NOT NULL ,
  `userId` INT(11) NOT NULL ,
  PRIMARY KEY (`spamId`) ,
  CONSTRAINT `fk_{7D792BE9-6269-4FD9-A7E7-A6E027F4F9C4}`
    FOREIGN KEY (`userId` )
    REFERENCES `mydb`.`user` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_{1FCB9784-AFBC-44CA-9A71-D55CEC305288}`
    FOREIGN KEY (`pachumeId` )
    REFERENCES `mydb`.`pachume` (`pachumeId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `stream`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stream` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `stream` (
  `intStreamid` INT(11) NOT NULL ,
  `intPachumeid` INT(11) NOT NULL ,
  `intCommentid` INT(11) NOT NULL ,
  `intUserid` INT(11) NOT NULL ,
  PRIMARY KEY (`intStreamid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tag` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `tag` (
  `tagId` INT(11) ZEROFILL NOT NULL AUTO_INCREMENT ,
  `tag` TEXT NOT NULL ,
  `pachumeId` INT(11) ZEROFILL NOT NULL ,
  PRIMARY KEY (`tagId`) ,
  CONSTRAINT `fk_{BADE6622-E232-4F90-BF0F-68965EBE8A52}`
    FOREIGN KEY (`pachumeId` )
    REFERENCES `mydb`.`pachume` (`pachumeId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `user` (
  `userId` INT(11) NOT NULL AUTO_INCREMENT ,
  `screenName` TEXT NOT NULL ,
  `firstName` TEXT NOT NULL ,
  `lastName` TEXT NOT NULL ,
  `userPassword` TEXT NOT NULL ,
  `email` TEXT NOT NULL ,
  `active` BOOLEAN NOT NULL DEFAULT false ,
  `activationCode` INT(11) NOT NULL DEFAULT -1 ,
  `notifiable` BOOLEAN NOT NULL DEFAULT false ,
  `privatized` BOOLEAN NOT NULL DEFAULT false ,
  `loggedIn` BOOLEAN NOT NULL DEFAULT false ,
  `role` TEXT NULL ,
  `backgroundColor` TEXT NULL ,
  `avatarLocation` TEXT NULL ,
  `backgroundImageRepeat` TEXT NULL ,
  `location` TEXT NULL ,
  `feedLocation` TEXT NULL ,
  `college` TEXT NULL ,
  `backgroundImageLocation` TEXT NULL ,
  PRIMARY KEY (`userId`) )
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `user` (
  `userId` INT(11) NOT NULL AUTO_INCREMENT ,
  `screenName` TEXT NOT NULL ,
  `firstName` TEXT NOT NULL ,
  `lastName` TEXT NOT NULL ,
  `userPassword` TEXT NOT NULL ,
  `email` TEXT NOT NULL ,
  `active` BOOLEAN NOT NULL DEFAULT false ,
  `activationCode` INT(11) NOT NULL DEFAULT -1 ,
  `notifiable` BOOLEAN NOT NULL DEFAULT false ,
  `privatized` BOOLEAN NOT NULL DEFAULT false ,
  `loggedIn` BOOLEAN NOT NULL DEFAULT false ,
  `role` TEXT NULL ,
  `backgroundColor` TEXT NULL ,
  `avatarLocation` TEXT NULL ,
  `backgroundImageRepeat` TEXT NULL ,
  `location` TEXT NULL ,
  `feedLocation` TEXT NULL ,
  `college` TEXT NULL ,
  `backgroundImageLocation` TEXT NULL ,
  PRIMARY KEY (`userId`) )
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pachume`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pachume` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `pachume` (
  `pachumeId` INT(11) ZEROFILL NOT NULL AUTO_INCREMENT ,
  `userId` INT(11) NOT NULL ,
  `pachume` TEXT NOT NULL ,
  `viewCount` INT(11) NOT NULL DEFAULT -1 ,
  `dateSecond` INT(11) NOT NULL DEFAULT -1 ,
  `dateMinute` INT(11) NOT NULL DEFAULT -1 ,
  `dateHour` INT(11) NOT NULL DEFAULT -1 ,
  `dateDay` INT(11) NOT NULL DEFAULT -1 ,
  `dateMonth` INT(11) NOT NULL DEFAULT -1 ,
  `dateYear` INT(11) NOT NULL DEFAULT -1 ,
  `dateYearDay` INT(11) NOT NULL DEFAULT -1 ,
  `tags` TEXT NULL ,
  `channelId` INT(11) NOT NULL DEFAULT -1 ,
  `privatized` BOOLEAN NOT NULL DEFAULT false ,
  `role` TEXT NULL ,
  `location` TEXT NULL ,
  `spamCount` INT NOT NULL DEFAULT 0 ,
  `groupId` INT UNSIGNED NULL ,
  PRIMARY KEY (`pachumeId`) ,
  CONSTRAINT `fk_{1EF061C9-46F8-4835-8631-1241465F8DE0}`
    FOREIGN KEY (`userId` )
    REFERENCES `mydb`.`user` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tag` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `tag` (
  `tagId` INT(11) ZEROFILL NOT NULL AUTO_INCREMENT ,
  `tag` TEXT NOT NULL ,
  `pachumeId` INT(11) ZEROFILL NOT NULL ,
  PRIMARY KEY (`tagId`) ,
  CONSTRAINT `fk_{BADE6622-E232-4F90-BF0F-68965EBE8A52}`
    FOREIGN KEY (`pachumeId` )
    REFERENCES `mydb`.`pachume` (`pachumeId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `api`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `api` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `api` (
  `intClientid` INT(11) NOT NULL ,
  `intClientkey` INT(11) NOT NULL ,
  PRIMARY KEY (`intClientid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `bookmark`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookmark` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `bookmark` (
  `intBookmarkid` INT(11) NOT NULL ,
  `intPachumeid` INT(11) NOT NULL ,
  `intUserid` INT(11) NOT NULL ,
  PRIMARY KEY (`intBookmarkid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `channel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `channel` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `channel` (
  `intChannelid` INT(11) NOT NULL ,
  `intUserid` INT(11) NOT NULL ,
  `boolIsprivate` TINYINT(1) NOT NULL ,
  `intSecuritycode` INT(11) NOT NULL ,
  `strChannelname` TEXT NOT NULL ,
  `strChanneldescription` TEXT NOT NULL ,
  `strAvatarlocation` TEXT NOT NULL ,
  `boolBackgroundimagerepeat` TINYINT(1) NOT NULL ,
  `strBackgroundimagecolor` TEXT NOT NULL ,
  `strBackgroundimagelocation` TEXT NOT NULL ,
  PRIMARY KEY (`intChannelid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `comment` (
  `commentId` INT(11) NOT NULL ,
  `pachumeId` INT(11) ZEROFILL NOT NULL ,
  `userId` INT(11) NOT NULL ,
  `dateSecond` INT(11) NOT NULL ,
  `dateHour` INT(11) NOT NULL ,
  `dateDay` INT(11) NOT NULL ,
  `dateMonth` INT(11) NOT NULL ,
  `dateYear` INT(11) NOT NULL ,
  `dateYearDay` INT(11) NOT NULL ,
  `channelId` INT(11) NOT NULL ,
  `groupId` INT NOT NULL ,
  PRIMARY KEY (`commentId`) ,
  CONSTRAINT `fk_{58B87E3B-144A-4CAC-8C88-9740E15D69B1}`
    FOREIGN KEY (`pachumeId` )
    REFERENCES `mydb`.`pachume` (`pachumeId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contact` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `contact` (
  `contactId` INT(11) ZEROFILL NOT NULL AUTO_INCREMENT ,
  `userId` INT(11) NOT NULL ,
  `friendId` INT(11) NOT NULL ,
  PRIMARY KEY (`contactId`) ,
  CONSTRAINT `fk_{76A847BD-D7E8-45C4-BC26-A71E1EDB5F18}`
    FOREIGN KEY (`userId` )
    REFERENCES `mydb`.`user` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `member` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `member` (
  `intMemberid` INT(11) NOT NULL ,
  `intChannelid` INT(11) NOT NULL ,
  `intUserid` INT(11) NOT NULL ,
  `intSecuritycode` INT(11) NOT NULL ,
  `boolIsmember` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`intMemberid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `stream`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stream` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `stream` (
  `intStreamid` INT(11) NOT NULL ,
  `intPachumeid` INT(11) NOT NULL ,
  `intCommentid` INT(11) NOT NULL ,
  `intUserid` INT(11) NOT NULL ,
  PRIMARY KEY (`intStreamid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `spam`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spam` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `spam` (
  `spamId` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `pachumeId` INT(11) ZEROFILL NOT NULL ,
  `userId` INT(11) NOT NULL ,
  PRIMARY KEY (`spamId`) ,
  CONSTRAINT `fk_{7D792BE9-6269-4FD9-A7E7-A6E027F4F9C4}`
    FOREIGN KEY (`userId` )
    REFERENCES `mydb`.`user` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_{1FCB9784-AFBC-44CA-9A71-D55CEC305288}`
    FOREIGN KEY (`pachumeId` )
    REFERENCES `mydb`.`pachume` (`pachumeId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `user` (
  `userId` INT(11) NOT NULL AUTO_INCREMENT ,
  `screenName` TEXT NOT NULL ,
  `firstName` TEXT NOT NULL ,
  `lastName` TEXT NOT NULL ,
  `userPassword` TEXT NOT NULL ,
  `email` TEXT NOT NULL ,
  `active` BOOLEAN NOT NULL DEFAULT false ,
  `activationCode` INT(11) NOT NULL DEFAULT -1 ,
  `notifiable` BOOLEAN NOT NULL DEFAULT false ,
  `privatized` BOOLEAN NOT NULL DEFAULT false ,
  `loggedIn` BOOLEAN NOT NULL DEFAULT false ,
  `role` TEXT NULL ,
  `backgroundColor` TEXT NULL ,
  `avatarLocation` TEXT NULL ,
  `backgroundImageRepeat` TEXT NULL ,
  `location` TEXT NULL ,
  `feedLocation` TEXT NULL ,
  `college` TEXT NULL ,
  `backgroundImageLocation` TEXT NULL ,
  PRIMARY KEY (`userId`) )
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pachume`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pachume` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `pachume` (
  `pachumeId` INT(11) ZEROFILL NOT NULL AUTO_INCREMENT ,
  `userId` INT(11) NOT NULL ,
  `pachume` TEXT NOT NULL ,
  `viewCount` INT(11) NOT NULL DEFAULT -1 ,
  `dateSecond` INT(11) NOT NULL DEFAULT -1 ,
  `dateMinute` INT(11) NOT NULL DEFAULT -1 ,
  `dateHour` INT(11) NOT NULL DEFAULT -1 ,
  `dateDay` INT(11) NOT NULL DEFAULT -1 ,
  `dateMonth` INT(11) NOT NULL DEFAULT -1 ,
  `dateYear` INT(11) NOT NULL DEFAULT -1 ,
  `dateYearDay` INT(11) NOT NULL DEFAULT -1 ,
  `tags` TEXT NULL ,
  `channelId` INT(11) NOT NULL DEFAULT -1 ,
  `privatized` BOOLEAN NOT NULL DEFAULT false ,
  `role` TEXT NULL ,
  `location` TEXT NULL ,
  `spamCount` INT NOT NULL DEFAULT 0 ,
  `groupId` INT UNSIGNED NULL ,
  PRIMARY KEY (`pachumeId`) ,
  CONSTRAINT `fk_{1EF061C9-46F8-4835-8631-1241465F8DE0}`
    FOREIGN KEY (`userId` )
    REFERENCES `mydb`.`user` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tag` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `tag` (
  `tagId` INT(11) ZEROFILL NOT NULL AUTO_INCREMENT ,
  `tag` TEXT NOT NULL ,
  `pachumeId` INT(11) ZEROFILL NOT NULL ,
  PRIMARY KEY (`tagId`) ,
  CONSTRAINT `fk_{BADE6622-E232-4F90-BF0F-68965EBE8A52}`
    FOREIGN KEY (`pachumeId` )
    REFERENCES `mydb`.`pachume` (`pachumeId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `api`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `api` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `api` (
  `intClientid` INT(11) NOT NULL ,
  `intClientkey` INT(11) NOT NULL ,
  PRIMARY KEY (`intClientid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `bookmark`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookmark` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `bookmark` (
  `intBookmarkid` INT(11) NOT NULL ,
  `intPachumeid` INT(11) NOT NULL ,
  `intUserid` INT(11) NOT NULL ,
  PRIMARY KEY (`intBookmarkid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `channel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `channel` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `channel` (
  `intChannelid` INT(11) NOT NULL ,
  `intUserid` INT(11) NOT NULL ,
  `boolIsprivate` TINYINT(1) NOT NULL ,
  `intSecuritycode` INT(11) NOT NULL ,
  `strChannelname` TEXT NOT NULL ,
  `strChanneldescription` TEXT NOT NULL ,
  `strAvatarlocation` TEXT NOT NULL ,
  `boolBackgroundimagerepeat` TINYINT(1) NOT NULL ,
  `strBackgroundimagecolor` TEXT NOT NULL ,
  `strBackgroundimagelocation` TEXT NOT NULL ,
  PRIMARY KEY (`intChannelid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `comment` (
  `commentId` INT(11) NOT NULL ,
  `pachumeId` INT(11) ZEROFILL NOT NULL ,
  `userId` INT(11) NOT NULL ,
  `dateSecond` INT(11) NOT NULL ,
  `dateHour` INT(11) NOT NULL ,
  `dateDay` INT(11) NOT NULL ,
  `dateMonth` INT(11) NOT NULL ,
  `dateYear` INT(11) NOT NULL ,
  `dateYearDay` INT(11) NOT NULL ,
  `channelId` INT(11) NOT NULL ,
  `groupId` INT NOT NULL ,
  PRIMARY KEY (`commentId`) ,
  CONSTRAINT `fk_{58B87E3B-144A-4CAC-8C88-9740E15D69B1}`
    FOREIGN KEY (`pachumeId` )
    REFERENCES `mydb`.`pachume` (`pachumeId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contact` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `contact` (
  `contactId` INT(11) ZEROFILL NOT NULL AUTO_INCREMENT ,
  `userId` INT(11) NOT NULL ,
  `friendId` INT(11) NOT NULL ,
  PRIMARY KEY (`contactId`) ,
  CONSTRAINT `fk_{76A847BD-D7E8-45C4-BC26-A71E1EDB5F18}`
    FOREIGN KEY (`userId` )
    REFERENCES `mydb`.`user` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `member` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `member` (
  `intMemberid` INT(11) NOT NULL ,
  `intChannelid` INT(11) NOT NULL ,
  `intUserid` INT(11) NOT NULL ,
  `intSecuritycode` INT(11) NOT NULL ,
  `boolIsmember` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`intMemberid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `stream`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stream` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `stream` (
  `intStreamid` INT(11) NOT NULL ,
  `intPachumeid` INT(11) NOT NULL ,
  `intCommentid` INT(11) NOT NULL ,
  `intUserid` INT(11) NOT NULL ,
  PRIMARY KEY (`intStreamid`) );

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `spam`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spam` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `spam` (
  `spamId` INT ZEROFILL NOT NULL AUTO_INCREMENT ,
  `pachumeId` INT(11) ZEROFILL NOT NULL ,
  `userId` INT(11) NOT NULL ,
  PRIMARY KEY (`spamId`) ,
  CONSTRAINT `fk_{7D792BE9-6269-4FD9-A7E7-A6E027F4F9C4}`
    FOREIGN KEY (`userId` )
    REFERENCES `mydb`.`user` (`userId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_{1FCB9784-AFBC-44CA-9A71-D55CEC305288}`
    FOREIGN KEY (`pachumeId` )
    REFERENCES `mydb`.`pachume` (`pachumeId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
