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

CREATE TABLE comment (
  commentId INTEGER(11) NOT NULL,
  pachumeId INTEGER(11) ZEROFILL NOT NULL,
  userId INTEGER(11) NOT NULL,
  dateSecond INTEGER(11) NOT NULL,
  dateHour INTEGER(11) NOT NULL,
  dateDay INTEGER(11) NOT NULL,
  dateMonth INTEGER(11) NOT NULL,
  dateYear INTEGER(11) NOT NULL,
  dateYearDay INTEGER(11) NOT NULL,
  channelId INTEGER(11) NOT NULL,
  groupId INTEGER NOT NULL,
  PRIMARY KEY(commentId)
);

CREATE TABLE contact (
  contactId INTEGER(11) ZEROFILL NOT NULL AUTO_INCREMENT,
  userId INTEGER(11) NOT NULL,
  friendId INTEGER(11) NOT NULL,
  PRIMARY KEY(contactId)
);

CREATE TABLE member (
  intMemberid INTEGER(11) NOT NULL,
  intChannelid INTEGER(11) NOT NULL,
  intUserid INTEGER(11) NOT NULL,
  intSecuritycode INTEGER(11) NOT NULL,
  boolIsmember TINYINT(1) NOT NULL,
  PRIMARY KEY(intMemberid)
);

CREATE TABLE pachume (
  pachumeId INTEGER(11) ZEROFILL NOT NULL AUTO_INCREMENT,
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
  tags TEXT NULL,
  channelId INTEGER(11) NOT NULL DEFAULT -1,
  privatized BOOL NOT NULL DEFAULT false,
  role TEXT NULL,
  location TEXT NULL,
  spamCount INTEGER NOT NULL DEFAULT 0,
  groupId INTEGER UNSIGNED NULL,
  PRIMARY KEY(pachumeId)
);

CREATE TABLE spam (
  spamId INTEGER ZEROFILL NOT NULL AUTO_INCREMENT,
  pachumeId INTEGER(11) ZEROFILL NOT NULL,
  userId INTEGER(11) NOT NULL,
  PRIMARY KEY(spamId)
);

CREATE TABLE stream (
  intStreamid INTEGER(11) NOT NULL,
  intPachumeid INTEGER(11) NOT NULL,
  intCommentid INTEGER(11) NOT NULL,
  intUserid INTEGER(11) NOT NULL,
  PRIMARY KEY(intStreamid)
);

CREATE TABLE tag (
  tagId INTEGER(11) ZEROFILL NOT NULL AUTO_INCREMENT,
  tag TEXT NOT NULL,
  pachumeId INTEGER(11) ZEROFILL NOT NULL,
  PRIMARY KEY(tagId)
);

CREATE TABLE user (
  userId INTEGER(11) NOT NULL AUTO_INCREMENT,
  screenName TEXT NOT NULL,
  firstName TEXT NOT NULL,
  lastName TEXT NOT NULL,
  userPassword TEXT NOT NULL,
  email TEXT NOT NULL,
  active BOOL NOT NULL DEFAULT false,
  activationCode INTEGER(11) NOT NULL DEFAULT -1,
  notifiable BOOL NOT NULL DEFAULT false,
  privatized BOOL NOT NULL DEFAULT false,
  loggedIn BOOL NOT NULL DEFAULT false,
  role TEXT NULL,
  backgroundColor TEXT NULL,
  avatarLocation TEXT NULL,
  backgroundImageRepeat TEXT NULL,
  location TEXT NULL,
  feedLocation TEXT NULL,
  college TEXT NULL,
  backgroundImageLocation TEXT NULL,
  PRIMARY KEY(userId)
);


