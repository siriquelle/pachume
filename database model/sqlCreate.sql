SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `pachume` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `pachume`;

-- -----------------------------------------------------
-- Table `pachume`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pachume`.`user` (
  `intUserid` INT NOT NULL AUTO_INCREMENT ,
  `strUsername` TEXT NOT NULL ,
  `strFirstname` TEXT NOT NULL ,
  `strSecondname` TEXT NOT NULL ,
  `strPassword` TEXT NOT NULL ,
  `strEmail` TEXT NOT NULL ,
  `boolIsactive` BOOLEAN NOT NULL ,
  `intActivationcode` INT NOT NULL ,
  `boolNotify` BOOLEAN NOT NULL ,
  `boolIsprivate` BOOLEAN NOT NULL ,
  `boolIsloggedin` BOOLEAN NOT NULL ,
  `strRole` TEXT NOT NULL ,
  `strBackgroundcolor` TEXT NOT NULL ,
  `strAvatarlocation` TEXT NOT NULL ,
  `strBackgroundimagelocation` TEXT NOT NULL ,
  `strBackgroundimagerepeat` BOOLEAN NOT NULL ,
  `strLocation` TEXT NOT NULL ,
  `strFeedlocation` TEXT NOT NULL ,
  `strCollege` TEXT NOT NULL ,
  PRIMARY KEY (`intUserid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pachume`.`pachume`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pachume`.`pachume` (
  `intPachumeid` INT NOT NULL AUTO_INCREMENT ,
  `strPachume` TEXT NOT NULL ,
  `intUserid`  INT NOT NULL ,
  `intViewcount` INT NOT NULL ,
  `intReplycount` INT NOT NULL ,
  `intDatesecond` INT NOT NULL ,
  `intDateminute` INT NOT NULL ,
  `intDatehour` INT NOT NULL ,
  `intDateday` INT NOT NULL ,
  `intDatemonth` INT NOT NULL ,
  `intDateyear` INT NOT NULL ,
  `intDateyearday` INT NOT NULL ,
  `strImagelocation` TEXT NOT NULL ,
  `strTags` TEXT NOT NULL ,
  `intChannelid` INT NOT NULL ,
  `boolIsprivate` BOOLEAN NOT NULL ,
  `strRole` TEXT NOT NULL ,
  PRIMARY KEY (`intPachumeid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pachume`.`channel`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pachume`.`channel` (
  `intChannelid` INT NOT NULL AUTO_INCREMENT ,
  `strUserid` INT NOT NULL ,
  `boolIsprivate` BOOLEAN NOT NULL ,
  `intSecuritycode` INT NOT NULL ,
  `strChannelname` TEXT NOT NULL ,
  `strChanneldescription` TEXT NOT NULL ,
  `strAvatarlocation` TEXT NOT NULL ,
  `strBackgroundimagerepeat` BOOLEAN NOT NULL ,
  `strBackgroundimagecolor` TEXT NOT NULL ,
  `strBackgroundimagelocation` TEXT NOT NULL ,
  PRIMARY KEY (`intChannelid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pachume`.`member`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pachume`.`member` (
  `intMemberid` INT NOT NULL AUTO_INCREMENT ,
  `intChannelid` INT NOT NULL ,
  `intUserid` INT NOT NULL ,
  `intSecuritycode` INT NOT NULL DEFAULT 0 ,
  `boolIsmember` BOOLEAN NOT NULL DEFAULT false ,
  PRIMARY KEY (`intMemberid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pachume`.`api`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pachume`.`api` (
  `intClientid` INT NOT NULL AUTO_INCREMENT ,
  `strClientkey` INT NOT NULL ,
  PRIMARY KEY (`intClientid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pachume`.`bookmark`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pachume`.`bookmark` (
  `intBookmarkid` INT NOT NULL AUTO_INCREMENT ,
  `intPachumeid` INT NOT NULL ,
  `intUserid` INT NOT NULL ,
  PRIMARY KEY (`intBookmarkid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pachume`.`stream`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pachume`.`stream` (
  `intStreamid` INT NOT NULL AUTO_INCREMENT ,
  `intPachumeid` INT NOT NULL ,
  `intCommentid` INT NOT NULL ,
  `intUserid` INT NOT NULL ,
  PRIMARY KEY (`intStreamid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pachume`.`contact`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pachume`.`contact` (
  `intContactid` INT NOT NULL AUTO_INCREMENT,
  `intUserid` INT NOT NULL ,
  `intFriendid` INT NOT NULL ,
  PRIMARY KEY (`intContactid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pachume`.`tag`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pachume`.`tag` (
  `intTagid` INT NOT NULL AUTO_INCREMENT ,
  `strTag` TEXT NOT NULL ,
  `intPachumeid` TEXT NOT NULL ,
  PRIMARY KEY (`intTagid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pachume`.`comment`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pachume`.`comment` (
  `intCommentid` INT NOT NULL AUTO_INCREMENT ,
  `strComment` TEXT NOT NULL ,
  `intUserid` INT NOT NULL ,
  `intDatesecond` INT NOT NULL ,
  `intDatehour` INT NOT NULL ,
  `intDateday` INT NOT NULL ,
  `intDatemonth` INT NOT NULL ,
  `intDateyear` INT NOT NULL ,
  `intDateyearday` INT NOT NULL ,
  `intPachumeid` INT NOT NULL ,
  `intChannelid` INT NOT NULL ,
  PRIMARY KEY (`intCommentid`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
