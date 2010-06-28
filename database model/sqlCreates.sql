CREATE  TABLE IF NOT EXISTS `user` (
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


CREATE  TABLE IF NOT EXISTS `pachume` (
  `intPachumeid` INT NOT NULL AUTO_INCREMENT ,
  `strPachume` TEXT NOT NULL ,
  `intUserid`  NOT NULL ,
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


CREATE  TABLE IF NOT EXISTS `channel` (
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


CREATE  TABLE IF NOT EXISTS `member` (
  `intMemberid` INT NOT NULL AUTO_INCREMENT ,
  `intChannelid` INT NOT NULL ,
  `intUserid` INT NOT NULL ,
  `intSecuritycode` INT NOT NULL DEFAULT 0 ,
  `boolIsmember` BOOLEAN NOT NULL DEFAULT false ,
  PRIMARY KEY (`intMemberid`) )
ENGINE = InnoDB;


CREATE  TABLE IF NOT EXISTS `api` (
  `intClientid` INT NOT NULL AUTO_INCREMENT ,
  `strClientkey` INT NOT NULL ,
  PRIMARY KEY (`intClientid`) )
ENGINE = InnoDB;


CREATE  TABLE IF NOT EXISTS `bookmark` (
  `intBookmarkid` INT NOT NULL AUTO_INCREMENT ,
  `intPachumeid` INT NOT NULL ,
  `intUserid` INT NOT NULL ,
  PRIMARY KEY (`intBookmarkid`) )
ENGINE = InnoDB;


CREATE  TABLE IF NOT EXISTS `stream` (
  `intStreamid` INT NOT NULL AUTO_INCREMENT ,
  `intPachumeid` INT NOT NULL ,
  `intCommentid` INT NOT NULL ,
  `intUserid` INT NOT NULL ,
  PRIMARY KEY (`intStreamid`) )
ENGINE = InnoDB;


CREATE  TABLE IF NOT EXISTS `contact` (
  `intContactid` INT NOT NULL ,
  `intUserid` INT NOT NULL ,
  `intFriendid`  NOT NULL ,
  PRIMARY KEY (`intContactid`) )
ENGINE = InnoDB;


CREATE  TABLE IF NOT EXISTS `tag` (
  `intTagid` INT NOT NULL AUTO_INCREMENT ,
  `strTag` TEXT NOT NULL ,
  `intPachumeid` TEXT NOT NULL ,
  PRIMARY KEY (`intTagid`) )
ENGINE = InnoDB;


CREATE  TABLE IF NOT EXISTS `comment` (
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
