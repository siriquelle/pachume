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
  boolIsprivate BOOL NOT NULL,
  intSecuritycode INTEGER(11) NOT NULL,
  strChannelname TEXT NOT NULL,
  strChanneldescription TEXT NOT NULL,
  strAvatarlocation TEXT NOT NULL,
  boolBackgroundimagerepeat BOOL NOT NULL,
  strBackgroundimagecolor TEXT NOT NULL,
  strBackgroundimagelocation TEXT NOT NULL,
  PRIMARY KEY(intChannelid)
);

CREATE TABLE comment (
  intCommentid INTEGER(11) NOT NULL,
  strComment TEXT NOT NULL,
  intUserid INTEGER(11) NOT NULL,
  intDatesecond INTEGER(11) NOT NULL,
  intDatehour INTEGER(11) NOT NULL,
  intDateday INTEGER(11) NOT NULL,
  intDatemonth INTEGER(11) NOT NULL,
  intDateyear INTEGER(11) NOT NULL,
  intDateyearday INTEGER(11) NOT NULL,
  intPachumeid INTEGER(11) NOT NULL,
  intChannelid INTEGER(11) NOT NULL,
  PRIMARY KEY(intCommentid)
);

CREATE TABLE contact (
  intContactid INTEGER(11) NOT NULL,
  intUserid INTEGER(11) NOT NULL,
  intFriendid INTEGER(11) NOT NULL,
  PRIMARY KEY(intContactid)
);

CREATE TABLE member (
  intMemberid INTEGER(11) NOT NULL,
  intChannelid INTEGER(11) NOT NULL,
  intUserid INTEGER(11) NOT NULL,
  intSecuritycode INTEGER(11) NOT NULL,
  boolIsmember BOOL NOT NULL,
  PRIMARY KEY(intMemberid)
);

CREATE TABLE pachume (
  intPachumeid INTEGER(11) NOT NULL,
  strPachume TEXT NOT NULL,
  intUserid INTEGER(11) NOT NULL,
  intViewcount INTEGER(11) NOT NULL,
  intReplycount INTEGER(11) NOT NULL,
  intDatesecond INTEGER(11) NOT NULL,
  intDateminute INTEGER(11) NOT NULL,
  intDatehour INTEGER(11) NOT NULL,
  intDateday INTEGER(11) NOT NULL,
  intDatemonth INTEGER(11) NOT NULL,
  intDateyear INTEGER(11) NOT NULL,
  intDateyearday INTEGER(11) NOT NULL,
  strImagelocation TEXT NOT NULL,
  strTags TEXT NOT NULL,
  intChannelid INTEGER(11) NOT NULL,
  boolIsprivate BOOL NOT NULL,
  strRole TEXT NOT NULL,
  PRIMARY KEY(intPachumeid)
);

CREATE TABLE stream (
  intStreamid INTEGER(11) NOT NULL,
  intPachumeid INTEGER(11) NOT NULL,
  intCommentid INTEGER(11) NOT NULL,
  intUserid INTEGER(11) NOT NULL,
  PRIMARY KEY(intStreamid)
);

CREATE TABLE tag (
  intTagid INTEGER(11) NOT NULL,
  strTag TEXT NOT NULL,
  intPachumeid INTEGER(11) NOT NULL,
  PRIMARY KEY(intTagid)
);

CREATE TABLE user (
  intUserid INTEGER(11) NOT NULL,
  strUsername TEXT NOT NULL,
  strFirstname TEXT NOT NULL,
  strSecondname TEXT NOT NULL,
  strPassword TEXT NOT NULL,
  strEmail TEXT NOT NULL,
  boolIsactive BOOL NOT NULL,
  intActivationcode INTEGER(11) NOT NULL,
  boolNotify BOOL NOT NULL,
  boolIsprivate BOOL NOT NULL,
  boolIsloggedin BOOL NOT NULL,
  strRole TEXT NOT NULL,
  strBackgroundcolor TEXT NOT NULL,
  strAvatarlocation TEXT NOT NULL,
  strBackgroundimagelocation TEXT NOT NULL,
  boolBackgroundimagerepeat BOOL NOT NULL,
  strLocation TEXT NOT NULL,
  strFeedlocation TEXT NOT NULL,
  strCollege TEXT NOT NULL,
  PRIMARY KEY(intUserid)
);


