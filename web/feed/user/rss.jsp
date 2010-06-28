<?xml version="1.0" encoding="utf-8"?>
<%@ page import="command.user.*, command.pachume.*, model.DataBaseConnection, bean.streamBean, bean.userBean, java.sql.ResultSet, command.time.GetTime, command.channel.GetUsersChannels, command.group.GetUsersGroups ,  java.util.Vector, java.util.Iterator, java.util.Enumeration, org.safehaus.uuid.UUIDGenerator; " %>
<%@ page contentType="text/xml" %>
<%//feed for user
            userBean thisUser = null;
            streamBean thisStream = null;
            DataBaseConnection db = null;
            UUIDGenerator joe = null;
            ResultSet rs = null;

            Vector usersGroups = null;
            Vector usersContacts = null;
            Vector usersChannels = null;

            Iterator groupIterator = null;
            Iterator contactsIterator = null;
            Iterator channelIterator = null;

            String sql = null;
            String userName = null;
            String apiKey = null;
            String requestFrom = null;
            String q = null;
            String groupId = null;
            String pachume = null;
            String uuid = null;
            String pachume_screenName = null;

            String groupList = "";
            String contactList = "";
            String channelList = "";
            
            int thisUser_userId = -1;
            int p = -1;
            int channelId = -1;
            int pachumeId = -1;
            int pachume_userId = -1;


%>
<rss version="2.0">
    <channel>
        <%
            userName = request.getParameter("user");
            apiKey = request.getParameter("apiKey");
            thisUser = new userBean(userName);
            if (apiKey.equals(thisUser.getApiKey()) || thisUser == null)
              {
        %>
        
        <title>pachumes from <%=thisUser.getScreenName()%></title>
        <description><%=thisUser.getScreenName()%>'s microblog stream</description>
        <link>http://pachume.com/profile/<%=thisUser.getScreenName().trim()%></link>
        <lastBuildDate><%= GetTime.run("pachume", "pachumeId", GetUsersLastPachumeId.run(thisUser.getUserId()))%></lastBuildDate>
        <managingEditor><%=thisUser.getScreenName().trim()%>@pachume.com (<%=thisUser.getFirstName()%> <%=thisUser.getLastName().trim()%>)</managingEditor>
        
        <%
              thisStream = new streamBean(request);
              thisUser_userId = GetUserId.run(userName);
              requestFrom = thisStream.getRequestFrom();
              q = thisStream.getQ();
              p = thisStream.getP();
              channelId = thisStream.getChanelId();
              groupId = thisStream.getGroupId();
              usersGroups = GetUsersGroups.run(thisUser_userId);
              groupIterator = usersGroups.iterator();

              while (groupIterator.hasNext())
                {
                  groupList += ("'" + groupIterator.next().toString() + "',");
                }

              if (thisUser == null)
                {
                  requestFrom = "viewAll";
                }

              if (requestFrom.equals("stream"))
                {

                  usersContacts = GetUsersContacts.run(thisUser_userId);
                  contactsIterator = usersContacts.iterator();
                  while (contactsIterator.hasNext())
                    {
                      contactList += contactsIterator.next().toString() + ",";
                    }

                  usersChannels = GetUsersChannels.run(thisUser_userId);
                  channelIterator = usersChannels.iterator();
                  while (channelIterator.hasNext())
                    {
                      channelList += channelIterator.next().toString() + ",";
                    }

                  sql = "SELECT * FROM pachume WHERE pachume.channelId IN (" + channelList + "'-1000') OR pachume.groupId IN ('00000000-0000-0000-0000-000000000000') AND pachume.userId IN (" + contactList + thisUser_userId + ") ORDER BY pachume.pachumeId DESC LIMIT 20 OFFSET " + p;
                } else if (requestFrom.equalsIgnoreCase("profile"))
                {
                  sql = "SELECT * FROM pachume WHERE userId =" + thisUser_userId + " AND pachume.groupId IN ('00000000-0000-0000-0000-000000000000') ORDER BY pachumeId DESC LIMIT 20 OFFSET " + p;
                }

              db = new DataBaseConnection();
              db.connect();
              rs = db.execSQL(sql);

              while (rs.next())
                {
                  pachume = rs.getString("pachume.pachume");
                  pachumeId = rs.getInt("pachume.pachumeId");
                  pachume_userId = rs.getInt("pachume.userId");

                  pachume_screenName = GetScreenName.run(pachume_userId).trim();

                  joe = UUIDGenerator.getInstance();
                  uuid = joe.generateRandomBasedUUID().toString();
        %>                       
        
        <item>
            <title><![CDATA[<%= pachume%>]]></title>
            <link>http://pachume.com/comment/<%=pachumeId%></link>
            <guid isPermaLink="false">urn:uuid:<%=uuid%></guid>
            <pubDate><%= rs.getTimestamp("dateTime")%></pubDate>
            <description>By <%= pachume_screenName%> <%= GetPacumeLocation.run(pachumeId)%> | <%= GetTime.run("pachume", "pachumeId", pachumeId)%></description>
        
            <author><%=GetScreenName.run(pachume_userId)%></author>
            <image><%=GetAvatarLocation.run(pachume_userId)%></image>
            
        
        </item>
        
        <%
              }
            db.close();
          } else
          {%>
        
        
        <%}%>
        
    </channel>
</rss>
