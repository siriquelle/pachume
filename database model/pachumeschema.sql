CREATE TABLE user (
  userId INTEGER(11) NOT NULL,
  screenName TEXT NOT NULL,
  firstName TEXT NOT NULL,
  lastName TEXT NOT NULL,
  userPassword TEXT NOT NULL,
  email TEXT NOT NULL,
  active TINYINT(1) NOT NULL,
  activationCode INTEGER(11) NOT NULL DEFAULT -1,
  notifiable TINYINT(1) NOT NULL,
  privatized TINYINT(1) NOT NULL,
  loggedIn TINYINT(1) NOT NULL,
  role TEXT NOT NULL,
  backgroundColor TEXT NOT NULL,
  avatarLocation TEXT NOT NULL,
  backgroundImageRepeat TEXT NOT NULL,
  location TEXT NOT NULL,
  feedLocation TEXT NOT NULL,
  college TEXT NOT NULL,
  backgroundImageLocation TEXT NOT NULL,
  PRIMARY KEY(userId)
);

CREATE TABLE stream (
  intStreamid INTEGER(11) NOT NULL,
  intPachumeid INTEGER(11) NOT NULL,
  intCommentid INTEGER(11) NOT NULL,
  intUserid INTEGER(11) NOT NULL,
  PRIMARY KEY(intStreamid)
);

CREATE TABLE channel (
  intChannelid INTEGER(11) NOT NULL,
  intUserid INTEGER(11) NOT NULL,
  boolIsprivate TINYINT(1) NOT NULL,
  intSecuritycode INTEGER(11) NOT NULL,
  strChannelname TEXT NOT NULL,
  strChanneldescription TEXT NOT NULL,
  strAvatarlocation TEXT NOT NULL,
  boolBackgroundimagerepeat TINYINT(1) NOT NULL,
  strBackgroundimagecolor TEXT NOT NULL,
  strBackgroundimagelocation TEXT NOT NULL,
  PRIMARY KEY(intChannelid)
);

CREATE TABLE api (
  intClientid INTEGER(11) NOT NULL,
  intClientkey INTEGER(11) NOT NULL,
  PRIMARY KEY(intClientid)
);

CREATE TABLE bookmark (
  intBookmarkid INTEGER(11) NOT NULL,
  intPachumeid INTEGER(11) NOT NULL,
  intUserid INTEGER(11) NOT NULL,
  PRIMARY KEY(intBookmarkid)
);

CREATE TABLE pachume (
  pachumeId INTEGER(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  userId INTEGER(11) NOT NULL,
  pachume TEXT NOT NULL,
  viewCount INTEGER(11) NOT NULL DEFAULT -1,
  dateSecond INTEGER(11) NOT NULL DEFAULT -1,
  dateMinute INTEGER(11) NOT NULL DEFAULT -1,
  dateHour INTEGER(11) NOT NULL DEFAULT -1,
  dateDay INTEGER(11) NOT NULL DEFAULT -1,
  dateMonth INTEGER(11) NOT NULL DEFAULT -1,
  dateYear INTEGER(11) NOT NULL DEFAULT -1,
  dateYearDay INTEGER(11) NOT NULL DEFAULT -1,
  tags TEXT NOT NULL,
  channelId INTEGER(11) NOT NULL DEFAULT -1,
  privatized TINYINT(1) NOT NULL,
  role TEXT NOT NULL,
  location TEXT NOT NULL,
  spamCount INTEGER(11) NOT NULL,
  groupId INTEGER(10) UNSIGNED NOT NULL,
  PRIMARY KEY(pachumeId),
  INDEX userId(userId),
  FOREIGN KEY(userId)
    REFERENCES user(userId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE contact (
  contactId INTEGER(11) UNSIGNED ZEROFILL NOT NULL,
  userId INTEGER(11) NOT NULL,
  friendId INTEGER(11) NOT NULL,
  PRIMARY KEY(contactId),
  INDEX userId(userId),
  FOREIGN KEY(userId)
    REFERENCES user(userId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE member (
  intMemberid INTEGER(11) NOT NULL,
  intChannelid INTEGER(11) NOT NULL,
  intUserid INTEGER(11) NOT NULL,
  intSecuritycode INTEGER(11) NOT NULL,
  boolIsmember TINYINT(1) NOT NULL,
  PRIMARY KEY(intMemberid),
  FOREIGN KEY(intChannelid)
    REFERENCES channel(intChannelid)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE comment (
  commentId INTEGER(11) NOT NULL,
  pachumeId INTEGER(11) UNSIGNED ZEROFILL NOT NULL,
  userId INTEGER(11) NOT NULL,
  dateSecond INTEGER(11) NOT NULL,
  dateHour INTEGER(11) NOT NULL,
  dateDay INTEGER(11) NOT NULL,
  dateMonth INTEGER(11) NOT NULL,
  dateYear INTEGER(11) NOT NULL,
  dateYearDay INTEGER(11) NOT NULL,
  channelId INTEGER(11) NOT NULL,
  groupId INTEGER(11) NOT NULL,
  PRIMARY KEY(commentId),
  INDEX pachumeId(pachumeId),
  FOREIGN KEY(pachumeId)
    REFERENCES pachume(pachumeId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(userId)
    REFERENCES user(userId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE spam (
  spamId INTEGER(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  pachumeId INTEGER(11) UNSIGNED ZEROFILL NOT NULL,
  userId INTEGER(11) NOT NULL,
  PRIMARY KEY(spamId),
  INDEX userId(userId),
  INDEX pachumeId(pachumeId),
  FOREIGN KEY(pachumeId)
    REFERENCES pachume(pachumeId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(userId)
    REFERENCES user(userId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE tag (
  tagId INTEGER(11) UNSIGNED ZEROFILL NOT NULL,
  tag TEXT NOT NULL,
  pachumeId INTEGER(11) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY(tagId),
  INDEX pachumeId(pachumeId),
  FOREIGN KEY(pachumeId)
    REFERENCES pachume(pachumeId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


